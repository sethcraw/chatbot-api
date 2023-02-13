package cn.seth.chatbot.api.application.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import cn.seth.chatbot.api.common.utils.JsonUtils;
import cn.seth.chatbot.api.domain.ai.IOpenAIApi;
import cn.seth.chatbot.api.domain.zsxq.IZsxqApi;
import cn.seth.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.seth.chatbot.api.domain.zsxq.model.vo.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author ssx
 * @version V1.0
 * @className ChatbotSchedule
 * @description chatbot回答任务处理
 * @date 2023-02-13 11:28
 * @since 1.8
 */
@EnableScheduling
@Configuration
@Slf4j
public class ChatbotSchedule {
    @Value(value = "${chatbot-api.groupId}")
    private String groupId;
    @Value(value = "${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAIApi openAIApi;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void run(){
        try {
            // 为避免爬虫太频繁,随机boolean
            if (RandomUtil.randomBoolean()) {
                log.info("随机打烊中...");
                return;
            }
            // 凌晨不进行回答
            GregorianCalendar calendar = new GregorianCalendar();
            int i = calendar.get(Calendar.HOUR_OF_DAY);
            if (i > 22 || i < 7) {
                log.info("打烊时间不工作，AI 下班了！");
                return;
            }
            UnAnsweredQuestionAggregates questions = zsxqApi.queryUnAnsweredQuestions(groupId, cookie);
            log.info("检索结果：{}", JsonUtils.toJsonString(questions));
            if (!questions.isSucceeded()) {
                log.info("查询问题失败");
                return;
            }
            List<Topics> topics = questions.getRespData().getTopics();
            if (CollUtil.isEmpty(topics)) {
                log.info("本次检索未查询到待会答问题");
                return;
            }
            // 获取未回答问题
            Topics topic = topics.get(0);
            String text = openAIApi.doChatGPT(topic.getQuestion().getText());
            boolean answer = zsxqApi.answer(groupId, cookie, topic.getTopicId(), text, false);
            log.info("编号:{},问题:{},答案:{},状态:{}", topic.getTopicId(), topic.getQuestion().getText(),
                    text, answer);
        } catch (Exception e) {
            log.error("自动回答问题异常: {}", e.getLocalizedMessage(), e);
        }
    }
}

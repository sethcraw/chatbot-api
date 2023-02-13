package cn.seth.chatbot.api.test;

import cn.seth.chatbot.api.domain.ai.IOpenAIApi;
import cn.seth.chatbot.api.domain.zsxq.IZsxqApi;
import cn.seth.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.seth.chatbot.api.domain.zsxq.model.vo.Topics;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className SpringBootRunTest
 * @description springboot测试类
 * @date 2023-02-07 13:29
 * @since 1.8
 */
@SpringBootTest
@Slf4j
public class SpringBootRunTest {
    @Value(value = "${chatbot-api.groupId}")
    private String groupId;
    @Value(value = "${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAIApi openAIApi;

    @Test
    public void test_query_unAnswerQuestion() throws IOException {
        UnAnsweredQuestionAggregates unAnsweredQuestionAggregates = zsxqApi.queryUnAnsweredQuestions(groupId, cookie);
        log.info("结果插叙结果为:{}", unAnsweredQuestionAggregates);
        if (unAnsweredQuestionAggregates.isSucceeded()) {
            List<Topics> topics = unAnsweredQuestionAggregates.getRespData().getTopics();
            for (Topics topic : topics) {
                log.info("topicId:{},问题为:{}", topic.getTopicId(), topic.getQuestion().getText());
                // 回答问题
                zsxqApi.answer(groupId, cookie, topic.getTopicId(), topic.getQuestion().getText(), false);
            }
        }
    }

    @Test
    public void test_doChatGPT() throws IOException {
        String question = "写个表扬帅哥王晴宇的歌";
        String res = openAIApi.doChatGPT(question);
        log.info("ai 返回结果为:{}", res);
    }
}

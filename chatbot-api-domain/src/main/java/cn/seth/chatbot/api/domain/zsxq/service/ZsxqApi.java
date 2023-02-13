package cn.seth.chatbot.api.domain.zsxq.service;

import cn.seth.chatbot.api.domain.zsxq.IZsxqApi;
import cn.seth.chatbot.api.common.utils.JsonUtils;
import cn.seth.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.seth.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.seth.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.seth.chatbot.api.domain.zsxq.model.res.AnswerRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author ssx
 * @version V1.0
 * @className ZsxqApi
 * @description 知识星球api实现
 * @date 2023-02-06 17:02
 * @since 1.8
 */
@Service
@Slf4j
public class ZsxqApi implements IZsxqApi {
    @Override
    public UnAnsweredQuestionAggregates queryUnAnsweredQuestions(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=unanswered_questions&count=20");
        httpGet.setHeader("cookie", cookie);
        httpGet.setHeader("content-type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                log.info("拉取提问问题. groupId:{}, jsonStr:{}",groupId, res);
                return JsonUtils.parseObject(res, UnAnsweredQuestionAggregates.class);
            } else {
                log.error("queryUnAnsweredQuestions: Err code is:{}", response.getStatusLine().getStatusCode());
                throw new RuntimeException("queryUnAnsweredQuestions: Err code is :"+ response.getStatusLine().getStatusCode());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/answer");
        httpPost.addHeader("content-type", "application/json; charset=UTF-8");
        httpPost.setHeader("cookie", cookie);
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        /* 测试数据
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";*/
        CloseableHttpResponse response = null;
        try {
            ReqData reqData = ReqData.builder()
                    .text(text)
                    .silenced(false)
                    .build();
            AnswerReq answerReq = new AnswerReq(reqData);
            httpPost.setEntity(new StringEntity(JsonUtils.toJsonString(answerReq), ContentType.create("text/json", "UTF-8")));
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                log.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, res);
                AnswerRes answerRes = JsonUtils.parseObject(res, AnswerRes.class);
                return answerRes.isSucceeded();
            } else {
                log.error("请求报错,信息为:{}", response.getStatusLine().getStatusCode());
                throw new RuntimeException("answer Err code is" + response.getStatusLine().getStatusCode());
            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}

package cn.seth.chatbot.api.domain.ai.service;

import cn.seth.chatbot.api.common.utils.JsonUtils;
import cn.seth.chatbot.api.domain.ai.IOpenAIApi;
import cn.seth.chatbot.api.domain.ai.model.aggregates.AiAnswerAggregates;
import cn.seth.chatbot.api.domain.ai.model.req.AiReq;
import cn.seth.chatbot.api.domain.ai.model.vo.Choices;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className OpenAIApi
 * @description chatgpt ai接口实现
 * @date 2023-02-09 13:26
 * @since 1.8
 */
@Service
@Slf4j
public class OpenAIApi implements IOpenAIApi {
    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Override
    public String doChatGPT(String question) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.openai.com/v1/completions");
        httpPost.setHeader("Authorization", "Bearer "+openAiKey);
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = null;
        try {
            AiReq aiReq = AiReq.builder()
                    .prompt(question)
                    .maxTokens(1024)
                    .build();

            httpPost.setEntity(new StringEntity(JsonUtils.toJsonString(aiReq), ContentType.create("text/json", "UTF-8")));
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                log.info("问题:{},chatgpt回答为:{}",question, res);
                AiAnswerAggregates aiAnswerAggregates = JsonUtils.parseObject(res, AiAnswerAggregates.class);
                List<Choices> choices = aiAnswerAggregates.getChoices();
                StringBuilder stringBuilder = new StringBuilder();
                for (Choices choice : choices) {
                    stringBuilder.append(choice.getText());
                }
                return stringBuilder.toString();
            } else {
                throw new RuntimeException("doChatGPT Err code is "+ response.getStatusLine().getStatusCode());
            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }
}

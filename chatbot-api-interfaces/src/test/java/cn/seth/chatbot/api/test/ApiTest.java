package cn.seth.chatbot.api.test;

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
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author ssx
 * @version V1.0
 * @className ApiTest
 * @description 单元测试
 * @date 2023-02-02 14:01
 * @since 1.8
 */
@Slf4j
public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/51112288588224/topics?scope=all&count=20");
//        httpGet.setHeader("cookie", "zsxq_access_token=566B1C1F-AFE3-DA8D-5B91-C32FC21CC888_606409C82CDE18DA; zsxqsessionid=bbb2661e8ac59e4a678313b7148bfdb1; abtest_env=beta");
        httpGet.setHeader("cookie", "zsxqsessionid=719deb5ffa4e66089e39a5c07b76bf59; sensorsdata2015jssdkcross={\"distinct_id\":\"844142842281522\",\"first_id\":\"18576ecddeaab6-0c897244f8104b8-26021151-921600-18576ecddeba96\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg1NzZlY2RkZWFhYjYtMGM4OTcyNDRmODEwNGI4LTI2MDIxMTUxLTkyMTYwMC0xODU3NmVjZGRlYmE5NiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6Ijg0NDE0Mjg0MjI4MTUyMiJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"844142842281522\"},\"$device_id\":\"18576ecddeaab6-0c897244f8104b8-26021151-921600-18576ecddeba96\"}; abtest_env=product; zsxq_access_token=5D2ECE8C-C8C6-3869-1014-22E66A5B6ABA_606409C82CDE18DA");
        httpGet.setHeader("content-type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = null;
        try {
             response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                HttpEntity entity = response.getEntity();
//                String jsonRes = JsonUtils.toJsonString(entity);
                String res = EntityUtils.toString(response.getEntity());
                log.info("返回查询结果为:{}",res);
            } else {
                log.error("请求报错,信息为:{}", response.getStatusLine().getStatusCode());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/584115818228854/comments");
        httpPost.addHeader("content-type", "application/json; charset=UTF-8");
        httpPost.setHeader("cookie", "zsxqsessionid=719deb5ffa4e66089e39a5c07b76bf59; sensorsdata2015jssdkcross={\"distinct_id\":\"844142842281522\",\"first_id\":\"18576ecddeaab6-0c897244f8104b8-26021151-921600-18576ecddeba96\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg1NzZlY2RkZWFhYjYtMGM4OTcyNDRmODEwNGI4LTI2MDIxMTUxLTkyMTYwMC0xODU3NmVjZGRlYmE5NiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6Ijg0NDE0Mjg0MjI4MTUyMiJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"844142842281522\"},\"$device_id\":\"18576ecddeaab6-0c897244f8104b8-26021151-921600-18576ecddeba96\"}; abtest_env=product; zsxq_access_token=5D2ECE8C-C8C6-3869-1014-22E66A5B6ABA_606409C82CDE18DA");
//        httpPost.setHeader("cookie", "zsxq_access_token=566B1C1F-AFE3-DA8D-5B91-C32FC21CC888_606409C82CDE18DA; zsxqsessionid=bbb2661e8ac59e4a678313b7148bfdb1; abtest_env=beta");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new StringEntity(paramJson,ContentType.create("text/json", "UTF-8")));
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                log.info("回答评论返回为:{}", res);
            } else {
                log.error("请求报错,信息为:{}", response.getStatusLine().getStatusCode());
            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}

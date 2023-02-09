package cn.seth.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @author ssx
 * @version V1.0
 * @className IOpenAIApi
 * @description chatgptApi接口
 * @date 2023-02-09 13:25
 * @since 1.8
 */
public interface IOpenAIApi {
    String doChatGPT(String question) throws IOException;
}

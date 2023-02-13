package cn.seth.chatbot.api.domain.zsxq;

import cn.seth.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;

import java.io.IOException;

/**
 * @author ssx
 * @version V1.0
 * @className IZsxqApi
 * @description 知识星球api
 * @date 2023-02-06 17:01
 * @since 1.8
 */
public interface IZsxqApi {
    UnAnsweredQuestionAggregates queryUnAnsweredQuestions(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}

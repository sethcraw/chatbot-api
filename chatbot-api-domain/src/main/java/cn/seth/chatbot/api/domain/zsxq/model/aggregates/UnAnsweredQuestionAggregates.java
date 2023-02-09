package cn.seth.chatbot.api.domain.zsxq.model.aggregates;

import cn.seth.chatbot.api.domain.zsxq.model.res.RespData;
import lombok.Data;

/**
 * @author ssx
 * @version V1.0
 * @className UnAnsweredQuestionAggregates
 * @description 为回答问题聚合类
 * @date 2023-02-06 23:40
 * @since 1.8
 */
@Data
public class UnAnsweredQuestionAggregates {
    /**
     * <pre>
     *
     * </pre>
     */
    private boolean	succeeded;

    /**
     * <pre>
     * respData
     * </pre>
     */
    private RespData respData;
}

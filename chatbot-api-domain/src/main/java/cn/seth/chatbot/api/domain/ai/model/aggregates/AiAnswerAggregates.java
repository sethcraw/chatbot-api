package cn.seth.chatbot.api.domain.ai.model.aggregates;

import cn.seth.chatbot.api.domain.ai.model.vo.Choices;
import cn.seth.chatbot.api.domain.ai.model.vo.Usage;
import lombok.Data;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className AiAnswerAggregates
 * @description chatgpt 回答类
 * @date 2023-02-09 14:18
 * @since 1.8
 */
@Data
public class AiAnswerAggregates {
    /**
     * <pre>
     *
     * </pre>
     */
    private String	id;

    /**
     * <pre>
     *
     * </pre>
     */
    private String	object;

    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	created;

    /**
     * <pre>
     *
     * </pre>
     */
    private String	model;

    /**
     * <pre>
     * choices
     * </pre>
     */
    private List<Choices> choices;

    /**
     * <pre>
     * usage
     * </pre>
     */
    private Usage usage;
}

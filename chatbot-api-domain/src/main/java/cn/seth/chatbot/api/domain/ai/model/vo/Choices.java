package cn.seth.chatbot.api.domain.ai.model.vo;

import lombok.Data;

/**
 * <pre>
 *  Choices
 * </pre>
 * @author ssx
 * @verison $Id: Choices v 0.1 2023-02-09 14:17:30
 */
@Data
public class Choices{

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	text;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	index;

    /**
     * <pre>
     * logprobs
     * </pre>
     */
    private String	logprobs;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	finishReason;


}

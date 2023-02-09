package cn.seth.chatbot.api.domain.ai.model.vo;

import lombok.Data;

/**
 * <pre>
 *  Usage
 * </pre>
 * @author ssx
 * @verison $Id: Usage v 0.1 2023-02-09 14:17:30
 */
@Data
public class Usage{

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	promptTokens;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	completionTokens;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	totalTokens;


}

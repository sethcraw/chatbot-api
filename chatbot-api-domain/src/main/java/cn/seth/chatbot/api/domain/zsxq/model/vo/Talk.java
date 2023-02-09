package cn.seth.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * <pre>
 *  Talk
 * </pre>
 * @author seth
 * @verison $Id: Talk v 0.1 2023-02-06 17:35:38
 */
@Data
public class Talk{

    /**
     * <pre>
     * owner
     * </pre>
     */
    private Owner	owner;

    /**
     * <pre>
     * 你是谁
     * </pre>
     */
    private String	text;


}

package cn.seth.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * <pre>
 *  Question
 * </pre>
 * @author ssx
 * @verison $Id: Question v 0.1 2023-02-13 15:08:09
 */
@Data
public class Question{

    /**
     * <pre>
     * owner
     * </pre>
     */
    private Owner	owner;

    /**
     * <pre>
     * questionee
     * </pre>
     */
    private Questionee	questionee;

    /**
     * <pre>
     * 写一个冒泡排序算法
     * </pre>
     */
    private String	text;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	expired;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	anonymous;

    /**
     * <pre>
     * ownerDetail
     * </pre>
     */
    private OwnerDetail	ownerDetail;

    /**
     * <pre>
     * 安徽
     * </pre>
     */
    private String	ownerLocation;


}

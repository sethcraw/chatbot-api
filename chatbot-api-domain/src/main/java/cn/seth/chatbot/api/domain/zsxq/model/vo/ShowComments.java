package cn.seth.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * <pre>
 *  ShowComments
 * </pre>
 * @author seth
 * @verison $Id: ShowComments v 0.1 2023-02-06 17:35:38
 */
@Data
public class ShowComments{

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	commentId;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	createTime;

    /**
     * <pre>
     * owner
     * </pre>
     */
    private Owner owner;

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
    private Integer	likesCount;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	rewardsCount;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	sticky;


}

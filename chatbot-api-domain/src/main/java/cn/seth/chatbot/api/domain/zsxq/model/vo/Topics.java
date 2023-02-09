package cn.seth.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

import java.util.List;

/**
 * <pre>
 *  Topics
 * </pre>
 * @author seth
 * @verison $Id: Topics v 0.1 2023-02-06 17:35:38
 */
@Data
public class Topics{

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	topicId;

    /**
     * <pre>
     * group
     * </pre>
     */
    private Group	group;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	type;

    /**
     * <pre>
     * talk
     * </pre>
     */
    private Talk	talk;

    /**
     * <pre>
     * showComments
     * </pre>
     */
    private List<ShowComments> showComments;

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
    private Integer	commentsCount;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	readingCount;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	readersCount;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	digested;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	sticky;

    /**
     * <pre>
     * 
     * </pre>
     */
    private String	createTime;

    /**
     * <pre>
     * userSpecific
     * </pre>
     */
    private UserSpecific	userSpecific;


}

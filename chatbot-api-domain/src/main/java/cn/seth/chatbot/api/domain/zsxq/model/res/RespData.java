package cn.seth.chatbot.api.domain.zsxq.model.res;

import cn.seth.chatbot.api.domain.zsxq.model.vo.Topics;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 *  RespData
 * </pre>
 * @author seth
 * @verison $Id: RespData v 0.1 2023-02-06 17:35:38
 */
@Data
public class RespData{

    /**
     * <pre>
     * topics
     * </pre>
     */
    private List<Topics> topics;


}

package cn.seth.chatbot.api.domain.zsxq.model.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <pre>
 *  ReqData
 * </pre>
 * @author seth
 * @verison $Id: ReqData v 0.1 2023-02-07 00:20:36
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqData{

    /**
     * <pre>
     * 
     * </pre>
     */
    @NonNull
    private String	text;

    /**
     * <pre>
     * imageIds
     * </pre>
     */
    private List<String> imageIds;

    /**
     * <pre>
     * mentionedUserIds
     * </pre>
     */
    private List<String>	mentionedUserIds;


}

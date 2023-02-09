package cn.seth.chatbot.api.domain.ai.model.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * <pre>
 *  Root
 * </pre>
 * @author ssx
 * @verison $Id: Root v 0.1 2023-02-09 13:46:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AiReq {

    /**
     * <pre>
     * 
     * </pre>
     */
    @Builder.Default
    private String	model = "text-davinci-003";

    /**
     * <pre>
     * 
     * </pre>
     */
    @NonNull
    private String	prompt;

    /**
     * <pre>
     * 
     * </pre>
     */
    @Builder.Default
    private Integer	temperature = 0;

    /**
     * <pre>
     * 
     * </pre>
     */
    private Integer	maxTokens;


}

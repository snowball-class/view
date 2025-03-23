package shop.snowballclass.view.dto.member;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberInfoResponse (
        Boolean result,
        UUID memberUUID,
        String name,
        String nickname,
        String email,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime joinDate
) {

}

package shop.snowballclass.view.dto.member;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberInfoResponse (
    Boolean result,
    UUID memberUUID,
    String name,
    String nickname,
    String email,
    LocalDateTime joinDate
) {

}
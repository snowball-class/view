package shop.snowballclass.view.dto.member;


import com.fasterxml.jackson.annotation.JsonFormat;
import shop.snowballclass.view.dto.lesson.LessonResponse;

import java.time.LocalDateTime;
import java.util.List;
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
        public static MemberInfoResponse from(MemberInfoResponse memberInfo) {
                return new MemberInfoResponse(
                        memberInfo.result(),
                        memberInfo.memberUUID(),
                        memberInfo.name(),
                        memberInfo.nickname(),
                        memberInfo.email(),
                        memberInfo.joinDate()
                );
        }
}

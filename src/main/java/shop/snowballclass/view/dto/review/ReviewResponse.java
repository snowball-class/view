package shop.snowballclass.view.dto.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ReviewResponse(
        @Schema(description = "리뷰 작성자", example = "Jerry")
        String writer,
        @Schema(description = "별점", example = "4,7")
        Double starScore,
        @Schema(description = "리뷰 내용", example = "13")
        String content,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        @Schema(description = "리뷰 작성일", example = "2025-03-04T12:30:16")
        LocalDateTime createdAt


) {
    public static ReviewResponse from(String writer, Double starScore, String content, LocalDateTime createdAt){
        return new ReviewResponse(
                writer,
                starScore,
                content,
                createdAt
        );
    }
}

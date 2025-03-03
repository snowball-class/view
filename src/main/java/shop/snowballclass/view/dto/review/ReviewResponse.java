package shop.snowballclass.view.dto.review;

public record ReviewResponse(
        Double starScore,
        String content
//        강의명, 리뷰내용, 별점, 작성날짜
) {
}

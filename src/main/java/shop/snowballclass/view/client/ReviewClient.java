package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;

import java.util.List;

@FeignClient(name = "review-service", url = "${feign.snowball.review}")
public interface ReviewClient {

    // 예시 코드
    @GetMapping("/bulk")
    ApiResponse<List<ReviewResponse>> getBulkReviews(@RequestParam("ids") String reviewIds);

}

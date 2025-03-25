package shop.snowballclass.view.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.client.EventClient;
import shop.snowballclass.view.client.LessonClient;
import shop.snowballclass.view.client.ReviewClient;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.event.EventResponse;
import shop.snowballclass.view.dto.lesson.LessonDetailsResponse;
import shop.snowballclass.view.dto.lesson.LessonViewResponse;
import shop.snowballclass.view.dto.lesson.LessonResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.exception.common.ExternalServiceException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LessonViewService {
    private final EventLessonService eventLessonService;
    private final LessonReviewService lessonReviewService;
    private final LessonClient lessonClient;
    private final EventClient eventClient;
    private final ReviewClient reviewClient;

    @Transactional(readOnly = true)
    public LessonViewResponse getLessonView(Long lessonId) {
        try {
            LessonResponse lessonResponse = lessonClient.getLessonByLessonId(lessonId).data();
            EventResponse eventData = eventLessonService.findEventLessonByLessonId(lessonId)
                    .map(EventLesson::getEventId)
                    .map(eventClient::getEventByEventId)
                    .map(ApiResponse::data)
                    .orElse(null);

            // Review 서비스 개발 완료후 적용
//            String reviewIds = lessonReviewService.getLessonReviewListByLessonId(lessonId).stream()
//                    .map(LessonReview::getReviewId)
//                    .map(String::valueOf)
//                    .collect(Collectors.joining(","));
//            List<ReviewResponse> reviewList = reviewClient.getBulkReviews(reviewIds).data();

            Integer discountedPrice = lessonResponse.price();
            Integer numOfStudents = 18; // MemberLesson쪽 서비스 개발 완료 이후 수강자 수 계산
            Double averageStarScore = 4.7;// LessonReview쪽 서비스 개발 완료 이후 평균 별점 계산
            if(eventData != null) {
                discountedPrice = discountedPrice * (100 - eventData.discountRate()) / 100;
            }

            LessonDetailsResponse lessonDetails = LessonDetailsResponse.from(lessonResponse, discountedPrice, numOfStudents, averageStarScore);
            List<ReviewResponse> reviewList = List.of( // Review 서비스 개발 완료후 적용
//                    ReviewResponse.from("Jerry", 4.5, "Review Mock Data1", LocalDateTime.now()),
//                    ReviewResponse.from("Tom", 3.5, "Review Mock Data2", LocalDateTime.now())
            );
            return LessonViewResponse.from(lessonDetails, eventData, reviewList);
        } catch (FeignException e) {
            // LessonResponse, EventResponse, List<ReviewResponse> 가져올때 생길 수 있는 FeignException 처리
            throw new ExternalServiceException(String.format("Failed to get response on getLessonView. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }

}

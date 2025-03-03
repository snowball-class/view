package shop.snowballclass.view.service;

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
import shop.snowballclass.view.dto.lesson.LessonResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;
import shop.snowballclass.view.entity.EventLesson;
import shop.snowballclass.view.entity.LessonReview;
import shop.snowballclass.view.exception.common.ExternalServiceException;

import java.util.List;
import java.util.stream.Collectors;

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
    public LessonDetailsResponse getLessonView(Long lessonId) {
        ApiResponse<LessonResponse> lessonResponse = lessonClient.getLessonByLessonId(lessonId);
        if (!lessonResponse.checkStatusOK())
            throw new ExternalServiceException("[LessonClient] Failed to get Lesson response. Status: " + lessonResponse.status());

        EventLesson eventLesson = eventLessonService.getEventLessonByLessonId(lessonId);
        ApiResponse<EventResponse> eventResponse = eventClient.getEventByEventId(eventLesson.getEventId());
        if (!eventResponse.checkStatusOK())
            throw new ExternalServiceException("[EventClient] Failed to get Event response. Status: " + eventResponse.status());

        String reviewIds = lessonReviewService.getLessonReviewListByLessonId(lessonId).stream()
                .map(LessonReview::getReviewId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        ApiResponse<List<ReviewResponse>> reviewListResponse = reviewClient.getBulkReviews(reviewIds);
        if (!reviewListResponse.checkStatusOK())
            throw new ExternalServiceException("[ReviewClient] Failed to get Review response. Status: " + reviewListResponse.status());

        // (할인된 가격, 수강자 수, 평균 별점) 계산하는 로직 들어가야 함
        // 수강자 수는 MemberLesson쪽 서비스 구현 필요
        // 평균 별점은 LessonReview쪽 서비스 구현 필요

        return LessonDetailsResponse.from(lessonResponse.data(), eventResponse.data(), reviewListResponse.data());
    }
}

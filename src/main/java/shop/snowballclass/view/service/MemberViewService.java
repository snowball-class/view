package shop.snowballclass.view.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.snowballclass.view.client.LessonClient;
import shop.snowballclass.view.client.MemberClient;
import shop.snowballclass.view.client.ReviewClient;
import shop.snowballclass.view.dto.lesson.LessonResponse;
import shop.snowballclass.view.dto.member.MemberInfoResponse;
import shop.snowballclass.view.dto.member.MemberLessonViewResponse;
import shop.snowballclass.view.dto.member.MemberReviewResponse;
import shop.snowballclass.view.dto.review.ReviewResponse;
import shop.snowballclass.view.entity.MemberLesson;
import shop.snowballclass.view.exception.common.ExternalServiceException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberViewService {
    private final MemberLessonService memberLessonService;
    private final LessonReviewService lessonReviewService;
    private final LessonClient lessonClient;
    private final MemberClient memberClient;
    private final ReviewClient reviewClient;

    @Transactional(readOnly = true)
    public MemberLessonViewResponse getMemberLessonView(String token) {
        try {
            UUID memberId = memberClient.getMemberInfo(token).data().memberUUID();
            String lessonIds = memberLessonService.getMemberLessonListByMemberId(memberId).stream()
                    .map(MemberLesson::getLessonId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            List<LessonResponse> lessonList = lessonIds.isEmpty() ? List.of() : lessonClient.getBulkLessons(lessonIds).data();
            return MemberLessonViewResponse.from(memberId, lessonList);
        } catch (FeignException e) {
            throw new ExternalServiceException(String.format("Failed to get response on getMemberLessonView. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }

    @Transactional(readOnly = true)
    public MemberReviewResponse getMemberReview(String token) {
        try{
            UUID memberId = memberClient.getMemberInfo(token).data().memberUUID();
            List<ReviewResponse> reviewList = reviewClient.getReviewListByMemberId(token).data();

            // 리뷰에 있는 lessonId 추출
            String lessonIds = reviewList.stream()
                    .map(ReviewResponse::lessonId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            // 리뷰에 있는 lessonId로 강의 정보 벌크 조회
            List<LessonResponse> lessonList = lessonIds.isEmpty() ? List.of() : lessonClient.getBulkLessons(lessonIds).data();
            return MemberReviewResponse.from(memberId, reviewList, lessonList);
        } catch (FeignException e){
            throw new  ExternalServiceException(String.format("Failed to get response on getMemberReview. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }

    public MemberInfoResponse getMemberInfo(String token) {
        try{
            MemberInfoResponse memberInfo = memberClient.getMemberInfo(token).data();
            return MemberInfoResponse.from(memberInfo);
        } catch (FeignException e){
            throw new  ExternalServiceException(String.format("Failed to get response on getMemberInfo. [status:%s][message:%s]", e.status(), e.getMessage()));
        }
    }
}

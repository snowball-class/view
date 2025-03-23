package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.member.MemberInfoResponse;

@FeignClient(name = "member-service", url = "${feign.snowball.member}")
public interface MemberClient {

    @GetMapping
    ApiResponse<MemberInfoResponse> getMemberInfo(@RequestHeader("Authorization") String token);

}

package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.snowballclass.view.dto.ApiResponse;
import shop.snowballclass.view.dto.cart.CartResponse;

@FeignClient(name = "cart-service", url = "${feign.snowball.cart}")
public interface CartClient {
    @GetMapping("/cart/{memberUUID}")
    ApiResponse<CartResponse> getCart(@PathVariable String memberUUID);
}

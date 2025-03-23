package shop.snowballclass.view.client;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "payment-service", url = "${feign.snowball.payment}")
public interface PaymentClient {

}

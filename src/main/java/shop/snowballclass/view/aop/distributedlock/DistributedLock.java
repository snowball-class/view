package shop.snowballclass.view.aop.distributedlock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key(); // Lock의 이름 (고유값)
    TimeUnit timeUnit() default TimeUnit.SECONDS; // 시간 단위
    long waitTime() default 5L; // 락 획득을 위해 waitTime 만큼 대기
    long leaseTime() default 3L; // 락을 획득한 이후 leaseTime 이 지나면 락을 해제
}
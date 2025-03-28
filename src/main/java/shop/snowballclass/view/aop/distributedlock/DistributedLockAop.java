package shop.snowballclass.view.aop.distributedlock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import shop.snowballclass.view.aop.AopForTransaction;
import shop.snowballclass.view.aop.CustomSpringELParser;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {
    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(shop.snowballclass.view.aop.distributedlock.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = REDISSON_LOCK_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributedLock.key());
        RLock rLock = redissonClient.getLock(key); // 락의 이름을 key로 RLock 인스턴스를 가져옴

        try {
            // 정의된 waitTime까지 획득을 시도한다, 정의된 leaseTime이 지나면 잠금을 해제
            boolean available = rLock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
            if (!available) {
                return false;
            }
            // DistributedLock 어노테이션이 선언된 메서드를 별도의 트랜잭션으로, 트랜잭션 실행과 종료(커밋or롤백)까지 수행
            return aopForTransaction.proceed(joinPoint, (int)distributedLock.leaseTime());
        } catch (InterruptedException e) {
            throw new InterruptedException();
        } finally {
            try {
                rLock.unlock(); // 트랜잭션 종료후 락이 해제되므로 데이터의 정합성을 보장할 수 있음
            } catch (IllegalMonitorStateException e) {
                log.info("Redisson Lock Already UnLock [serviceName:{}][key:{}]", method.getName(), key);
            }
        }
    }

}

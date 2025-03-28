package shop.snowballclass.view.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import shop.snowballclass.view.exception.common.LockLeaseTimeoutException;

@Component
@RequiredArgsConstructor
public class AopForTransaction {
    // Propagation.REQUIRES_NEW 옵션을 지정해 부모 트랜잭션의 유무에 관계없이 별도의 트랜잭션으로 동작
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
//        return joinPoint.proceed();
//    }

    private final PlatformTransactionManager transactionManager;
    // todo: timeout 테스트 필요
    public Object proceed(ProceedingJoinPoint joinPoint, int timeoutSeconds) throws Throwable {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.setTimeout(timeoutSeconds); // leaseTime 기반 타임아웃 설정

        return transactionTemplate.execute(status -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new LockLeaseTimeoutException();
            }
        });
    }
}

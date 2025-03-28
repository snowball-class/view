package shop.snowballclass.view.exception.common;

import shop.snowballclass.view.exception.ErrorCode;

public class LockLeaseTimeoutException extends ServiceException {

    public LockLeaseTimeoutException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}

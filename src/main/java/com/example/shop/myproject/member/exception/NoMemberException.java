package com.example.shop.myproject.member.exception;

public class NoMemberException extends RuntimeException {
    private Long memberId;

    public NoMemberException() {
        super();
    }

    public NoMemberException(Long memberId) {
        this.memberId = memberId;
    }

    public NoMemberException(String message) {
        super(message);
    }

    public NoMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMemberException(Throwable cause) {
        super(cause);
    }

    protected NoMemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

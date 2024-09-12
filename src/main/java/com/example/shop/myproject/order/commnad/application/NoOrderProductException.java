package com.example.shop.myproject.order.commnad.application;

public class NoOrderProductException extends RuntimeException {

    private Long productId;

    public NoOrderProductException() {
        super();
    }

    public NoOrderProductException(Long productId) {
        this.productId = productId;
    }

    public NoOrderProductException(String message) {
        super(message);
    }

    public NoOrderProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoOrderProductException(Throwable cause) {
        super(cause);
    }

    protected NoOrderProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
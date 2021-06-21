package com.bjtu.bookstore.utils.exceptionHandler.exception;

public interface BaseErrorInfoInterface {

    Integer getResultCode();

    String getResultMsg();

    Object getResultData();
}
package net.junhabaek.springtestexercise.common.exception;

import lombok.Getter;
import net.junhabaek.springtestexercise.common.response.ErrorStatus;

@Getter
public class BusinessException extends RuntimeException{
    private final ErrorStatus errorStatus;

    protected BusinessException(String message, ErrorStatus errorStatus){
        super(message);
        this.errorStatus=errorStatus;
    }
}
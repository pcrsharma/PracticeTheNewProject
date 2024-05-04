package com.passenger.payment.exception;

public class InsufficientAmountException extends RuntimeException{

	public InsufficientAmountException(String msg){
        super(msg);
    }
}

package br.com.teste.exception;

public class BusinessException extends Exception{
    public BusinessException(String message) {
        super(message);
    }
}
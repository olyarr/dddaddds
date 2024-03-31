package com.example.Warehouse.exception;

public class MaterialFillerAlreadyExistException extends Exception{
    public MaterialFillerAlreadyExistException() {
        super("Такой материал наполнителя уже существует");
    }
}

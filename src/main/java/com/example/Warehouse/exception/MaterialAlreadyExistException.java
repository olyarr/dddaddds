package com.example.Warehouse.exception;

public class MaterialAlreadyExistException extends Exception{
    public MaterialAlreadyExistException() {
        super("Такой материал уже существует");
    }
}

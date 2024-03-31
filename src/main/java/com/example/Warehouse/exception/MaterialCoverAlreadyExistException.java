package com.example.Warehouse.exception;

public class MaterialCoverAlreadyExistException extends Exception{
    public MaterialCoverAlreadyExistException() {
        super("Такой материал чехла уже существует");
    }
}

package com.example.Warehouse.exception;

public class MaterialUsedException extends Exception{
    public MaterialUsedException() { super("Данный материал используется в номенклатуре"); }
}

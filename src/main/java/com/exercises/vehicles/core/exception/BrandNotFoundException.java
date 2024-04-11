package com.exercises.vehicles.core.exception;

public class BrandNotFoundException extends UseCaseException{
    public BrandNotFoundException() {
        super("BRAND_NOT_FOUND");
    }
}

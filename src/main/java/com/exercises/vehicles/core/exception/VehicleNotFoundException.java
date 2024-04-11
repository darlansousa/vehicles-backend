package com.exercises.vehicles.core.exception;

public class VehicleNotFoundException extends UseCaseException{
    public VehicleNotFoundException() {
        super("VEHICLE_NOT_FOUND");
    }
}

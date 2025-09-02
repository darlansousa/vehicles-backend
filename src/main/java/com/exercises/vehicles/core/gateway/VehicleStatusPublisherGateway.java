package com.exercises.vehicles.core.gateway;

import com.exercises.vehicles.core.domain.vehicles.VehicleStatusEvent;

public interface VehicleStatusPublisherGateway {

    void publish(VehicleStatusEvent event);
}

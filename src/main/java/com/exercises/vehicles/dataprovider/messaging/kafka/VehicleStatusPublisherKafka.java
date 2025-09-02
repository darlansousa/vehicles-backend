package com.exercises.vehicles.dataprovider.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.exercises.vehicles.core.domain.vehicles.VehicleStatusEvent;
import com.exercises.vehicles.core.gateway.VehicleStatusPublisherGateway;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehicleStatusPublisherKafka implements VehicleStatusPublisherGateway {

    public final KafkaTemplate<String, Object> template;

    @Override
    public void publish(VehicleStatusEvent event) {
        template.send("vehicle-status", event);
    }

}

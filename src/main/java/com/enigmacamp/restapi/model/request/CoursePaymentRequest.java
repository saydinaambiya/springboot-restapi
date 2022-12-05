package com.enigmacamp.restapi.model.request;

import lombok.Getter;
import lombok.Setter;


public class CoursePaymentRequest {
    @Getter@Setter
    private String transactionId;
    @Getter@Setter
    private String customerId;
    @Getter@Setter
    private String transactionType;
}

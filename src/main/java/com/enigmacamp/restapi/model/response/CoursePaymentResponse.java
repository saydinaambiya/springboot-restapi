package com.enigmacamp.restapi.model.response;

import lombok.Getter;
import lombok.Setter;

public class CoursePaymentResponse {
    @Getter@Setter
    private String transactionId;
    @Setter@Getter
    private boolean status;


}

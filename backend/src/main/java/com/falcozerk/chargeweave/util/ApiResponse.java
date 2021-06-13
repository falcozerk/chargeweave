package com.falcozerk.chargeweave.util;

import lombok.*;

@Data
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean pSuccess, String pMessage) {
        success = pSuccess;
        message = pMessage;
    }
}

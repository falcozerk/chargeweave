package com.falcozerk.chargeweave.beans.user;

import lombok.*;

@Data
public class UserIdentityAvailability {
    private Boolean available;

    public UserIdentityAvailability(Boolean available) {
        this.available = available;
    }

}

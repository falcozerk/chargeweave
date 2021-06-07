package com.falcozerk.chargeweave.beans.user;

import lombok.*;

import java.time.Instant;

@Data
public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;

    public UserProfile(Long id, String username, String name, Instant joinedAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
    }

}

package com.falcozerk.chargeweave.beans.user;

import com.falcozerk.chargeweave.beans.common.CwBean;
import com.falcozerk.chargeweave.beans.role.Role;
import com.falcozerk.chargeweave.util.DateAudit;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class User extends DateAudit implements CwBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @NotBlank
    String username;

    @NaturalId
    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;

    @NotBlank
    String name;

    Integer competitorId;
    String handle;
    String region;
    String leader;
    String badges;
    String century;
}
package com.falcozerk.chargeweave.beans.visit;

import com.falcozerk.chargeweave.beans.charger.Charger;
import com.falcozerk.chargeweave.beans.common.CwBean;
import com.falcozerk.chargeweave.util.DateAudit;
import com.falcozerk.chargeweave.beans.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Visit extends DateAudit implements CwBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Charger charger;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @NotBlank Instant visitDate;

}

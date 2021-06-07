package com.falcozerk.chargeweave.beans.charger;

import com.falcozerk.chargeweave.beans.common.CwBean;
import com.falcozerk.chargeweave.beans.user.UserDateAudit;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Charger extends UserDateAudit implements CwBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer sid;
    String name;
    String streetAddress;
    String city;
    String state;
    String zip;
    String country;
    Integer stalls;
    Double kW;
    String gps;
    Integer elevation;
    String status;
    String tsid;
    Integer originalStalls;

    String type;
    String teslaUrl;
    String discussUrl;

    String region;
    String sort;
    String latitude;
    String longitude;
    Integer visits;

    Instant addDate;
    Instant permitDate;
    Instant constructionDate;
    Instant openDate;
    Instant v3UpgradeDate;
    Instant stallsUpgradeDate;
    Instant firstVisitDate;
    Instant lastActiveDate;
    Instant closedDate;

    Integer daysFromPermitToConstruction;
    Integer daysFromConstructionToOpen;
    Integer daysFromOpenToFirst;

    Integer visits2013;
    Integer visits2014;
    Integer visits2015;
    Integer visits2016;
    Integer visits2017;
    Integer visits2018;
    Integer visits2019;
    Integer visits2020;
    Integer visits2021;

}

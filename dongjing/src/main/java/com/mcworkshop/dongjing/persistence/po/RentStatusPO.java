package com.mcworkshop.dongjing.persistence.po;

import java.util.Date;

/**
 * Created by MarkfredChen on 2014/9/22.
 */
public class RentStatusPO {

    private Long relationID;
    private Long renteeID;
    private Long rentorID;
    private String nonDJCompany;
    private Double area;
    private String address;
    private Date startDate;
    private Date endDate;

    public Long getRelationID() {
        return relationID;
    }

    public void setRelationID(Long relationID) {
        this.relationID = relationID;
    }

    public Long getRenteeID() {
        return renteeID;
    }

    public void setRenteeID(Long renteeID) {
        this.renteeID = renteeID;
    }

    public Long getRentorID() {
        return rentorID;
    }

    public void setRentorID(Long rentorID) {
        this.rentorID = rentorID;
    }

    public String getNonDJCompany() {
        return nonDJCompany;
    }

    public void setNonDJCompany(String nonDJCompany) {
        this.nonDJCompany = nonDJCompany;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

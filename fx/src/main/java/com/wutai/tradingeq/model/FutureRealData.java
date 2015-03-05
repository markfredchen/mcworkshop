// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author $Author$
 * 
 */
public class FutureRealData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date              date;
    private String            ticket;
    private String            type;
    private String            isOpen;
    private double            price;
    private int               amount;
    private double            commission;
    private String            toubao;
    private String            tradingNo;

    public Date getDate() {
        return date;
    }

    public String getTicket() {
        return ticket;
    }

    public String getType() {
        return type;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public double getCommission() {
        return commission;
    }

    public String getToubao() {
        return toubao;
    }

    public String getTradingNo() {
        return tradingNo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setToubao(String toubao) {
        this.toubao = toubao;
    }

    public void setTradingNo(String tradingNo) {
        this.tradingNo = tradingNo;
    }

}

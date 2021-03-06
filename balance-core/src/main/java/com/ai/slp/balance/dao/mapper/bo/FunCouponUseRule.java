package com.ai.slp.balance.dao.mapper.bo;

import java.sql.Timestamp;

public class FunCouponUseRule {
    private String couponUserId;

    private Integer faceValue;

    private Integer requiredMoneyAmount;

    private String currencyUnit;

    private Timestamp createTime;

    public String getCouponUserId() {
        return couponUserId;
    }

    public void setCouponUserId(String couponUserId) {
        this.couponUserId = couponUserId == null ? null : couponUserId.trim();
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public Integer getRequiredMoneyAmount() {
        return requiredMoneyAmount;
    }

    public void setRequiredMoneyAmount(Integer requiredMoneyAmount) {
        this.requiredMoneyAmount = requiredMoneyAmount;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit == null ? null : currencyUnit.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
package com.ai.slp.balance.api.activityrule.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 活动规则变更.<br>
 */
public class FunActivityRuleRequest implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
	 * 活动规则信息
	 */
	private String id;

	private Long activityId;

	private String currencyUnit;

	private Long totalMoneyAmount;

	private Integer frequency;

	private Long remainingMoneyAmount;

	private String createStaff;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit == null ? null : currencyUnit.trim();
	}

	public Long getTotalMoneyAmount() {
		return totalMoneyAmount;
	}

	public void setTotalMoneyAmount(Long totalMoneyAmount) {
		this.totalMoneyAmount = totalMoneyAmount;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Long getRemainingMoneyAmount() {
		return remainingMoneyAmount;
	}

	public void setRemainingMoneyAmount(Long remainingMoneyAmount) {
		this.remainingMoneyAmount = remainingMoneyAmount;
	}

	public String getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff == null ? null : createStaff.trim();
	}

}
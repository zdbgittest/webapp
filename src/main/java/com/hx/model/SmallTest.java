package com.hx.model;

public class SmallTest {
	private String stName;
	private String stAge;
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStAge() {
		return stAge;
	}
	public void setStAge(String stAge) {
		this.stAge = stAge;
	}
	@Override
	public String toString() {
		return "SmallTest [stName=" + stName + ", stAge=" + stAge + "]";
	}
}

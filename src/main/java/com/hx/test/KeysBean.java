package com.hx.test;

public class KeysBean {
	private String server;
	 
	private long bytes;
	private long expiry;
	
	
	
	
	
	public KeysBean(String server2, long parseLong, long parseLong2) {
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public long getBytes() {
		return bytes;
	}
	public void setBytes(long bytes) {
		this.bytes = bytes;
	}
	public long getExpiry() {
		return expiry;
	}
	public void setExpiry(long expiry) {
		this.expiry = expiry;
	}
	
}

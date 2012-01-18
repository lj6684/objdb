package demo;

import javax.persistence.Entity;

@Entity
public class Device {
	
	private String id;
	private String ip;
	private int port;
	private String desc;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", ip=" + ip + ", port=" + port + ", desc="
				+ desc + "]";
	}
	
	

}

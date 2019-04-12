package com.mellisphera.entities.bm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BmHive implements Serializable{

	/*
	 * "uuid": "bBsXpy30jFq6ghQdr1vI85aP9ilz2tSu",
		"created": "2018-10-12 17:31:37",
		"name": "R1",
		"data_last_received": "2019-04-10 12:35:22",
		"devices": []
	 */
	@JsonProperty("uuid")
	private String uuid;
	@JsonProperty("created")
	private String created;
	@JsonProperty("name")
	private String name;
	@JsonProperty("data_last_received")
	private String dataLastReceived;
	@JsonProperty("devices")
	private BmSensor[] devices;
	
	public BmHive() {
		
	}
	public BmHive(String uuid, String created, String name, String dataLastReceived, BmSensor[] devices) {
		this.uuid = uuid;
		this.created = created;
		this.name = name;
		this.dataLastReceived = dataLastReceived;
		this.devices = devices;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataLastReceived() {
		return dataLastReceived;
	}

	public void setDataLastReceived(String dataLastReceived) {
		this.dataLastReceived = dataLastReceived;
	}

	public BmSensor[] getDevices() {
		return devices;
	}

	public void setDevices(BmSensor[] devices) {
		this.devices = devices;
	}
	
	
	
	
}

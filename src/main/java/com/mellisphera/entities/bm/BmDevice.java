package com.mellisphera.entities.bm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BmDevice implements Serializable {
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("createDate")
    private int createDate;
    @JsonProperty("dataLastReceived")
    private int dataLastReceived;
    @JsonProperty("name")
    private String name;
    @JsonProperty("currentLocation")
    private DeviceLocation currentLocation;
    @JsonProperty("deviceAddress")
    private String deviceAddress;
    @JsonProperty("model")
    private String model;

    public BmDevice(String deviceId, int createDate, int dataLastReceived, String name, DeviceLocation currentLocation, String deviceAddress, String model) {
        this.deviceId = deviceId;
        this.createDate = createDate;
        this.dataLastReceived = dataLastReceived;
        this.name = name;
        this.currentLocation = currentLocation;
        this.deviceAddress = deviceAddress;
        this.model = model;
    }

    public BmDevice() { }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getCreateDate() {
        return createDate;
    }

    public DeviceLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(DeviceLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public int getDataLastReceived() {
        return dataLastReceived;
    }

    public void setDataLastReceived(int dataLastReceived) {
        this.dataLastReceived = dataLastReceived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "BmDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", createDate=" + createDate +
                ", dataLastReceived=" + dataLastReceived +
                ", name='" + name + '\'' +
                ", currentLocation=" + currentLocation +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

}
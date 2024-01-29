package com.zp.parking.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class ParkingInfo1 {
    private String ParkingID;
    private String ParkingName;
    private String EnglishName;
    private String ParkingAddr;
    private String ContactPhone;
    private String Linkman;
    private String DtuID;
    private Integer TotalSpace;
    private Integer PublicSpace;
    private Integer TempSpace;
    private Integer CurrentSpace;
    private String LastDate;
    private String LastTime;
    private String otherid;
    private Date createtime;
    private String Lng;
    private String Lat;
    private String BusinessTime;
    private String Type;
    private String Price;
    private String PriceDay;
    private String PriceNight;
    private String City;
    private String PriceAllDay;




}

package com.degtu.myesalon;

public class Salon {

    private String salonname,salonaddress,city,pincode,state,ip,salonimg,gender,service;

    public Salon(String salonname, String salonaddress, String city, String pincode, String state, String ip, String salonimg, String gender, String service) {
        this.salonname = salonname;
        this.salonaddress = salonaddress;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.ip = ip;
        this.salonimg = salonimg;
        this.gender = gender;
        this.service = service;
    }

    public Salon() {
    }

    public String getSalonname() {
        return salonname;
    }

    public void setSalonname(String salonname) {
        this.salonname = salonname;
    }

    public String getSalonaddress() {
        return salonaddress;
    }

    public void setSalonaddress(String salonaddress) {
        this.salonaddress = salonaddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSalonimg() {
        return salonimg;
    }

    public void setSalonimg(String salonimg) {
        this.salonimg = salonimg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}

package com.degtu.myesalon;

public class Booking {

    private String timeslot,inttimee,ampm,salonname,service,ip,salonaddress;

    public Booking(String timeslot, String inttimee, String ampm, String salonname, String service, String ip, String salonaddress) {
        this.timeslot = timeslot;
        this.inttimee = inttimee;
        this.ampm = ampm;
        this.salonname = salonname;
        this.service = service;
        this.ip = ip;
        this.salonaddress = salonaddress;
    }

    public Booking() {
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getInttimee() {
        return inttimee;
    }

    public void setInttimee(String inttimee) {
        this.inttimee = inttimee;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getSalonname() {
        return salonname;
    }

    public void setSalonname(String salonname) {
        this.salonname = salonname;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSalonaddress() {
        return salonaddress;
    }

    public void setSalonaddress(String salonaddress) {
        this.salonaddress = salonaddress;
    }
}

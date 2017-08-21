package com.example.user.selffix;

/**
 * Created by kyawthetwin on 8/20/17.
 */

public class Data {

    String address;
    String username;
    int condition;
    String time;

    public Data(String address, String username, int condition, String time) {
        this.address = address;
        this.username = username;
        this.condition = condition;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

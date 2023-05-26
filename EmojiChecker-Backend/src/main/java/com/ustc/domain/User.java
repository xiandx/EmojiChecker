package com.ustc.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int id;
    private String firstName;  //gets the text from first name field and stores it in firstName
    private String lastName;
    private String email;
    private String birthDate;
    private String phoneNumber;
    private String formattedPhoneNum; //format the number to E164
    private String username;
    private String password;
    private String dateJoined;

    public String getDateJoined() {
        Date date=new Date();
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateJoined=temp.format(date);
        return dateJoined;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFormattedPhoneNum() {
        return formattedPhoneNum;
    }

    public void setFormattedPhoneNum(String formattedPhoneNum) {
        this.formattedPhoneNum = formattedPhoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", formattedPhoneNum='" + formattedPhoneNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

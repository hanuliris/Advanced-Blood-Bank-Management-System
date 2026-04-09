package com.bloodbank.model;

public class Donor {
    private int id;
    private String name;
    private int age;
    private String bloodGroup;
    private String contact;
    private String city;

    public Donor(int id, String name, int age, String bloodGroup, String contact, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.city = city;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getBloodGroup() { return bloodGroup; }
    public String getContact() { return contact; }
    public String getCity() { return city; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setContact(String contact) { this.contact = contact; }
    public void setCity(String city) { this.city = city; }
}

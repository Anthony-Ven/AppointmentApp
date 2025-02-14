package com.example.appointmentapp;

public class User {
    private String name;
    private String username;
    private String preferredTimezone;

    public User(String name, String username, String preferredTimezone) {
        this.name = name;
        this.username = username;
        this.preferredTimezone = preferredTimezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPreferredTimezone() {
        return preferredTimezone;
    }

    public void setPreferredTimezone(String preferredTimezone) {
        this.preferredTimezone = preferredTimezone;
    }
}

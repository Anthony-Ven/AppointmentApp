package com.example.appointmentapp.model;

public class Appointment {
    private int id_creator;
    private String title;
    private String start;
    private String end;

    public Appointment(int id_creator, String title, String start, String end) {
        this.id_creator = id_creator;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

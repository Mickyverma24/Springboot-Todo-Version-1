package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String username;
    @Size(min = 10,message = "Enter least {min} char")
    private String goal;
    private LocalDate now;
    private boolean done;
    public Todo(){}
    public Todo(int id, String username, String goal, LocalDate now, boolean done) {
        this.id = id;
        this.username = username;
        this.goal = goal;
        this.now = now;
        this.done = done;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", goal='" + goal + '\'' +
                ", now=" + now +
                ", done=" + done +
                '}';
    }
}

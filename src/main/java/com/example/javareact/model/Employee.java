package com.example.javareact.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
//import java.io.Serializable;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String supervisor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Employee(@NotBlank String name, @NotBlank String phoneNumber, @NotBlank String supervisor) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.supervisor = supervisor;
    }

    public Employee(){

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }
}


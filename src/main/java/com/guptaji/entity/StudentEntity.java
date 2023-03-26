package com.guptaji.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentEntity {

    @Id
    private int id;
    private String name;
    private String branch;
    private String collegeName;

    public StudentEntity(int id, String name, String branch, String collegeName) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.collegeName = collegeName;
    }

    public StudentEntity() {
        // default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}

package com.backend.testing.dto;

public class UserDto {
    private long id;
    private String name;
    private long age;

    public UserDto() {
    }

    public UserDto(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public UserDto(long id, String name, long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}

package com.backend.testing.enumerations;

public enum UserApiPath {
    GET_ALL_USERS("/getAllUsers"),
    GET_USER_BY_ID("/getUserById"),
    CREATE_USER("/createUser"),
    UPDATE_USER("/updateUser"),
    DELETE_USER_BY_ID("/deleteUser");

    private String path;

    UserApiPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

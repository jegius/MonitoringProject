package ru.ncedu.ecomm.data.models;

public class User {
    private long id;
    private long roleId;
    private String name;
    private String password;

    public User() {
    }

    public User(long id, long roleId, String name, String password) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

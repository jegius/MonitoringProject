package ru.ncedu.ecomm.servlets.ViewModels;

public class UserViewModel {
    private long id;
    private String role;
    private String name;

    public UserViewModel() {
    }

    public UserViewModel(long id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

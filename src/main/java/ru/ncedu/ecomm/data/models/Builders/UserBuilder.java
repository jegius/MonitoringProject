package ru.ncedu.ecomm.data.models.Builders;

import ru.ncedu.ecomm.data.models.User;

public class UserBuilder {
    private long id;
    private long roleId;
    private String name;
    private String password;

    public UserBuilder() {
    }

    public UserBuilder setId(long id) {
        this.id = id;

        return this;
    }

    public UserBuilder setRoleId(long roleId) {
        this.roleId = roleId;

        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;

        return this;
    }

    public User build(){
        return new User(id, roleId, name, password);
    }
}

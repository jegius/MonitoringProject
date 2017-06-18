package ru.ncedu.ecomm.servlets.ViewModels.Builders;

import ru.ncedu.ecomm.servlets.ViewModels.UserViewModel;

public class UserViewModelBuilder {
    private long id;
    private String roleId;
    private String name;

    public UserViewModelBuilder() {
    }

    public UserViewModelBuilder setId(long id) {
        this.id = id;

        return this;
    }

    public UserViewModelBuilder setRoleId(String roleId) {
        this.roleId = roleId;

        return this;
    }

    public UserViewModelBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public UserViewModel build(){
        return new UserViewModel(id, roleId, name);
    }
}

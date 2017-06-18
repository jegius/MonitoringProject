package ru.ncedu.ecomm.servlets.services;


import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.ViewModels.Builders.UserViewModelBuilder;
import ru.ncedu.ecomm.servlets.ViewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserService {


    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<UserViewModel> getUsersViewModels() {
        List<UserViewModel> usersViewModels = new ArrayList<>();

        List<User> users  = DAOFactory
                .getDAOFactory()
                .getUserDAO()
                .getUsers();

        for (User user : users){
                UserViewModel userViewModel = new UserViewModelBuilder()
                        .setId(user.getId())
                        .setName(user.getName())
                        .setRoleId(getRoleName(user.getRoleId()))
                        .build();

                usersViewModels.add(userViewModel);
        }

        return usersViewModels;
    }
    private String getRoleName(long id){
        List<Role> roles = DAOFactory
                .getDAOFactory()
                .getRoleDAO()
                .getRoles();
        for (Role role : roles) {
            if (role.getId() == id){
                return role.getName();
            }
        }
        return null;
    }
}

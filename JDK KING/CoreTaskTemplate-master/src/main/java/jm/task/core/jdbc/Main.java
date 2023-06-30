package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Anton","Zorin", (byte) 21);
        userService.saveUser("Kirill","Sablin", (byte)34);
        userService.saveUser("Albna","Qazanceva", (byte)43);
        userService.saveUser("Ivan","Popov", (byte)21);
        userService.saveUser("Albert","Zacharin", (byte)57);
        userService.getAllUsers();
        System.out.println("______________________________________");
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();





    }
}

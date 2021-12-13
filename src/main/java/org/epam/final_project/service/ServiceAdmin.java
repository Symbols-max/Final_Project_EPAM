package org.epam.final_project.service;

import org.epam.final_project.model.Admin;

public interface ServiceAdmin extends Service{

    Admin add(String login, String password);
    boolean checkAdmin(String login, String password);
    boolean deleteAdmin(String login);
    void deleteAll();
}

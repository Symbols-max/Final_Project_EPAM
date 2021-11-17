package org.epam.final_project.service;

public interface ServiceAdmin extends Service{

    void add(String login, String password);
    boolean checkAdmin(String login, String password);
}

package org.epam.final_project.service;

import org.epam.final_project.util.DBManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceAdminImpl implements ServiceAdmin {
    private static final Logger logger=Logger.getLogger(ServiceAdminImpl.class.getName());
    @Override
    public void add(String login,String password) {
        try(Connection connection= DBManager.connectToDB();
            Statement statement=connection.createStatement())
            {
            statement.executeUpdate("INSERT INTO final_project_epam.admin(login, password) " +
                    "VALUES('"+login+"','"+password+"')");
            }
            catch (SQLException ex) {
                logger.log(Level.WARNING,ex.getMessage(),ex);
        }
    }

    @Override
    public boolean checkAdmin(String login,String password) {
        String sql="select login,password from admin where login=? and password=?;";
        try(Connection connection= DBManager.connectToDB();
            PreparedStatement statement= connection.prepareStatement(sql))
        {
            statement.setString(1, login);
            statement.setString(2, password);
            try(ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    return true;
                }
            }
        }
        catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return false;
    }

    public Integer count(){
        Integer n = null;
        String sql = "SELECT COUNT(*) FROM admin";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                n=rs.getInt(1);
            }
            return n;
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            n=null;
            return n;
        }
    }
}

package org.epam.final_project.service;

import org.epam.final_project.model.Admin;
import org.epam.final_project.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceAdminImpl implements ServiceAdmin {
    private static final Logger logger=Logger.getLogger(ServiceAdminImpl.class.getName());
    private DBManager dbManager;

    public ServiceAdminImpl(String path){
        dbManager=DBManager.getInstance(path);
    }

    public ServiceAdminImpl(){
        dbManager=DBManager.getInstance("database.properties");
    }

    @Override
    public Admin add(String login, String password) {
//        String hash= Helper.coder(password);
        String sql="INSERT INTO admin(login, password) " +
                "VALUES(?,?)";
        try(Connection connection= dbManager.connectToDB();
            PreparedStatement statement=connection.prepareStatement(sql))
            {
                statement.setString(1, login);
                statement.setString(2, password);
                statement.executeUpdate();
            return new Admin(login,password);
            }
            catch (SQLException ex) {
                logger.log(Level.WARNING,ex.getMessage(),ex);
                return null;
        }
    }

    @Override
    public boolean checkAdmin(String login,String password) {
        String sql="select login,password from admin where login=? and password=?";
        try(Connection connection= dbManager.connectToDB();
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
        try (Connection connection = dbManager.connectToDB();
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

    @Override
    public boolean deleteAdmin(String login){
        String sql = "DELETE FROM admin where login=?";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            if (statement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return true;
    }

    @Override
    public void deleteAll(){
        String sql = "DELETE FROM admin";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
        }
    }
}

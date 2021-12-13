package org.epam.final_project.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private final static Logger logger=Logger.getLogger(DBManager.class.getName());
    private String path;
    private static volatile DBManager dbManager;

    private DBManager(String path){
        this.path=path;
    }

    public static DBManager getInstance(String path){
        DBManager manager=dbManager;
        if (manager != null) {
            return manager;
        }
        synchronized(DBManager.class) {
            if (dbManager == null) {
                dbManager = new DBManager(path);
            }
            return dbManager;
        }
    }

    public Connection connectToDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
        }
        Properties properties=new Properties();
        try {
            properties.load(DBManager.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
        }

        return DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("user"),properties.getProperty("password"));

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
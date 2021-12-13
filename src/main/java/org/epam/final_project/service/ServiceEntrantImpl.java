package org.epam.final_project.service;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Subject;
import org.epam.final_project.util.Converter;
import org.epam.final_project.util.DBManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEntrantImpl implements ServiceEntrant {
    private static final Logger logger=Logger.getLogger(ServiceEntrantImpl.class.getName());
    private DBManager dbManager;

    public ServiceEntrantImpl(String path){
        dbManager=DBManager.getInstance(path);
    }
    public ServiceEntrantImpl(){
        dbManager=DBManager.getInstance("database.properties");
    }

    @Override
    public byte[] getDiplomByEmail(String email) {
        byte[] bytes;
        String sql="Select diplom from entrant where email=?";
        try (Connection connection=dbManager.connectToDB();
        PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1,email);
            try (ResultSet rs = statement.executeQuery()) {
                Blob blob = null;
                while (rs.next()) {
                    blob=rs.getBlob(1);
                }
                try(InputStream inputStream=blob.getBinaryStream()){
                    bytes=new byte[inputStream.available()];
                    for (int i=0;i<bytes.length;i++){
                        bytes[i]=(byte)inputStream.read();
                    }
                }
            }
            return bytes;
        } catch (SQLException | IOException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            bytes=null;
            return bytes;
        }
    }

    @Override
    public String checkStatusByEmail(String email) {
        String status = null;
        String sql = "select entrant.status from entrant where email=?";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    status=rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
        }
        return status;
    }

    @Override
    public boolean deleteEntrantById(long id) {
        String sql="DELETE FROM entrant WHERE id_entrant=?";
        try(Connection connection=dbManager.connectToDB();
        PreparedStatement statement=connection.prepareStatement(sql)){

            statement.setLong(1,id);
            statement.executeUpdate();
        }
        catch (SQLException ex){
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean changeStatus(String status,long id_entrant) {
        String sql = "UPDATE entrant set status=? where id_entrant=?";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setLong(2, id_entrant);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Entrant> findAllEntrants() {
        List<Entrant> entrantList=new ArrayList<>();
        String sql = "select * from entrant";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    String city = rs.getString(4);
                    String region = rs.getString(5);
                    String placeEducation = rs.getString(6);
                    Blob blob= rs.getBlob(7);
                    InputStream inputStream=blob.getBinaryStream();
                    byte[] diplom = Converter.streamToByteArray(inputStream);
                    String surname = rs.getString(8);
                    String middleName = rs.getString(9);
                    String status=rs.getString(10);
                    Entrant entrant = new Entrant(id, name, middleName, surname, email, city, region, placeEducation, diplom,status);
                    entrantList.add(entrant);
                }
            }
            return entrantList;
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Entrant> findAllEntrantsByStatus(String status_entrant) {
        List<Entrant> entrantList=new ArrayList<>();
        String sql = "select * from entrant where status=?";
        try (Connection connection =dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,status_entrant);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    String city = rs.getString(4);
                    String region = rs.getString(5);
                    String placeEducation = rs.getString(6);
                    Blob blob= rs.getBlob(7);
                    InputStream inputStream=blob.getBinaryStream();
                    byte[] diplom = Converter.streamToByteArray(inputStream);
                    String surname = rs.getString(8);
                    String middleName = rs.getString(9);
                    String status=rs.getString(10);
                    Entrant entrant = new Entrant(id, name, middleName, surname, email, city, region, placeEducation, diplom,status);
                    entrantList.add(entrant);
                }
            }
            return entrantList;
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean checkEntrantByEmail(String email) {
        String sql = "select * from entrant where email=?;";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return false;
    }

    @Override
    public Entrant findEntrantByEmail(String emailEntrant) {
        Entrant entrant = null;
        String sql = "select * from entrant where email=?;";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emailEntrant);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    String city = rs.getString(4);
                    String region = rs.getString(5);
                    String placeEducation = rs.getString(6);
                    Blob blob= rs.getBlob(7);
                    InputStream inputStream=blob.getBinaryStream();
                    byte[] diplom = Converter.streamToByteArray(inputStream);
                    String surname = rs.getString(8);
                    String middleName = rs.getString(9);
                    String status=rs.getString(10);
                    entrant = new Entrant(id, name, middleName, surname, email, city, region, placeEducation, diplom,status);
                    return entrant;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return null;
        }
        return entrant;
    }

    @Override
    public boolean addEntrant(String name, String surname, String middleName, String email,
                              String city, String region, String placeEducation, InputStream diplom) {

        String sql = "INSERT INTO entrant(first_name,email,city,region,place_of_education,diplom,last_name,midle_name,status)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?);";

        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, city);
            statement.setString(4, region);
            statement.setString(5, placeEducation);
            statement.setBlob(6, diplom);
            statement.setString(7, surname);
            statement.setString(8, middleName);
            statement.setString(9,"active");
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
    public Entrant findEntrantById(long id_entrant) {
        Entrant entrant = null;
        String sql = "select * from entrant where id_entrant=?;";
        try (Connection connection =dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id_entrant);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    String city = rs.getString(4);
                    String region = rs.getString(5);
                    String placeEducation = rs.getString(6);
                    Blob blob= rs.getBlob(7);
                    InputStream inputStream=blob.getBinaryStream();
                    byte[] diplom = Converter.streamToByteArray(inputStream);
                    String surname = rs.getString(8);
                    String middleName = rs.getString(9);
                    String status=rs.getString(10);
                    entrant = new Entrant(id, name, middleName, surname, email, city, region, placeEducation, diplom,status);
                    return entrant;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return null;
        }
        return entrant;
    }

    @Override
    public List<Subject> findSubjectById(long id) {
        List<Subject> subjectList = new ArrayList<>();
        String sql = "select name_subject,grade from subject_entrant where id_entrant=?;";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString(1);
                    int grade = rs.getInt(2);
                    Subject subject = new Subject(name, grade);
                    subjectList.add(subject);
                }
            }
            return subjectList;
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    @Override
    public long findIdByEmail(String emailEntrant) {
        long id = -1;
        String sql = "select * from entrant where email=?;";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emailEntrant);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                    return id;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return -1;
        }
        return id;
    }

    @Override
    public List<Long> findFacultyById(long id) {
        List<Long> id_faculties = new ArrayList<>();
        String sql = "SELECT id_faculty FROM faculty_entrant where id_entrant=?;";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    id = rs.getLong(1);
                    id_faculties.add(id);
                }
                return id_faculties;
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean addEntrantFaculty(long idEntrant, long idFaculty) {
        String sql = "INSERT INTO faculty_entrant(id_faculty, id_entrant) VALUES(?,?);";

        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, idFaculty);
            statement.setLong(2, idEntrant);
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
    public boolean updateEntrantInfoByEmail(String name, String surname, String middleName, String email,
                                            String city, String region, String placeEducation, InputStream diplom, String oldEmail) {
        String sql = "UPDATE entrant SET\n" +
                "first_name = ?,\n" +
                "email = ?,\n" +
                "city = ?,\n" +
                "region = ?,\n" +
                "place_of_education = ?,\n" +
                "diplom = ?,\n" +
                "last_name = ?,\n" +
                "midle_name = ?\n" +
                "WHERE email = ?;";

        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, city);
            statement.setString(4, region);
            statement.setString(5, placeEducation);
            statement.setBlob(6, diplom);
            statement.setString(7, surname);
            statement.setString(8, middleName);
            statement.setString(9, oldEmail);
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
    public boolean deleteFacultyInEntrant(long id_faculty, long id_entrant) {
        String sql = "DELETE FROM faculty_entrant WHERE id_faculty=? and id_entrant=?";

        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id_faculty);
            statement.setLong(2, id_entrant);
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
    public boolean deleteSubjectById(long id) {
        String sql = "DELETE FROM subject_entrant WHERE id_entrant=?";

        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
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
    public boolean addSubjectsById(Map<String, Integer> subjects, long id) {
        String sql = "INSERT INTO subject_entrant (id_entrant,name_subject,grade)\n" +
                "VALUES (?,?,?);";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.connectToDB();
            connection.setAutoCommit(false);
            for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
                statement = connection.prepareStatement(sql);
                statement.setLong(1, id);
                statement.setString(2, entry.getKey());
                statement.setInt(3, entry.getValue());
                if (statement.executeUpdate() != 1) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                logger.log(Level.WARNING,ex.getMessage(),ex);
                return false;
            } catch (SQLException e) {
                logger.log(Level.WARNING,e.getMessage(),e);
                return false;
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                logger.log(Level.WARNING,ex.getMessage(),ex);
                return false;
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING,e.getMessage(),e);
                    return false;
                }
            }

        }
        return true;
    }


    @Override
    public boolean addSubjectsByIdWithDeleting(Map<String, Integer> subjects, long id) {

        String sql2 = "delete from subject_entrant where id_entrant=?";

        String sql = "INSERT INTO subject_entrant (id_entrant,name_subject,grade)\n" +
                "VALUES (?,?,?);";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbManager.connectToDB();
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql2);
            statement.setLong(1, id);
            if(statement.executeUpdate()==-1){
                connection.rollback();
                return false;
            }
            for (Map.Entry<String, Integer> entry : subjects.entrySet()) {
                statement = connection.prepareStatement(sql);
                statement.setLong(1, id);
                statement.setString(2, entry.getKey());
                statement.setInt(3, entry.getValue());
                if (statement.executeUpdate() != 1) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                logger.log(Level.WARNING,ex.getMessage(),ex);
                return false;
            } catch (SQLException e) {
                logger.log(Level.WARNING,e.getMessage(),e);
                return false;
            }
        } finally {
            try {
                statement.close();
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING,e.getMessage(),e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkSubjectById(long id) {
        String sql = "select * from subject_entrant where id_entrant=?;";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return false;
        }
        return false;
    }

    @Override
    public Integer count(){
        Integer n = null;
        String sql = "SELECT COUNT(*) FROM entrant";
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
    public void deleteAll(){
        String sql = "DELETE FROM entrant";
        try (Connection connection = dbManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
        }
    }
}

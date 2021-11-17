package org.epam.final_project.service;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Subject;
import org.epam.final_project.util.DBManager;
import org.epam.final_project.model.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceFacultyImpl implements ServiceFaculty {
    private static final Logger logger=Logger.getLogger(ServiceFacultyImpl.class.getName());
    @Override
    public boolean changeRecruitmentStatusById(String recruitment,long id_faculty){
        String sql = "UPDATE faculty set recruitment=? where id_faculty=?";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, recruitment);
            statement.setLong(2, id_faculty);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return false;
        }
        return true;
    }

    @Override
    public List<Faculty> findAllFaculty() {
        List<Faculty> faculties = new ArrayList<>();
        String sql = "select id_faculty,name_faculty,all_places,funded_places from faculty;";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong(1);
                    String name = rs.getString(2);
                    int allPlace = rs.getInt(3);
                    int fundedPlaces = rs.getInt(4);
                    Faculty faculty = new Faculty(id, name, fundedPlaces, allPlace);
                    faculties.add(faculty);
                }
            }
            return faculties;
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return Collections.emptyList();
        }
    }

    public List<Subject> findSubjectById(long id) {
        List<Subject> subjectList = new ArrayList<>();
        String sql = "select name_subject,grade from subject_faculty where id_faculty=?;";
        try (Connection connection = DBManager.connectToDB();
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
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return Collections.emptyList();
        }
    }

    public Faculty findFacultyById(long id) {
        Faculty faculty = null;
        String sql = "select id_faculty,name_faculty,all_places,funded_places,description,recruitment from faculty where id_faculty=?;";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    long id_faculty = rs.getLong(1);
                    String name = rs.getString(2);
                    int allPlace = rs.getInt(3);
                    int fundedPlaces = rs.getInt(4);
                    String description = rs.getString(5);
                    String recruitment=rs.getString(6);
                    faculty = new Faculty(id_faculty, name, fundedPlaces, allPlace, description,recruitment);
                }
            }
            return faculty;
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return new Faculty();
        }
    }

    public int numberOfStudentsOnFaculty(long id) {
        int i = -1;
        String sql = "SELECT count(*) FROM faculty_entrant where id_faculty=?;";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    i = rs.getInt(1);
                }
            }
            return i;
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return -1;
        }
    }

    public boolean deleteFaculty(long id) {
        String sql = "DELETE FROM faculty WHERE id_faculty=?";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rs = statement.executeUpdate();
            if (rs != 1) {
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return false;
        }
        return true;
    }

    public boolean addFaculty(long id, String name, int allPlaces, int fundedPlaces, String description) {

        String sql = "INSERT INTO faculty(id_faculty, name_faculty, funded_places, all_places, description)\n" +
                "VALUES(?,?,?,?,?);";

        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setString(2, name);
            statement.setInt(3, fundedPlaces);
            statement.setInt(4, allPlaces);
            statement.setString(5, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return false;
        }
        return true;
    }

    public boolean changeFaculty(long id, String name, int allPlaces, int fundedPlaces, String description, long oldFaculty_id) {
        String sql = "UPDATE faculty SET id_faculty=?,name_faculty=?,funded_places=?,all_places=?,description=? WHERE id_faculty=?";

        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setString(2, name);
            statement.setInt(3, fundedPlaces);
            statement.setInt(4, allPlaces);
            statement.setString(5, description);
            statement.setLong(6, oldFaculty_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSubjectById(long id) {
        String sql = "select * from subject_faculty where id_faculty=?;";
        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,e.getMessage(),e);
            return false;
        }
        return false;
    }

    public boolean addSubjectById(Map<String,Integer> subjects, long id){
        String sql = "INSERT INTO subject_faculty (id_faculty,name_subject,grade)\n" +
                "VALUES (?,?,?);";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBManager.connectToDB();
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
        } catch (SQLException e) {
            try {
                connection.rollback();
                logger.log(Level.WARNING,e.getMessage(),e);
                return false;
            } catch (SQLException ex) {
                logger.log(Level.WARNING,ex.getMessage(),ex);
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
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.log(Level.WARNING,ex.getMessage(),ex);
                    return false;
                }
            }

        }
        return true;
    }

    public boolean addSubjectsByIdWithDeleting(Map<String, Integer> subjects, long id) {

        String sql2 = "delete from subject_faculty where id_faculty=?";

        String sql = "INSERT INTO subject_faculty (id_faculty,name_subject,grade)\n" +
                "VALUES (?,?,?);";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBManager.connectToDB();
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
        } catch (SQLException e) {
            try {
                connection.rollback();
                logger.log(Level.WARNING,e.getMessage(),e);
                return false;
            } catch (SQLException ex) {
                logger.log(Level.WARNING,ex.getMessage(),ex);
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
            }
        }
        return true;
    }

    public Integer count(){
        Integer n = null;
        String sql = "SELECT COUNT(*) FROM faculty";
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

    public List<Entrant> entrantsOnFaculty(long id_faculty){
        List<Entrant> entrantList = new ArrayList<>();
        String sql = "select e.last_name,e.first_name,e.midle_name,e.email,avg(s.grade) \n" +
                "from entrant as e\n" +
                "join faculty_entrant as f on f.id_entrant=e.id_entrant\n" +
                "join subject_entrant as s on s.id_entrant=f.id_entrant \n" +
                "where id_faculty=?\n" +
                "group by e.id_entrant \n" +
                "order by avg(s.grade) desc;";

        try (Connection connection = DBManager.connectToDB();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id_faculty);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String surname=rs.getString(1);
                    String name=rs.getString(2);
                    String middleName=rs.getString(3);
                    String email=rs.getString(4);
                    float avgGrade=rs.getFloat(5);
                    Entrant entrant=new Entrant(surname,name,middleName,email,avgGrade);
                    entrantList.add(entrant);
                }
            }
            return entrantList;
        } catch (SQLException ex) {
            logger.log(Level.WARNING,ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }
}

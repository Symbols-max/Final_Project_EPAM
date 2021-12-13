package org.epam.final_project.service;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Subject;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ServiceEntrant extends Service{
    Integer count();
    byte[] getDiplomByEmail(String email);
    String checkStatusByEmail(String email);
    boolean deleteEntrantById(long id);
    boolean changeStatus(String status,long id_entrant);
    List<Entrant> findAllEntrants();
    List<Entrant> findAllEntrantsByStatus(String status);
    boolean checkEntrantByEmail(String email);
    boolean addEntrant(String name, String surname, String middleName, String email,
                              String city, String region, String placeEducation, InputStream diplom);
    Entrant findEntrantById(long id);
    Entrant findEntrantByEmail(String email);
    List<Subject> findSubjectById(long id);
    long findIdByEmail(String email);
    boolean addEntrantFaculty(long idEntrant,long idFaculty);
    List<Long> findFacultyById(long id);
    boolean updateEntrantInfoByEmail(String name, String surname, String middleName, String email,
                      String city, String region, String placeEducation, InputStream diplom,String oldEmail);
    boolean deleteFacultyInEntrant(long id_faculty, long id_entrant);
    boolean deleteSubjectById(long id);
    boolean checkSubjectById(long id);
    boolean addSubjectsById(Map<String, Integer> subjects, long id);
    boolean addSubjectsByIdWithDeleting(Map<String, Integer> subjects, long id);
    void deleteAll();
}

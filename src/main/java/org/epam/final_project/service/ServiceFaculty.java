package org.epam.final_project.service;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Subject;

import java.util.List;
import java.util.Map;

public interface ServiceFaculty extends Service{
    boolean changeRecruitmentStatusById(String recruitment,long id_faculty);
    List<Entrant> entrantsOnFaculty(long id_faculty);
    Integer count();
    List<Faculty> findAllFaculty();
    Faculty findFacultyById(long id);
    List<Subject> findSubjectById(long id);
    int numberOfStudentsOnFaculty(long id);
    boolean deleteFaculty(long id);
    boolean addFaculty(long id,String name,int allPlaces,int fundedPlaces,String description);
    boolean changeFaculty(long id,String name,int allPlaces,int fundedPlaces,String description,long oldFaculty_id);
    boolean checkSubjectById(long id);
    boolean addSubjectById(Map<String,Integer> subjects, long id);
    boolean addSubjectsByIdWithDeleting(Map<String,Integer> subjects, long id);
    void deleteAll();
}

package org.epam.final_project.model;

import java.util.List;
import java.util.Objects;

public class Faculty {
    private long id;
    private String nameFaculty;
    private int allPlaces;
    private int fundedPlaces;
    private String description;
    private List<Entrant> entrantList;
    private List<Subject> subjectList;
    private String recruitment;

    public Faculty(){

    }

    public Faculty(long id, String nameFaculty, int fundedPlaces, int allPlaces, List<Entrant> entrantList, List<Subject> subjectList) {
        this.id = id;
        this.nameFaculty = nameFaculty;
        this.fundedPlaces = fundedPlaces;
        this.allPlaces = allPlaces;
        this.entrantList = entrantList;
        this.subjectList = subjectList;
    }

    public Faculty(long id, String nameFaculty, int fundedPlaces, int allPlaces) {
        this.id = id;
        this.nameFaculty = nameFaculty;
        this.fundedPlaces = fundedPlaces;
        this.allPlaces = allPlaces;
    }

    public Faculty(long id, String nameFaculty, int fundedPlaces, int allPlaces,String description) {
        this.id = id;
        this.nameFaculty = nameFaculty;
        this.fundedPlaces = fundedPlaces;
        this.allPlaces = allPlaces;
        this.description=description;
    }

    public Faculty(long id, String nameFaculty, int fundedPlaces, int allPlaces,String description,String recruitment) {
        this.id = id;
        this.nameFaculty = nameFaculty;
        this.fundedPlaces = fundedPlaces;
        this.allPlaces = allPlaces;
        this.description=description;
        this.recruitment=recruitment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public List<Entrant> getEntrantList() {
        return entrantList;
    }

    public void setEntrantList(List<Entrant> entrantList) {
        this.entrantList = entrantList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public int getFundedPlaces() {
        return fundedPlaces;
    }

    public void setFundedPlaces(int fundedPlaces) {
        this.fundedPlaces = fundedPlaces;
    }

    public int getAllPlaces() {
        return allPlaces;
    }

    public void setAllPlaces(int allPlaces) {
        this.allPlaces = allPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(String recruitment) {
        this.recruitment = recruitment;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + id + '\'' +
                ", nameFaculty='" + nameFaculty +
                ", allPlaces='" + allPlaces +
                ", fundedPlaces='" + fundedPlaces +
                ", description='" + description +
                ", recruitment='" + recruitment +
                ", entrantList=" + entrantList +
                ", subjectList=" + subjectList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return allPlaces == faculty.allPlaces && fundedPlaces == faculty.fundedPlaces && Objects.equals(description, faculty.description) && Objects.equals(id, faculty.id) && Objects.equals(nameFaculty, faculty.nameFaculty) && Objects.equals(recruitment, faculty.recruitment) && Objects.equals(entrantList, faculty.entrantList) && Objects.equals(subjectList, faculty.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameFaculty, allPlaces, fundedPlaces, entrantList, subjectList,description,recruitment);
    }
}

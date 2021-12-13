package org.epam.final_project.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Entrant {
    private long id;
    private String firstName;
    private String middleName;
    private String surname;
    private String email;
    private String city;
    private String region;
    private String placeEducation;
    private String status;
    private byte[] diplom;
    private List<Faculty> facultyList;
    private List<Subject> subjectList;
    private float avgGrade;

    public Entrant(long id, String firstName, String middleName, String lastName, String email, String city, String region, String placeEducation, byte[] diplom) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = lastName;
        this.email = email;
        this.city = city;
        this.region = region;
        this.placeEducation = placeEducation;
        this.diplom = diplom;
    }

    public Entrant(long id, String firstName, String middleName, String lastName, String email, String city, String region, String placeEducation) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = lastName;
        this.email = email;
        this.city = city;
        this.region = region;
        this.placeEducation = placeEducation;
    }

    public Entrant(long id, String firstName, String middleName, String lastName, String email, String city, String region, String placeEducation, byte[] diplom,String status) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = lastName;
        this.email = email;
        this.city = city;
        this.region = region;
        this.placeEducation = placeEducation;
        this.diplom = diplom;
        this.status=status;
    }

    public Entrant(String surname,String name,String middleName,String email,float avgGrade){
        this.surname=surname;
        this.firstName=name;
        this.middleName=middleName;
        this.email=email;
        this.avgGrade=avgGrade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlaceEducation() {
        return placeEducation;
    }

    public void setPlaceEducation(String placeEducation) {
        this.placeEducation = placeEducation;
    }

    public byte[] getDiplom() {
        return diplom;
    }

    public void setDiplom(byte[] diplom) {
        this.diplom = diplom;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Entrant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", placeEducation='" + placeEducation + '\'' +
                ", status'" + status + '\'' +
                ", avgGrade'" + avgGrade + '\'' +
                ", facultyList=" + facultyList +
                ", subjectList=" + subjectList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrant entrant = (Entrant) o;
        return id == entrant.id && Float.compare(entrant.avgGrade, avgGrade) == 0 && Objects.equals(firstName, entrant.firstName) && Objects.equals(middleName, entrant.middleName) && Objects.equals(surname, entrant.surname) && Objects.equals(email, entrant.email) && Objects.equals(city, entrant.city) && Objects.equals(region, entrant.region) && Objects.equals(placeEducation, entrant.placeEducation) && Objects.equals(status, entrant.status) && Arrays.equals(diplom, entrant.diplom) && Objects.equals(facultyList, entrant.facultyList) && Objects.equals(subjectList, entrant.subjectList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, middleName, surname, email, city, region, placeEducation, status, facultyList, subjectList, avgGrade);
        result = 31 * result + Arrays.hashCode(diplom);
        return result;
    }
}

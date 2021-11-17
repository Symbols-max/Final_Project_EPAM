package org.epam.final_project.model;

import java.util.List;
import java.util.Objects;

public class Subject {
    private long id;
    private String nameSubject;
    private int grade;
    private List<Entrant> entrantList;
    private List<Faculty> facultyList;

    public Subject(){

    }

    public Subject(String nameSubject, int grade) {
        this.nameSubject = nameSubject;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public List<Entrant> getEntrantList() {
        return entrantList;
    }

    public void setEntrantList(List<Entrant> entrantList) {
        this.entrantList = entrantList;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", nameSubject='" + nameSubject + '\'' +
                ", entrantList=" + entrantList +
                ", facultyList=" + facultyList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id && grade == subject.grade && Objects.equals(nameSubject, subject.nameSubject) && Objects.equals(entrantList, subject.entrantList) && Objects.equals(facultyList, subject.facultyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSubject, grade, entrantList, facultyList);
    }
}

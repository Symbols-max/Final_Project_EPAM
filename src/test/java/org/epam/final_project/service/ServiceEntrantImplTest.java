package org.epam.final_project.service;

import org.epam.final_project.model.Entrant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ServiceEntrantImplTest {
private ServiceEntrantImpl serviceEntrant;

    public ServiceEntrantImplTest(){
        serviceEntrant=new ServiceEntrantImpl("databaseTest.properties");
    }


    @BeforeEach
    void beforeEach(){
        serviceEntrant.deleteAll();
    }

    @Test
    void getDiplomByEmail() throws IOException {
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
        }
        byte[] bytes=serviceEntrant.getDiplomByEmail("");
        Assertions.assertNotNull(bytes);
    }

    @Test
    void checkStatusByEmail() {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", null);
        String s=serviceEntrant.checkStatusByEmail("");
        Assertions.assertEquals("active",s);
    }

    @Test
    void deleteEntrantById() throws IOException{
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
        }
        Entrant entrant=serviceEntrant.findEntrantByEmail("");
        boolean b=serviceEntrant.deleteEntrantById(entrant.getId());
        Assertions.assertTrue(b);
    }

    @Test
    void changeStatus() {
        serviceEntrant.addEntrant("", "", "", "", "", "", "", null);
        String status=serviceEntrant.checkStatusByEmail("");
        Assertions.assertEquals("active",status);
        serviceEntrant.changeStatus("blocked",serviceEntrant.findIdByEmail(""));
        String s=serviceEntrant.checkStatusByEmail("");
        Assertions.assertEquals("blocked",s);

    }

    @Test
    void findAllEntrants() throws IOException{
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
        List<Entrant> list=new ArrayList<>();

        Entrant entrant=new Entrant(serviceEntrant.findIdByEmail(""),"", "",
                "", "", "", "",
                "",serviceEntrant.getDiplomByEmail(""),serviceEntrant.checkStatusByEmail(""));
        list.add(entrant);
        List<Entrant> entrantList=serviceEntrant.findAllEntrants();
        Assertions.assertArrayEquals(list.toArray(),entrantList.toArray());
        }

    }

    @Test
    void findAllEntrantsByStatus() throws IOException{
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
            List<Entrant> list=new ArrayList<>();

            Entrant entrant=new Entrant(serviceEntrant.findIdByEmail(""),"", "",
                    "", "", "", "",
                    "",serviceEntrant.getDiplomByEmail(""),serviceEntrant.checkStatusByEmail(""));
            list.add(entrant);
            List<Entrant> entrantList=serviceEntrant.findAllEntrantsByStatus("active");
            List<Entrant> entrantListBlocked=serviceEntrant.findAllEntrantsByStatus("blocked");
            Assertions.assertArrayEquals(list.toArray(),entrantList.toArray());
            Assertions.assertEquals(0, Arrays.stream(entrantListBlocked.toArray()).count());
        }
    }

    @Test
    void checkEntrantByEmail() {
        serviceEntrant.addEntrant("", "", "", "", "", "", "", null);
        boolean b=serviceEntrant.checkEntrantByEmail("");
        Assertions.assertTrue(b);
    }

    @Test
    void findEntrantByEmail() throws IOException {
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
        }
        Entrant entrant=serviceEntrant.findEntrantByEmail("e");
        Assertions.assertNull(entrant);
        Entrant entrant2=serviceEntrant.findEntrantByEmail("");
        Assertions.assertNotNull(entrant2);
    }

    @Test
    void addEntrant() {
        InputStream inputStream=null;
       boolean b=serviceEntrant.addEntrant("","","","","","","",inputStream);
        Assertions.assertTrue(b);
    }

    @Test
    void findEntrantById() throws IOException {
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
        }
        Entrant entrantById=serviceEntrant.findEntrantById(serviceEntrant.findEntrantByEmail("").getId());
        Entrant entrantByEmail=serviceEntrant.findEntrantByEmail("");
        Assertions.assertEquals(entrantByEmail,entrantById);
    }

    @Test
    void findSubjectById() {

    }

    @Test
    void findIdByEmail() {

    }

    @Test
    void findFacultyById() {
    }

    @Test
    void addEntrantFaculty() {
    }

    @Test
    void updateEntrantInfoByEmail() {
    }

    @Test
    void deleteSubjectById() {
    }

    @Test
    void addSubjectsById() {
    }

    @Test
    void addSubjectsByIdWithDeleting() {
    }

    @Test
    void checkSubjectById() {

    }

    @Test
    void count() throws IOException{
        try (InputStream inputStream = new FileInputStream(new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\test\\resources\\doPostTest.pdf"))) {
            serviceEntrant.addEntrant("", "", "", "", "", "", "", inputStream);
        int i=serviceEntrant.count();
        Assertions.assertEquals(1,i);
        }
    }
}
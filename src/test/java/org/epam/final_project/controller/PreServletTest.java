package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceFacultyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PreServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @InjectMocks
    PreServlet servlet;

    private ServiceFacultyImpl serviceFaculty;

    public PreServletTest() {
        this.serviceFaculty=new ServiceFacultyImpl("databaseTest.properties");
    }

    @BeforeEach
    public void setUp(){
        serviceFaculty.deleteAll();
        MockitoAnnotations.initMocks(this);
    }

    private String path="/pages/user/homePageServlet";


    @Test
    public void doGetTest() throws IOException, ServletException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("bundle")).thenReturn("");
        Mockito.when(session.getAttribute("role")).thenReturn("entrant");
        assertThrows(NullPointerException.class,
                ()->{
                    servlet.doGet(request,response);
                });

        assertThrows(NullPointerException.class,
                ()->{
                    verify(request,times(1)).getRequestDispatcher(path).forward(request,response);

                });
    }

}
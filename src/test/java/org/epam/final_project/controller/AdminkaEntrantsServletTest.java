//package org.epam.final_project.controller;
//
//import org.epam.final_project.service.ServiceEntrantImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//class AdminkaEntrantsServletTest {
//    @Mock
//    HttpServletRequest request;
//    @Mock
//    HttpServletResponse response;
//    @Mock
//    HttpSession session;
//
//    @InjectMocks
//    AdminkaEntrantsServlet servlet;
//
//    @Mock
//    private ServiceEntrantImpl serviceEntrant;
//
//    public AdminkaEntrantsServletTest() {
//        this.serviceEntrant=new ServiceEntrantImpl("databaseTest.properties");
//    }
//
//    @BeforeEach
//    public void setUp(){
//        serviceEntrant.deleteAll();
//        MockitoAnnotations.initMocks(this);
//    }
//
//    private String path="/pages/admin/adminkaServlet?sort=sortByName";
//    private String path2="/pages/admin/addNewFaculty.jsp";
//
//    @Test
//    public void doGetTest() {
//        ArrayList s=new ArrayList();
//                s.add(1);
//        Mockito.when(request.getSession()).thenReturn(session);
//        Mockito.when(request.getParameter("sort")).thenReturn("blockedEntrant");
//        Mockito.when(serviceEntrant.findAllEntrants()).thenReturn(new ArrayList<>());
//        Mockito.when(serviceEntrant.findAllEntrantsByStatus(eq("active"))).thenReturn(new ArrayList<>());
//        Mockito.when(serviceEntrant.findAllEntrantsByStatus(eq("blocked"))).thenReturn(s);
//        assertThrows(IllegalStateException.class,
//                ()->{
//                    servlet.doGet(request,response);
//
//                });
//
//                    verify(serviceEntrant, times(0)).findAllEntrants();
//
//                    verify(serviceEntrant, times(1)).findAllEntrantsByStatus(eq("blocked"));
//
//                    verify(serviceEntrant, times(0)).findAllEntrantsByStatus(eq("active"));
//
//    }
//}
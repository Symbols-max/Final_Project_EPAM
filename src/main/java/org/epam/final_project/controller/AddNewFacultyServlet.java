package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/pages/admin/addNewFacultyServlet")
public class AddNewFacultyServlet extends HttpServlet {
    private static final Logger logger=Logger.getLogger(AddNewFacultyServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name=request.getParameter("name");
        long id = Long.parseLong(request.getParameter("id"));
        int allPlaces = Integer.parseInt(request.getParameter("allPlaces"));
        int fundedPlaces = Integer.parseInt(request.getParameter("fundedPlaces"));

        if(fundedPlaces>allPlaces){
            String message="Не удалось добавить новый факультет.Количество бюджетных мест не должно превышать общее количество";
            request.setAttribute("message",message);
            try {
                getServletContext().getRequestDispatcher("/pages/admin/addNewFaculty.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.log(Level.WARNING,e.getMessage(),e);
                response.sendRedirect("/pages/errorPage.jsp");
            }
        }
        String description=request.getParameter("description");

        ServiceFaculty serviceFaculty=new ServiceFacultyImpl();
        boolean b=serviceFaculty.addFaculty(id,name,allPlaces,fundedPlaces,description);
        if(b!=false){
            try {
                response.sendRedirect("/pages/admin/adminkaServlet?sort=sortByName");
            } catch (IOException e) {
                logger.log(Level.WARNING,e.getMessage(),e);
                response.sendRedirect("/pages/errorPage.jsp");
            }
        }
        else{
            String message="Не удалось добавить новый факультет";
            request.setAttribute("message",message);
            try {
                getServletContext().getRequestDispatcher("/pages/admin/addNewFaculty.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.log(Level.WARNING,e.getMessage(),e);
                response.sendRedirect("/pages/errorPage.jsp");
            }
        }
    }
}

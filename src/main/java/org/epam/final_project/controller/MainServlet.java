package org.epam.final_project.controller;


import org.epam.final_project.model.Faculty;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;
import org.epam.final_project.util.Helper;
import org.epam.final_project.util.Sort;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/mainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();

        ServiceFaculty serviceFaculty = new ServiceFacultyImpl();
        List<Faculty> facultyList = serviceFaculty.findAllFaculty();
        String sort;
        if(request.getParameter("sort")==null){
            sort="";
        }
        else {
            sort = request.getParameter("sort");
        }

        session.setAttribute("sort",sort);

        if (sort.equals("sortByAllPlaces")) {
            Sort.sortByALLPlaces(facultyList);
        } else if (sort.equals("sortByBudgetPlaces")) {
            Sort.sortByBudgetPlaces(facultyList);
        } else {
            Sort.sortByName(facultyList);
        }

        int page;
        if(request.getParameter("page")==null){
            page=0;
        }
        else {
            page = Integer.parseInt(request.getParameter("page"));
        }

        Object[] faculties = Helper.pagginationHelp(page, facultyList);
        int pages = Helper.numberPages(serviceFaculty);

        request.setAttribute("pages", pages);
        request.setAttribute("faculties", faculties);

        getServletContext().getRequestDispatcher("/pages/index.jsp").forward(request, response);

    }

}

package org.epam.final_project.controller;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.service.ServiceEntrantImpl;
import org.epam.final_project.util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/pages/admin/adminkaEntrantsServlet")
public class AdminkaEntrantsServlet extends HttpServlet {
    private static final Logger log=Logger.getLogger(AdminkaEntrantsServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceEntrantImpl serviceEntrant=new ServiceEntrantImpl();
        List<Entrant> entrantList = null;

        String sort;
        if(request.getParameter("sort")==null){
            sort="";
        }
        else {
            sort = request.getParameter("sort");
        }

        HttpSession session=request.getSession();
        session.setAttribute("sort",sort);


       if (sort.equals("blockedEntrant")) {
            entrantList=serviceEntrant.findAllEntrantsByStatus("blocked");
        }
        else if(sort.equals("activeEntrant")){
            entrantList=serviceEntrant.findAllEntrantsByStatus("active");
        }
        else{
            entrantList=serviceEntrant.findAllEntrants();
        }

        int page;
        if(request.getParameter("page")==null){
            page=0;
        }
        else {
            page = Integer.parseInt(request.getParameter("page"));
        }

        Object[] entrants = Helper.pagginationHelp(page, entrantList);
        int pages = Helper.numberPages(serviceEntrant);

        request.setAttribute("pages", pages);
        request.setAttribute("entrants", entrants);

        try {
            getServletContext().getRequestDispatcher("/pages/admin/adminkaEntrant.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            log.log(Level.WARNING,e.getMessage(),e);
            response.sendRedirect("/pages/errorPage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

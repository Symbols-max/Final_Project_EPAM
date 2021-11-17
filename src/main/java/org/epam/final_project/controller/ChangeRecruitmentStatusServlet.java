package org.epam.final_project.controller;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pages/admin/changeRecruitmentStatusServlet")
public class ChangeRecruitmentStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        long id_faculty=Long.parseLong(httpServletRequest.getParameter("btnChange"));

        ServiceFaculty serviceFaculty=new ServiceFacultyImpl();
        Faculty faculty =serviceFaculty.findFacultyById(id_faculty);
        String recruitment=faculty.getRecruitment();
        HttpSession session=httpServletRequest.getSession();

        if(recruitment.equals("active")){
            boolean b=serviceFaculty.changeRecruitmentStatusById("finished",id_faculty);
            if(b==false){
                String message="Статус не был изменен.Произошла ошибка.";
                session.setAttribute("messageChange",message);
            }
        }
        else {
            boolean b=serviceFaculty.changeRecruitmentStatusById("active",id_faculty);
            if(b==false){
                String message="Статус не был изменен.Произошла ошибка.";
                session.setAttribute("messageChange",message);
            }
        }
        httpServletResponse.sendRedirect("/pages/admin/facultyInfoForAdminServlet?id="+id_faculty);
    }
}

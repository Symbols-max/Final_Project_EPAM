package org.epam.final_project.controller;

import org.epam.final_project.util.Subjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pages/user/preChangeSubjectEntrantServlet")
public class PreChangeSubjectEntrantServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Subjects[] subjects=Subjects.values();
        httpServletRequest.setAttribute("subjects",subjects);
        getServletContext().getRequestDispatcher("/pages/user/changeEntrantSubject.jsp").forward(httpServletRequest,httpServletResponse);
    }
}

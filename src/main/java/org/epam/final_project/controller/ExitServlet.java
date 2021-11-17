package org.epam.final_project.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session=httpServletRequest.getSession();
        Enumeration<String> enumeration=session.getAttributeNames();
        while (enumeration.hasMoreElements()){
            String s=enumeration.nextElement();
            if(s.equals("bundle")){
                continue;
            }
            session.removeAttribute(s);
        }
        httpServletResponse.sendRedirect("/preMainServlet");
    }
}

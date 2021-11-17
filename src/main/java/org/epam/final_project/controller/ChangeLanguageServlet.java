package org.epam.final_project.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/pages/changeLanguageServlet")
public class ChangeLanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String language=httpServletRequest.getParameter("language");
        ResourceBundle bundle = ResourceBundle.getBundle("language", new Locale(language, language.toUpperCase()));

        HttpSession session=httpServletRequest.getSession();
        session.setAttribute("bundle", bundle);

        httpServletResponse.sendRedirect("/pages/mainServlet");
    }
}

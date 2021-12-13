package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;
import org.epam.final_project.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.sql.*;

@WebServlet("/fileServlet")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String email= (String) session.getAttribute("email");
        createFile(email);
        File file = new File("C:\\JAVA_3_Course\\Final_Project_EPAM\\src\\main\\webapp\\pages\\diplom.pdf");
        response.setHeader("Content-Type",    getServletContext().getMimeType(file.getName()));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=diplom.pdf");
        Files.copy(file.toPath(), response.getOutputStream());

            }

            private void createFile(String email){
                ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
                byte[] bytes=serviceEntrant.getDiplomByEmail(email);
                try (OutputStream outputStream = new FileOutputStream(new File("C:/JAVA_3_Course/Final_Project_EPAM/src/main/webapp/pages/diplom.pdf")))
                {
                    outputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

}

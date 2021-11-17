package org.epam.final_project.controller;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Faculty;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;
import org.epam.final_project.util.Sender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pages/admin/sendToEntrantServlet")
public class SendToEntrantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id_faculty=Long.parseLong(request.getParameter("btnSend"));
        ServiceFaculty service=new ServiceFacultyImpl();

        List<Entrant> entrantList=service.entrantsOnFaculty(id_faculty);
        Object[] entrants= entrantList.toArray();
        Faculty faculty=service.findFacultyById(id_faculty);
        int fundedPlaces=faculty.getFundedPlaces();
        int allPlaces=faculty.getAllPlaces();

        String title="Письмо от Киевского Университета об результатах поступления";
        String fundedMessage="Поздравляем вы прошли на факультет на бюджетную основу.";
        String facultyMessage="Поздравляем вы прошли на факультет.";
        String badMessage="К сожалению вы не прошли на факультет.";

        Sender sender=new Sender();

        if(entrants.length<fundedPlaces){
            for (Entrant e:entrantList) {
                sender.send(fundedMessage,title,e.getEmail());
            }
        }
        else {
            for (int i = 0; i < fundedPlaces; i++) {
                Entrant entrant= (Entrant) entrants[i];
                sender.send(fundedMessage,title,entrant.getEmail());
            }
        }

        if(entrants.length>fundedPlaces) {
            if (entrants.length < allPlaces) {
                for (int i = fundedPlaces; i < entrants.length; i++) {
                    Entrant entrant = (Entrant) entrants[i];
                    sender.send(facultyMessage, title, entrant.getEmail());
                }
            } else {
                for (int i = fundedPlaces; i < allPlaces; i++) {
                    Entrant entrant = (Entrant) entrants[i];
                    sender.send(facultyMessage, title, entrant.getEmail());
                }
            }
        }


        if(entrants.length>allPlaces) {
            for (int i = allPlaces; i < entrants.length; i++) {
                Entrant entrant= (Entrant) entrants[i];
                sender.send(badMessage,title,entrant.getEmail());
            }
        }



        response.sendRedirect("/pages/admin/facultyInfoForAdminServlet?id="+id_faculty);
    }
}

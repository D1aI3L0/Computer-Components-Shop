package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.Client;
import org.example.tables.ClientDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientServlet", urlPatterns = {"/clients", "/clients/back", "/clients/listClients", "/clients/addClient", "/clients/editClient", "/clients/updateClient", "/clients/deleteClient"})
public class ClientServlet extends jakarta.servlet.http.HttpServlet {
    private final ClientDB clientDB = new ClientDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/clients/editClient":
                showEditForm(request, response);
                break;
            case "/clients/deleteClient":
                deleteClient(request, response);
                break;
            case "/clients/back":
                response.sendRedirect("/");
                break;
            default:
                listClients(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getServletPath();

        if (action.equals("/clients/addClient")) {
            addClient(request, response);
        } else if (action.equals("/clients/updateClient")) {
            updateClient(request, response);
        }
    }

    private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = clientDB.getAll();
        request.setAttribute("clients", clients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients/listClients.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Client client = clientDB.getById(id);
        request.setAttribute("client", client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients/editClient.jsp");
        dispatcher.forward(request, response);
    }

    private void addClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String phoneNumber = request.getParameter("phoneNumber");
        String emailAddress = request.getParameter("emailAddress");

        Client newClient = new Client(name, surname, patronymic, phoneNumber, emailAddress);
        clientDB.add(newClient);
        response.sendRedirect("/clients");
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String phoneNumber = request.getParameter("phoneNumber");
        String emailAddress = request.getParameter("emailAddress");

        Client client = new Client(id, name, surname, patronymic, phoneNumber, emailAddress);
        clientDB.update(client);
        response.sendRedirect("/clients");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clientDB.delete(id);
        response.sendRedirect("/clients");
    }
}
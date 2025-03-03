package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.Motherboard;
import org.example.tables.MotherboardDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MotherboardServlet", urlPatterns = {"/motherboards", "/motherboards/back", "/motherboards/listMotherboards", "/motherboards/addMotherboard", "/motherboards/editMotherboard", "/motherboards/updateMotherboard", "/motherboards/deleteMotherboard"})
public class MotherboardServlet extends HttpServlet {
    private final MotherboardDB motherboardDB = new MotherboardDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/motherboards/editMotherboard":
                showEditForm(request, response);
                break;
            case "/motherboards/deleteMotherboard":
                deleteMotherboard(request, response);
                break;
            case "/motherboards/back":
                response.sendRedirect("/");
                break;
            default:
                listMotherboards(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/motherboards/addMotherboard")) {
            addMotherboard(request, response);
        } else if (action.equals("/motherboards/updateMotherboard")) {
            updateMotherboard(request, response);
        }
    }

    private void listMotherboards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Motherboard> motherboards = motherboardDB.getAll();
        request.setAttribute("motherboards", motherboards);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/motherboards/listMotherboards.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Motherboard motherboard = motherboardDB.getById(id);
        request.setAttribute("motherboard", motherboard);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/motherboards/editMotherboard.jsp");
        dispatcher.forward(request, response);
    }

    private void addMotherboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int memorySlots = Integer.parseInt(request.getParameter("memorySlots"));
        String chipset = request.getParameter("chipset");
        String formFactor = request.getParameter("formFactor");

        Motherboard newMotherboard = new Motherboard(memorySlots, chipset, formFactor);
        motherboardDB.add(newMotherboard);
        response.sendRedirect("/motherboards");
    }

    private void updateMotherboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int memorySlots = Integer.parseInt(request.getParameter("memorySlots"));
        String chipset = request.getParameter("chipset");
        String formFactor = request.getParameter("formFactor");

        Motherboard motherboard = new Motherboard(id, memorySlots, chipset, formFactor);
        motherboardDB.update(motherboard);
        response.sendRedirect("/motherboards");
    }

    private void deleteMotherboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        motherboardDB.delete(id);
        response.sendRedirect("/motherboards");
    }
}
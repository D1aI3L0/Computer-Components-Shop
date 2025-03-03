package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.GraphicCard;
import org.example.tables.GraphicCardDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GraphicCardServlet", urlPatterns = {"/graphicCards", "graphicCards/back", "/graphicCards/listGraphicCards", "/graphicCards/addGraphicCard", "/graphicCards/editGraphicCard", "/graphicCards/updateGraphicCard", "/graphicCards/deleteGraphicCard"})
public class GraphicCardServlet extends HttpServlet {
    private final GraphicCardDB graphicCardDB = new GraphicCardDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/graphicCards/editGraphicCard":
                showEditForm(request, response);
                break;
            case "/graphicCards/deleteGraphicCard":
                deleteGraphicCard(request, response);
                break;
            case "/graphicCards/back":
                response.sendRedirect("/");
                break;
            default:
                listGraphicCards(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/graphicCards/addGraphicCard")) {
            addGraphicCard(request, response);
        } else if (action.equals("/graphicCards/updateGraphicCard")) {
            updateGraphicCard(request, response);
        }
    }

    private void listGraphicCards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GraphicCard> graphicCards = graphicCardDB.getAll();
        request.setAttribute("graphicCards", graphicCards);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/graphicCards/listGraphicCards.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GraphicCard graphicCard = graphicCardDB.getById(id);
        request.setAttribute("graphicCard", graphicCard);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/graphicCards/editGraphicCard.jsp");
        dispatcher.forward(request, response);
    }

    private void addGraphicCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int gpuCount = Integer.parseInt(request.getParameter("gpuCount"));
        double gpuFrequency = Double.parseDouble(request.getParameter("gpuFrequency"));
        int memoryCount = Integer.parseInt(request.getParameter("memoryCount"));
        double memoryFrequency = Double.parseDouble(request.getParameter("memoryFrequency"));

        GraphicCard newGraphicCard = new GraphicCard(gpuCount, gpuFrequency, memoryCount, memoryFrequency);
        graphicCardDB.add(newGraphicCard);
        response.sendRedirect("/graphicCards");
    }

    private void updateGraphicCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int gpuCount = Integer.parseInt(request.getParameter("gpuCount"));
        double gpuFrequency = Double.parseDouble(request.getParameter("gpuFrequency"));
        int memoryCount = Integer.parseInt(request.getParameter("memoryCount"));
        double memoryFrequency = Double.parseDouble(request.getParameter("memoryFrequency"));

        GraphicCard graphicCard = new GraphicCard(id, gpuCount, gpuFrequency, memoryCount, memoryFrequency);
        graphicCardDB.update(graphicCard);
        response.sendRedirect("/graphicCards");
    }

    private void deleteGraphicCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        graphicCardDB.delete(id);
        response.sendRedirect("/graphicCards");
    }
}
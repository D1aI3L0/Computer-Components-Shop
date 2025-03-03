package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.Processor;
import org.example.tables.ProcessorDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProcessorServlet", urlPatterns = {"/processors", "/processors/back", "/processors/listProcessors", "/processors/addProcessor", "/processors/editProcessor", "/processors/updateProcessor", "/processors/deleteProcessor"})
public class ProcessorServlet extends HttpServlet {
    private final ProcessorDB processorDB = new ProcessorDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/processors/editProcessor":
                showEditForm(request, response);
                break;
            case "/processors/deleteProcessor":
                deleteProcessor(request, response);
                break;
            case "/processors/back":
                response.sendRedirect("/");
                break;
            default:
                listProcessors(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();

        if (action.equals("/processors/addProcessor")) {
            addProcessor(request, response);
        } else if (action.equals("/processors/updateProcessor")) {
            updateProcessor(request, response);
        }
    }

    private void listProcessors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Processor> processors = processorDB.getAll();
        request.setAttribute("processors", processors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/processors/listProcessors.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Processor processor = processorDB.getById(id);
        request.setAttribute("processor", processor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/processors/editProcessor.jsp");
        dispatcher.forward(request, response);
    }

    private void addProcessor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int threadsCount = Integer.parseInt(request.getParameter("threadsCount"));
        double clockFrequency = Double.parseDouble(request.getParameter("clockFrequency"));
        double maxFrequency = Double.parseDouble(request.getParameter("maxFrequency"));
        int cpuCount = Integer.parseInt(request.getParameter("cpuCount"));

        Processor newProcessor = new Processor(threadsCount, clockFrequency, maxFrequency, cpuCount);
        processorDB.add(newProcessor);
        response.sendRedirect("/processors");
    }

    private void updateProcessor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int threadsCount = Integer.parseInt(request.getParameter("threadsCount"));
        double clockFrequency = Double.parseDouble(request.getParameter("clockFrequency"));
        double maxFrequency = Double.parseDouble(request.getParameter("maxFrequency"));
        int cpuCount = Integer.parseInt(request.getParameter("cpuCount"));

        Processor processor = new Processor(id, threadsCount, clockFrequency, maxFrequency, cpuCount);
        processorDB.update(processor);
        response.sendRedirect("/processors");
    }

    private void deleteProcessor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        processorDB.delete(id);
        response.sendRedirect("/processors");
    }
}
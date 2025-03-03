package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.Order;
import org.example.tables.OrderDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = {"/orders", "/orders/back", "/orders/listOrders", "/orders/addOrder", "/orders/editOrder", "/orders/updateOrder", "/orders/deleteOrder"})
public class OrderServlet extends HttpServlet {
    private final OrderDB orderDB = new OrderDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/orders/editOrder":
                showEditForm(request, response);
                break;
            case "/orders/deleteOrder":
                deleteOrder(request, response);
                break;
            case "/orders/back":
                response.sendRedirect("/");
                break;
            default:
                listOrders(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();

        if (action.equals("/orders/addOrder")) {
            addOrder(request, response);
        } else if (action.equals("/orders/updateOrder")) {
            updateOrder(request, response);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderDB.getAll();
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/orders/listOrders.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderDB.getById(id);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/orders/editOrder.jsp");
        dispatcher.forward(request, response);
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String orderDate = request.getParameter("orderDate");
        String status = request.getParameter("status");
        String paymentMethod = request.getParameter("paymentMethod");
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        Order newOrder = new Order(totalPrice, orderDate, status, paymentMethod, clientId);
        orderDB.add(newOrder);
        response.sendRedirect("/orders");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String orderDate = request.getParameter("orderDate");
        String status = request.getParameter("status");
        String paymentMethod = request.getParameter("paymentMethod");
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        Order order = new Order(id, totalPrice, orderDate, status, paymentMethod, clientId);
        orderDB.update(order);
        response.sendRedirect("/orders");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderDB.delete(id);
        response.sendRedirect("/orders");
    }
}
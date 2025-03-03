package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.ProductOrder;
import org.example.tables.ProductOrderDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductOrderServlet", urlPatterns = {"/productOrders", "/productOrders/back", "/productOrders/listProductOrders", "/productOrders/addProductOrder", "/productOrders/editProductOrder", "/productOrders/updateProductOrder", "/productOrders/deleteProductOrder"})
public class ProductOrderServlet extends HttpServlet {
    private final ProductOrderDB productOrderDB = new ProductOrderDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/productOrders/editProductOrder":
                showEditForm(request, response);
                break;
            case "/productOrders/deleteProductOrder":
                deleteProductOrder(request, response);
                break;
            case "/productOrders/back":
                response.sendRedirect("/");
                break;
            default:
                listProductOrders(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();

        if (action.equals("/productOrders/addProductOrder")) {
            addProductOrder(request, response);
        } else if (action.equals("/productOrders/updateProductOrder")) {
            updateProductOrder(request, response);
        }
    }

    private void listProductOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductOrder> productOrders = productOrderDB.getAll();
        request.setAttribute("productOrders", productOrders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/productOrders/listProductOrders.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductOrder productOrder = productOrderDB.getById(id);
        request.setAttribute("productOrder", productOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/productOrders/editProductOrder.jsp");
        dispatcher.forward(request, response);
    }

    private void addProductOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        ProductOrder newProductOrder = new ProductOrder(productId, orderId);
        productOrderDB.add(newProductOrder);
        response.sendRedirect("/productOrders");
    }

    private void updateProductOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        ProductOrder productOrder = new ProductOrder(id, productId, orderId);
        productOrderDB.update(productOrder);
        response.sendRedirect("/productOrders");
    }

    private void deleteProductOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productOrderDB.delete(id);
        response.sendRedirect("/productOrders");
    }
}
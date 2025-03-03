package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.Product;
import org.example.tables.ProductDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products", "/products/back", "/products/listProducts", "/products/addProduct", "/products/editProduct", "/products/updateProduct", "/products/deleteProduct"})
public class ProductServlet extends HttpServlet {
    private final ProductDB productDB = new ProductDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/products/editProduct":
                showEditForm(request, response);
                break;
            case "/products/deleteProduct":
                deleteProduct(request, response);
                break;
            case "/products/back":
                response.sendRedirect("/");
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();

        if (action.equals("/products/addProduct")) {
            addProduct(request, response);
        } else if (action.equals("/products/updateProduct")) {
            updateProduct(request, response);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDB.getAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/products/listProducts.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDB.getById(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/products/editProduct.jsp");
        dispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        int processorId;
        if(request.getParameter("processorId").isEmpty())
            processorId = 0;
        else
            processorId = Integer.parseInt(request.getParameter("processorId"));
        int motherboardId;
        if(request.getParameter("motherboardId").isEmpty())
            motherboardId = 0;
        else
            motherboardId = Integer.parseInt(request.getParameter("motherboardId"));
        int graphicCardId;
        if(request.getParameter("graphicCardId").isEmpty())
            graphicCardId = 0;
        else
            graphicCardId = Integer.parseInt(request.getParameter("graphicCardId"));

        Product newProduct = new Product(name, manufacturer, type, price, processorId, motherboardId, graphicCardId);
        productDB.add(newProduct);
        response.sendRedirect("/products");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        int processorId;
        if(request.getParameter("processorId").isEmpty())
            processorId = 0;
        else
            processorId = Integer.parseInt(request.getParameter("processorId"));
        int motherboardId;
        if(request.getParameter("motherboardId").isEmpty())
            motherboardId = 0;
        else
            motherboardId = Integer.parseInt(request.getParameter("motherboardId"));
        int graphicCardId;
        if(request.getParameter("graphicCardId").isEmpty())
            graphicCardId = 0;
        else
            graphicCardId = Integer.parseInt(request.getParameter("graphicCardId"));

        Product product = new Product(id, name, manufacturer, type, price, processorId, motherboardId, graphicCardId);
        productDB.update(product);
        response.sendRedirect("/products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDB.delete(id);
        response.sendRedirect("/products");
    }
}
package org.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.example.models.Review;
import org.example.tables.ReviewDB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewServlet", urlPatterns = {"/reviews", "/reviews/back", "/reviews/listReviews", "/reviews/addReview", "/reviews/editReview", "/reviews/updateReview", "/reviews/deleteReview"})
public class ReviewServlet extends HttpServlet {
    private final ReviewDB reviewDB = new ReviewDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/reviews/editReview":
                showEditForm(request, response);
                break;
            case "/reviews/deleteReview":
                deleteReview(request, response);
                break;
            case "/reviews/back":
                response.sendRedirect("/");
                break;
            default:
                listReviews(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();

        if (action.equals("/reviews/addReview")) {
            addReview(request, response);
        } else if (action.equals("/reviews/updateReview")) {
            updateReview(request, response);
        }
    }

    private void listReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Review> reviews = reviewDB.getAll();
        request.setAttribute("reviews", reviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reviews/listReviews.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Review review = reviewDB.getById(id);
        request.setAttribute("review", review);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reviews/editReview.jsp");
        dispatcher.forward(request, response);
    }

    private void addReview(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        String reviewText = request.getParameter("review");

        Review newReview = new Review(clientId, productId, reviewText);
        reviewDB.add(newReview);
        response.sendRedirect("/reviews");
    }

    private void updateReview(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        String reviewText = request.getParameter("review");

        Review review = new Review(id, clientId, productId, reviewText);
        reviewDB.update(review);
        response.sendRedirect("/reviews");
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        reviewDB.delete(id);
        response.sendRedirect("/reviews");
    }
}
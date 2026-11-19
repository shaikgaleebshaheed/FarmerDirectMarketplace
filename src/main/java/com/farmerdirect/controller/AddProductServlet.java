package com.farmerdirect.controller;

import com.farmerdirect.dao.ProductDAO;
import com.farmerdirect.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.html"
            );

            return;
        }

        int farmerId =
                (Integer) session.getAttribute("userId");

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price =
                Double.parseDouble(request.getParameter("price"));
        int quantity =
                Integer.parseInt(request.getParameter("quantity"));
        String description =
                request.getParameter("description");

        Product product = new Product(
                farmerId,
                name,
                category,
                price,
                quantity,
                description
        );

        boolean added = productDAO.addProduct(product);

        if (added) {

            response.sendRedirect(
                    request.getContextPath() + "/products"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/add-product.html?error=1"
            );
        }
    }
}
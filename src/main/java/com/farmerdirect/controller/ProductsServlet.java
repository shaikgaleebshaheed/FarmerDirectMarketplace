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
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {

        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.html"
            );

            return;
        }

        List<Product> products =
                productDAO.getAllProducts();

        request.setAttribute(
                "products",
                products
        );

        request.getRequestDispatcher(
                "/products.jsp"
        ).forward(request, response);
    }
}
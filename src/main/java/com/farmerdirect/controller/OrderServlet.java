package com.farmerdirect.controller;

import com.farmerdirect.dao.OrderDAO;
import com.farmerdirect.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAO();
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

        int customerId =
                (int) session.getAttribute("userId");

        int productId =
                Integer.parseInt(
                        request.getParameter("productId")
                );

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity")
                );

        double price =
                Double.parseDouble(
                        request.getParameter("price")
                );

        double totalPrice = price * quantity;

        Order order = new Order(
                customerId,
                productId,
                quantity,
                totalPrice,
                "Pending"
        );

        boolean ordered = orderDAO.placeOrder(order);

        if (ordered) {

            response.sendRedirect(
                    request.getContextPath() + "/orders"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/products"
            );
        }
    }
}
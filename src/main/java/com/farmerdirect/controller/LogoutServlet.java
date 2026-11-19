package com.farmerdirect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String role = null;

        if (session != null) {

            role = (String) session.getAttribute("role");

            session.invalidate();
        }

        if ("farmer".equalsIgnoreCase(role)) {

            response.sendRedirect(
                    request.getContextPath() + "/farmer.html"
            );

        } else if ("customer".equalsIgnoreCase(role)) {

            response.sendRedirect(
                    request.getContextPath() + "/customer.html"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/home.html"
            );
        }
    }
}
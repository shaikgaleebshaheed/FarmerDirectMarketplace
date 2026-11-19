package com.farmerdirect.controller;

import com.farmerdirect.dao.UserDAO;
import com.farmerdirect.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(
                name,
                email,
                password,
                role
        );

        boolean registered = userDAO.registerUser(user);

        if (registered) {

            response.sendRedirect(
                    request.getContextPath() + "/login.html"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/register.html?error=1"
            );
        }
    }
}
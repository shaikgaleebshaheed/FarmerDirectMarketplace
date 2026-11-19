package com.farmerdirect.controller;

import com.farmerdirect.dao.UserDAO;
import com.farmerdirect.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {

        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String role =
                request.getParameter("role");


        /*
         * Check role
         */

        if (role == null ||
                (!role.equals("farmer") &&
                        !role.equals("customer"))) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.html?error=role"
            );

            return;
        }


        /*
         * Login user
         */

        User user =
                userDAO.loginUser(
                        email,
                        password
                );


        /*
         * Check user
         */

        if (user != null) {

            /*
             * Check selected role matches
             * registered role
             */

            if (!user.getRole().equalsIgnoreCase(role)) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/login.html?role="
                                + role
                                + "&error=1"
                );

                return;
            }


            /*
             * Create session
             */

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "user",
                    user
            );

            session.setAttribute(
                    "userId",
                    user.getId()
            );

            session.setAttribute(
                    "userName",
                    user.getName()
            );

            session.setAttribute(
                    "role",
                    user.getRole()
            );


            /*
             * Redirect according to role
             */

            response.sendRedirect(
                    request.getContextPath()
                            + "/home.html?role="
                            + role
            );

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.html?role="
                            + role
                            + "&error=1"
            );
        }
    }
}
package com.farmerdirect.dao;

import com.farmerdirect.model.User;
import com.farmerdirect.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean registerUser(User user) {

        String sql =
                "INSERT INTO users " +
                        "(name, email, password, role) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(
                    1,
                    user.getName()
            );

            preparedStatement.setString(
                    2,
                    user.getEmail()
            );

            preparedStatement.setString(
                    3,
                    user.getPassword()
            );

            preparedStatement.setString(
                    4,
                    user.getRole()
            );

            int rows =
                    preparedStatement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    public User loginUser(
            String email,
            String password) {

        String sql =
                "SELECT * FROM users " +
                        "WHERE email = ? AND password = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(
                    1,
                    email
            );

            preparedStatement.setString(
                    2,
                    password
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(
                        resultSet.getInt("id")
                );

                user.setName(
                        resultSet.getString("name")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPassword(
                        resultSet.getString("password")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    public User loginUser(
            String email,
            String password,
            String role) {

        String sql =
                "SELECT * FROM users " +
                        "WHERE email = ? " +
                        "AND password = ? " +
                        "AND role = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(
                    1,
                    email
            );

            preparedStatement.setString(
                    2,
                    password
            );

            preparedStatement.setString(
                    3,
                    role
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(
                        resultSet.getInt("id")
                );

                user.setName(
                        resultSet.getString("name")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPassword(
                        resultSet.getString("password")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
package com.farmerdirect.dao;

import com.farmerdirect.model.Order;
import com.farmerdirect.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public boolean placeOrder(Order order) {

        String sql = "INSERT INTO orders " +
                "(customer_id, product_id, quantity, total_price, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getProductId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setDouble(4, order.getTotalPrice());
            preparedStatement.setString(5, order.getStatus());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getOrdersByCustomer(int customerId) {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE customer_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, customerId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                Order order = new Order();

                order.setId(resultSet.getInt("id"));
                order.setCustomerId(
                        resultSet.getInt("customer_id")
                );
                order.setProductId(
                        resultSet.getInt("product_id")
                );
                order.setQuantity(
                        resultSet.getInt("quantity")
                );
                order.setTotalPrice(
                        resultSet.getDouble("total_price")
                );
                order.setStatus(
                        resultSet.getString("status")
                );

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}
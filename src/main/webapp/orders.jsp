<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.farmerdirect.model.Order" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>My Orders - Farmer Direct</title>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;

            background:
                    linear-gradient(
                            135deg,
                            #e8f5e9,
                            #c8e6c9
                    );
        }

        nav {
            height: 70px;

            background: #2e7d32;

            display: flex;

            align-items: center;

            justify-content: space-between;

            padding: 0 50px;

            color: white;
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
        }

        .nav-links {
            display: flex;
            gap: 30px;
        }

        .nav-links a {
            color: white;

            text-decoration: none;

            font-weight: bold;
        }

        .nav-links a:hover {
            text-decoration: underline;
        }

        .container {
            width: 90%;

            max-width: 1000px;

            margin: 50px auto;
        }

        h1 {
            text-align: center;

            color: #2e7d32;

            margin-bottom: 10px;
        }

        .subtitle {
            text-align: center;

            color: #666;

            margin-bottom: 30px;
        }

        .order-card {
            background: white;

            padding: 25px;

            margin-bottom: 20px;

            border-radius: 15px;

            box-shadow:
                    0 8px 20px
                    rgba(0, 0, 0, 0.12);
        }

        .order-card h2 {
            color: #2e7d32;

            margin-bottom: 15px;
        }

        .order-card p {
            margin: 8px 0;

            color: #555;
        }

        .status {
            display: inline-block;

            padding: 6px 12px;

            background: #fff3cd;

            color: #856404;

            border-radius: 15px;

            font-weight: bold;
        }

        .empty {
            background: white;

            padding: 40px;

            text-align: center;

            border-radius: 15px;

            box-shadow:
                    0 8px 20px
                    rgba(0, 0, 0, 0.12);
        }

        .empty h2 {
            color: #2e7d32;

            margin-bottom: 15px;
        }

        .button {
            display: inline-block;

            margin-top: 20px;

            padding: 12px 22px;

            background: #43a047;

            color: white;

            text-decoration: none;

            border-radius: 8px;

            font-weight: bold;
        }

        .button:hover {
            background: #2e7d32;
        }

    </style>

</head>

<body>

<nav>

    <div class="logo">
        🌾 Farmer Direct
    </div>

    <div class="nav-links">

        <a href="home.html">
            Home
        </a>

        <a href="products">
            Products
        </a>

        <%
            String role =
                    (String) session.getAttribute("role");
        %>

        <% if ("Farmer".equalsIgnoreCase(role)) { %>

            <a href="add-product.html">
                Add Product
            </a>

        <% } %>

        <a href="orders">
            My Orders
        </a>

        <a href="logout">
            Logout
        </a>

    </div>

</nav>


<div class="container">

    <h1>
        📦 My Orders
    </h1>

    <p class="subtitle">
        View your orders and order status
    </p>


    <%

        List<Order> orders =
                (List<Order>)
                        request.getAttribute("orders");

        if (orders != null && !orders.isEmpty()) {

            for (Order order : orders) {

    %>


    <div class="order-card">

        <h2>
            Order #<%= order.getId() %>
        </h2>

        <p>

            <strong>
                Product ID:
            </strong>

            <%= order.getProductId() %>

        </p>

        <p>

            <strong>
                Quantity:
            </strong>

            <%= order.getQuantity() %>

        </p>

        <p>

            <strong>
                Total Price:
            </strong>

            ₹<%= order.getTotalPrice() %>

        </p>

        <p>

            <strong>
                Status:
            </strong>

            <span class="status">

                <%= order.getStatus() %>

            </span>

        </p>

    </div>


    <%

            }

        } else {

    %>


    <div class="empty">

        <h2>
            🛒 No Orders Yet
        </h2>

        <p>
            You haven't placed any orders yet.
        </p>

        <a href="products"
           class="button">

            View Products

        </a>

    </div>


    <%

        }

    %>

</div>

</body>

</html>
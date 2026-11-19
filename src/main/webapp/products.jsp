<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.farmerdirect.model.Product" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Products - Farmer Direct Marketplace</title>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(
                    135deg,
                    #e8f5e9,
                    #c8e6c9
            );
        }

        nav {
            background: #2e7d32;
            padding: 18px 50px;

            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            color: white;
            font-size: 24px;
            font-weight: bold;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin-left: 25px;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 40px auto;
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

        .products {
            display: grid;
            grid-template-columns:
                    repeat(auto-fit, minmax(250px, 1fr));

            gap: 25px;
        }

        .product-card {
            background: white;
            padding: 25px;
            border-radius: 15px;

            box-shadow:
                    0 5px 15px
                    rgba(0, 0, 0, 0.1);
        }

        .product-icon {
            text-align: center;
            font-size: 45px;
            margin-bottom: 15px;
        }

        .product-card h2 {
            color: #2e7d32;
            margin-bottom: 10px;
        }

        .category {
            display: inline-block;

            background: #e8f5e9;
            color: #2e7d32;

            padding: 5px 10px;

            border-radius: 15px;

            font-size: 13px;

            margin-bottom: 12px;
        }

        .price {
            color: #1b5e20;

            font-size: 20px;

            font-weight: bold;

            margin-bottom: 8px;
        }

        .quantity {
            color: #555;

            margin-bottom: 10px;
        }

        .description {
            color: #777;

            line-height: 1.5;
        }

        .buy-form {
            margin-top: 20px;
        }

        .buy-form input[type="number"] {

            width: 80px;

            padding: 10px;

            border: 1px solid #ccc;

            border-radius: 8px;

            margin-right: 10px;
        }

        .buy-form button {

            padding: 10px 18px;

            border: none;

            border-radius: 8px;

            background: #43a047;

            color: white;

            font-weight: bold;

            cursor: pointer;
        }

        .buy-form button:hover {
            background: #2e7d32;
        }

        .empty {

            background: white;

            padding: 40px;

            text-align: center;

            border-radius: 15px;

            color: #777;
        }

        .add-link {

            text-align: center;

            margin-top: 30px;
        }

        .add-link a {

            display: inline-block;

            padding: 12px 25px;

            background: #43a047;

            color: white;

            text-decoration: none;

            border-radius: 8px;

            font-weight: bold;
        }

        .add-link a:hover {
            background: #2e7d32;
        }

    </style>

</head>

<body>

<%
    String role =
            (String) session.getAttribute("role");

    boolean isFarmer =
            "farmer".equalsIgnoreCase(role);

    boolean isCustomer =
            "customer".equalsIgnoreCase(role);
%>

<nav>

    <div class="logo">
        🌾 Farmer Direct
    </div>

    <div>

        <a href="home.html">
            Home
        </a>

        <a href="products">
            Products
        </a>

        <% if (isFarmer) { %>

            <a href="add-product.html">
                Add Product
            </a>

        <% } %>

        <% if (isCustomer) { %>

            <a href="orders">
                My Orders
            </a>

        <% } %>

        <a href="logout">
            Logout
        </a>

    </div>

</nav>

<div class="container">

    <h1>
        🌾 Available Products
    </h1>

    <p class="subtitle">
        Fresh products directly from farmers
    </p>

    <%
        List<Product> products =
                (List<Product>)
                        request.getAttribute("products");
    %>

    <% if (products != null && !products.isEmpty()) { %>

        <div class="products">

            <% for (Product product : products) { %>

                <div class="product-card">

                    <div class="product-icon">
                        🌱
                    </div>

                    <h2>
                        <%= product.getName() %>
                    </h2>

                    <span class="category">
                        <%= product.getCategory() %>
                    </span>

                    <div class="price">
                        ₹<%= product.getPrice() %>
                    </div>

                    <div class="quantity">

                        Available Quantity:
                        <%= product.getQuantity() %>

                    </div>

                    <div class="description">

                        <%= product.getDescription() %>

                    </div>


                    <% if (isCustomer) { %>

                        <!-- BUY PRODUCT - CUSTOMER ONLY -->

                        <form
                                class="buy-form"
                                action="<%= request.getContextPath() %>/order"
                                method="post">

                            <input
                                    type="hidden"
                                    name="productId"
                                    value="<%= product.getId() %>">

                            <input
                                    type="hidden"
                                    name="price"
                                    value="<%= product.getPrice() %>">

                            <input
                                    type="number"
                                    name="quantity"
                                    min="1"
                                    max="<%= product.getQuantity() %>"
                                    value="1"
                                    required>

                            <button type="submit">
                                🛒 Buy Now
                            </button>

                        </form>

                    <% } %>


                </div>

            <% } %>

        </div>

    <% } else { %>

        <div class="empty">

            <h2>
                No products available
            </h2>

            <p>
                Farmers have not added any products yet.
            </p>

        </div>

    <% } %>


    <% if (isFarmer) { %>

        <div class="add-link">

            <a href="add-product.html">
                + Add Product
            </a>

        </div>

    <% } %>


</div>

</body>

</html>
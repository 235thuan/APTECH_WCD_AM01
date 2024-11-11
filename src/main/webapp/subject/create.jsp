<%@ page import="com.example.t2303e_wcd.entity.Student" %>
<%@ page import="com.example.t2303e_wcd.entity.StudentClass" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Class</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <!-- Custom CSS for styling similar to Student Listing -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .container {
            margin-top: 30px;
        }

        h1 {
            color: #343a40;
            font-size: 2.5rem;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-size: 1.1rem;
            margin-bottom: 15px;
            display: inline-block;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Styling for the form and form elements */
        .form-control {
            font-size: 1rem;
            padding: 10px;
            border-radius: 0.375rem;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            font-size: 1.1rem;
            padding: 10px 20px;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        /* Styling for the form container */
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .alert {
            margin-bottom: 20px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add New Subject</h1>

    <!-- Display error message if there's one -->
    <c:if test="${not empty message}">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </c:if>

    <!-- Form to create a new student -->
    <form action="/T2303E_WCD_war/subject/create" method="POST">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="hours" class="form-label">Hours</label>
            <input type="number" class="form-control" id="hours" name="hours" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Subject</button>
    </form>

    <br>
    <a href="/T2303E_WCD_war/student">Back to Student List</a>
</div>
</body>
</html>

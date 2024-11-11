<%@ page import="com.example.t2303e_wcd.entity.Student" %>
<%@ page import="com.example.t2303e_wcd.entity.StudentClass" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Listing</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <!-- Custom CSS for styling -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 30px;
        }

        h1 {
            color: #343a40;
            font-size: 2.5rem;
            margin-bottom: 20px;
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

        /* Button Styling */
        .btn-primary {
            font-size: 1.1rem;
            padding: 10px 20px;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .btn-primary:hover {
            background-color: #0056b3;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Student List</h1>
    <!-- Link to Add New Student -->
    <a href="student/create" class="btn btn-primary">Add New Student</a>
    <a href="class/create" class="btn btn-primary">Add New Class</a>
    <a href="subject/create" class="btn btn-primary">Add New Subject</a>

    <!-- Table to display the student list with Bootstrap's table-striped and table-hover -->
    <table class="table table-striped table-hover table-info">
        <thead class="table-primary">
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Class</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Assuming "students" is the list of student objects you have in your request attribute.
            for(Student s : (List<Student>)request.getAttribute("students")) {
        %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getAddress() %></td>
            <td><%= s.getTelephone() %></td>
            <td><%= s.getStudentClass() != null ? s.getStudentClass().getName() : "N/A" %></td>
            <td>
                <form action="/T2303E_WCD_war/student/update" method="get" style="display: inline;">
                    <input type="hidden" name="id" value="<%= s.getId() %>">
                    <input type="hidden" name="name" value="<%= s.getName() %>">
                    <input type="hidden" name="email" value="<%= s.getEmail() %>">
                    <input type="hidden" name="address" value="<%= s.getAddress() %>">
                    <input type="hidden" name="telephone" value="<%= s.getTelephone() %>">
                    <button type="submit" class="btn btn-warning btn-sm">Update</button>
                </form>
                <form action="/T2303E_WCD_war/student/delete" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= s.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?');">
                        Delete
                    </button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>

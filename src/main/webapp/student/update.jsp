<%@ page import="com.example.t2303e_wcd.entity.Student" %>
<%@ page import="com.example.t2303e_wcd.entity.StudentClass" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Update Student</h1>
    <a href="/T2303E_WCD_war/student">Back to Student List</a>
    <form action="<%= request.getContextPath() + "/student/update" %>" method="post">
        <input type="hidden" name="id" value="${student.id}" /> <!-- Hidden input for student ID -->
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${student.name}" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${student.email}" required>
        </div>

        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" name="address" value="${student.address}" required>
        </div>

        <div class="mb-3">
            <label for="telephone" class="form-label">Telephone</label>
            <input type="text" class="form-control" id="telephone" name="telephone" value="${student.telephone}" required>
        </div>

        <div class="mb-3">
            <label for="class_id" class="form-label">Class</label>
            <select class="form-control" id="class_id" name="class_id">
                <option value="">Select Class</option>
                <%
                    Student student = (Student) request.getAttribute("student");
                %>
                <%
                    // Assuming "studentClasses" is a list of StudentClass objects passed as a request attribute
                    List<StudentClass> studentClasses = (List<StudentClass>) request.getAttribute("studentClasses");
                    if (studentClasses != null) {
                        for (StudentClass sc : studentClasses) {
                            boolean isSelected = student != null && student.getStudentClass() != null && student.getStudentClass().getId() == sc.getId();
                %>
                <option value="<%= sc.getId() %>" <%= isSelected ? "selected" : "" %>><%= sc.getName() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>

    </form>
</div>
</body>
</html>

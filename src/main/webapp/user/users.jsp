<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Users</h1>

        <table>
            <tr>
                <td>name</td>
                <td>birthday</td>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.birthday}</td>
                </tr>
            </c:forEach>
        </table>
        
        <a href="add">new</a>
        
    </body>
</html>

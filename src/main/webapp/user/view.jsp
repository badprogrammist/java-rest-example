<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>New user</h1>
        <f:form method="POST" commandName="currentUser" action="add">
            <p>Name:</p>
            <p><f:input path="name"/></p>
            <input type="submit"/>               

        </f:form>
    </body>
</html>

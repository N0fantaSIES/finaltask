<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form action="registerServlet">
    <fmt:message key="register_page.jsp_login" bundle="${rb}"/> <input required name="login" />
    <br><br>
    <fmt:message key="register_page.jsp_password" bundle="${rb}"/> <input required type="password" name="password" />
    <br><br>
    <fmt:message key="register_page.jsp_name" bundle="${rb}"/> <input required name="name" />
    <br><br>
    <fmt:message key="register_page.jsp_surname" bundle="${rb}"/> <input required name="surname" />
    <br><br>
    <input type="submit" value= "<fmt:message key="register_page.jsp_button_next" bundle="${rb}"/>" />
</form>
</body>
</html>

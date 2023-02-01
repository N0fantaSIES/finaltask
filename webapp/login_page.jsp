<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="loginServlet">
    <fmt:message key="login_page.jsp_login" bundle="${rb}"/> <input required name="login"/>

    <fmt:message key="login_page.jsp_password" bundle="${rb}"/> <input required type="password" name="password"/>
    <input type="submit" value="<fmt:message key="login_page.jsp_button_enter" bundle="${rb}"/>"/>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User_Form</title>
</head>
<h2>
    <fmt:message key="main.jsp_head_welcome" bundle="${rb}"/>
</h2>

<h3>
    <fmt:message key="main.jsp_head_description" bundle="${rb}"/>
</h3>
<body>

<form action="login_page.jsp">
    <input type="submit" value="<fmt:message key="main.jsp_button_login" bundle="${rb}"/>" />
</form>
<form action="register_page.jsp">
    <input type="submit" value="<fmt:message key="main.jsp_button_register" bundle="${rb}"/>" />
</form>

</body>
</html>
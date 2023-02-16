<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form id="register_form" action="controller?command=registerCommand" method="post">

    <fmt:message key="register_page.jsp_login" bundle="${rb}"/> <input required name="login"/>

    <fmt:message key="register_page.jsp_password" bundle="${rb}"/> <input required type="password" name="password"/>

    <fmt:message key="register_page.jsp_name" bundle="${rb}"/> <input required name="name"/>

    <fmt:message key="register_page.jsp_surname" bundle="${rb}"/> <input required name="surname"/>

    <input type="submit" value="<fmt:message key="register_page.jsp_button_next" bundle="${rb}"/>"/>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="login_form" action="controller?command=loginCommand" method="post">
    <fieldset>
        <fmt:message key="login_page.jsp_login" bundle="${rb}"/> <input required name="login"/>
    </fieldset>
    <fieldset>
    <fmt:message key="login_page.jsp_password" bundle="${rb}"/> <input required type="password" name="password"/>
    </fieldset>
    <input type="submit" value="<fmt:message key="login_page.jsp_button_enter" bundle="${rb}"/>"/>
</form>
</body>
</html>

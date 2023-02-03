<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form id="register_form" action="controller?command=registerCommand" method="post">
    <fieldset>
        <fmt:message key="register_page.jsp_login" bundle="${rb}"/> <input required name="login"/>
    </fieldset>
    <fieldset>
        <fmt:message key="register_page.jsp_password" bundle="${rb}"/> <input required type="password" name="password"/>
    </fieldset>
    <fieldset>
        <fmt:message key="register_page.jsp_name" bundle="${rb}"/> <input required name="name"/>
    </fieldset>
    <fieldset>
        <fmt:message key="register_page.jsp_surname" bundle="${rb}"/> <input required name="surname"/>
    </fieldset>
    <input type="submit" value="<fmt:message key="register_page.jsp_button_next" bundle="${rb}"/>"/>
</form>
</body>
</html>

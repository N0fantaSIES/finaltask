<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>
<form id="show_all_meetings_user_form" action="controller?command=userTableCommand" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_users.jsp_button_show_all_meetings" bundle="${rb}"/>" />
</form>
<br><br>
<form id="show_user_meetings_form" action="controller?command=participationMeetingUserPage" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_users.jsp_button_my_meetings" bundle="${rb}"/>" />
</form>
</body>
</html>

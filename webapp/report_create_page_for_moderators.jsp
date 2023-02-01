<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="reportCreationServlet">
    <fmt:message key="report_create_page_for_moderators.jsp_title_of_report" bundle="${rb}"/> <input required name="title_of_report" />
    <br><br>
    <input type="submit" value="<fmt:message key="report_create_page_for_moderators.jsp_button_creat_new_report" bundle="${rb}"/>" />
    <input type="hidden" name="id" value="${id}"/>
</form>
</body>
</html>

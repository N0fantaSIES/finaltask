<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>
<form action="speakerReportTableServlet">
    <input type="submit" value="<fmt:message key="working_page_for_speakers.jsp_button_my_reports" bundle="${rb}"/>" />
</form>
<br><br>
<form action="speakerTableServlet">
    <input type="submit" value="<fmt:message key="working_page_for_speakers.jsp_button_show_all_available_reports" bundle="${rb}"/>" />
</form>
</body>
</html>

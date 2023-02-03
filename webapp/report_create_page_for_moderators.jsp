<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="create_report_moder_form" action="controller?command=moderatorReportCreationCommand" method="post">
    <fmt:message key="report_create_page_for_moderators.jsp_title_of_report" bundle="${rb}"/> <input required name="title_of_report" />
    <input type="submit" value="<fmt:message key="report_create_page_for_moderators.jsp_button_creat_new_report" bundle="${rb}"/>" />
    <input type="hidden" name="id" value="${id}"/>
</form>
</body>
</html>

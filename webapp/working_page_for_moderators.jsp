<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="meeting_creation_page_for_moderators.jsp">
    <input type="submit" value="<fmt:message key="working_page_for_moderators.jsp_button_create_new_meeting" bundle="${rb}"/>" />
</form>

<form id="show_all_meetings_moder_form" action="controller?command=moderatorTableCommand" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_moderators.jsp_button_show_all_meetings" bundle="${rb}"/>" />
</form>

<form id="show_all_reports_moder_form" action="controller?command=moderReportTableCommand" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_moderators.jsp_button_show_all_reports" bundle="${rb}"/>" />
</form>

<form id="show_all_participants_moder_form" action="controller?command=moderatorCountParticipantsTable" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_moderators.jsp_button_show_all_participants_of_meetings" bundle="${rb}"/>" />
</form>
</body>
</html>

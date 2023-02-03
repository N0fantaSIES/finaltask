<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>
<form id="show_speaker_reports_form" action="controller?command=speakerReportTableCommand" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_speakers.jsp_button_my_reports" bundle="${rb}"/>" />
</form>
<br><br>
<form id="show_all_available_reports_speaker_form" action="controller?command=speakerTableCommand" method="post">
    <input type="submit" value="<fmt:message key="working_page_for_speakers.jsp_button_show_all_available_reports" bundle="${rb}"/>" />
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <th> <fmt:message key="available_report_show_page_for_speaker.jsp_table_header_name" bundle="${rb}"/></th>
        <th><fmt:message key="available_report_show_page_for_speaker.jsp_table_header_topic" bundle="${rb}"/></th>
        <th><fmt:message key="available_report_show_page_for_speaker.jsp_table_header_creator_name" bundle="${rb}"/></th>
        <th><fmt:message key="available_report_show_page_for_speaker.jsp_table_header_date" bundle="${rb}"/></th>
        <th><fmt:message key="available_report_show_page_for_speaker.jsp_table_header_report_name" bundle="${rb}"/></th>
    </tr>
    <c:forEach var="list" items="${reportList}">
        <tr>
            <td>${list.meeting_name}</td>
            <td>${list.meeting_topic}</td>
            <td>${list.meeting_creator_name}</td>
            <td>${list.meeting_date}</td>
            <td>${list.title_of_report}</td>
            <td> <a href = "<c:url value = "/controller?command=reassignSpeakerCommand&id=${list.id}"/>">
                <input type="submit" value="<fmt:message key="available_report_show_page_for_speaker.jsp_button_take_report" bundle="${rb}"/>" /></a> <td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

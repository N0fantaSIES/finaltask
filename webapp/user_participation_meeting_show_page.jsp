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
        <th><fmt:message key="user_participation_meeting_show_page.jsp_table_header_name" bundle="${rb}"/></th>
        <th><fmt:message key="user_participation_meeting_show_page.jsp_table_header_topic" bundle="${rb}"/></th>
        <th><fmt:message key="user_participation_meeting_show_page.jsp_table_header_date" bundle="${rb}"/></th>
        <th><fmt:message key="user_participation_meeting_show_page.jsp_table_header_creator_name" bundle="${rb}"/></th>
    </tr>
    <c:forEach var="list" items="${meetingArrayList}">
        <tr>
            <td>${list.name}</td>
            <td>${list.topic}</td>
            <td>${list.date}</td>
            <td>${list.creator_name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

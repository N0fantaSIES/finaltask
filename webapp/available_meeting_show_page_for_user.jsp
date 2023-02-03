<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<table>
    <tr>
        <th><fmt:message key="available_meeting_show_page_for_user.jsp_table_header_name" bundle="${rb}"/></th>
        <th><fmt:message key="available_meeting_show_page_for_user.jsp_table_header_topic" bundle="${rb}"/></th>
        <th><fmt:message key="available_meeting_show_page_for_user.jsp_table_header_date" bundle="${rb}"/></th>
        <th><fmt:message key="available_meeting_show_page_for_user.jsp_table_header_creator_name" bundle="${rb}"/></th>
    </tr>
    <c:forEach var="list" items="${meetingList}">
        <tr>
            <td>${list.name}</td>
            <td>${list.topic}</td>
            <td>${list.date}</td>
            <td>${list.creator_name}</td>
            <td><a href="<c:url value = "/controller?command=userMeetingParticipation&id=${list.id}"/>">
                <input type="submit" value="<fmt:message key="available_meeting_show_page_for_user.jsp_button_participate" bundle="${rb}"/>"/>
            </a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:set var="k" value="0" />

<%
    int splitNumber = (int) request.getAttribute("split");
    for (int i = 0; i < splitNumber; i++) { %>
        <c:set var="k" value="${k+1}" />
        <a href="<c:url value = "/controller?command=userTableCommand&pagNumber=${k}"/>"><%=i+1%></a>
<%}%>
</body>
</html>

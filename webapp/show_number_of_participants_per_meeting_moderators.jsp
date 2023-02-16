<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

<c:set var="k" value="1" />

<table>
  <tr>
    <th><fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_name" bundle="${rb}"/></th>
    <th><fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_topic" bundle="${rb}"/></th>
    <th><fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_creator_name" bundle="${rb}"/></th>
    <th><fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_number_of_participants_will_physically_attend" bundle="${rb}"/></th>

    <th><a href="<c:url value = "/controller?command=meetingSortingForModeratorsCommand&sortingIndex=date&pagNumber=${page}"/>">
      <fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_date" bundle="${rb}"/></a></th>

    <th><a href="<c:url value = "/controller?command=meetingSortingForModeratorsCommand&sortingIndex=numberOfParticipants&pagNumber=${page}"/>">
      <fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_number_of_participants" bundle="${rb}"/></a></th>

    <th><a href="<c:url value = "/controller?command=meetingSortingForModeratorsCommand&sortingIndex=numberOfReports&pagNumber=${page}"/>">
      <fmt:message key="show_number_of_participants_per_meeting_moderators.jsp_table_header_moderator_number_of_reports" bundle="${rb}"/></a></th>
  </tr>
  <c:forEach var="list" items="${meetingList}">
    <tr>
      <td>${list.name}</td>
      <td>${list.topic}</td>
      <td>${list.creator_name}</td>
      <td>${list.number_of_participants_who_came}</td>
      <td>${list.date}</td>
      <td>${list.getNumber_of_participants_who_came()}</td>
      <td>${list.number_of_reports}</td>
    </tr>
  </c:forEach>
</table>

<c:set var="k" value="0" />

<%
  int splitNumber = (int) request.getAttribute("split");
  for (int i = 0; i < splitNumber; i++) { %>
<c:set var="k" value="${k+1}" />
<a href="<c:url value = "/controller?command=moderatorCountParticipantsTable&pagNumber=${k}"/>"><%=i+1%></a>
<%}%>

</body>
</html>
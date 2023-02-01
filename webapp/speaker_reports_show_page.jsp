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
    <th><fmt:message key="speaker_reports_show_page.jsp_table_header_report_name" bundle="${rb}"/></th>
    <th><fmt:message key="speaker_reports_show_page.jsp_table_header_meeting_name" bundle="${rb}"/></th>
  </tr>
  <c:forEach var="list" items="${reportArrayList}">
    <tr>
      <td>${list.title_of_report}</td>
      <td>${list.meeting_name}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>

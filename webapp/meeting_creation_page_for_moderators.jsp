<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="meetingServlet">
  <fmt:message key="meeting_creation_page_for_moderators.jsp_moder_name" bundle="${rb}"/> <input required name="name" />

  <fmt:message key="meeting_creation_page_for_moderators.jsp_moder_topic" bundle="${rb}"/> <input required name="topic" />

  <input type="submit" value="<fmt:message key="meeting_creation_page_for_moderators.jsp_moder_button_create" bundle="${rb}"/>" />
</form>
</body>
</html>

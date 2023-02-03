<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="meeting_creation_form" action="controller?command=createMeetingModeratorCommand" method="post">

    <fmt:message key="meeting_creation_page_for_moderators.jsp_moder_name" bundle="${rb}"/> <input required
                                                                                                   name="name"/>

    <fmt:message key="meeting_creation_page_for_moderators.jsp_moder_topic" bundle="${rb}"/> <input required
                                                                                                    name="topic"/>

    <fmt:message key="meeting_creation_page_for_moderators.jsp_moder_how_many_participants_will_physically_attend"
                 bundle="${rb}"/> <input required type="number" name="participantsNumber"/>

    <fmt:message key="meeting_creation_page_for_moderators.jsp_moder_date" bundle="${rb}"/> <input required
                                                                                                   type="date"
                                                                                                   name="date"/>

    <input type="submit"
           value="<fmt:message key="meeting_creation_page_for_moderators.jsp_moder_button_create" bundle="${rb}"/>"/>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytld" uri="/WEB-INF/custom.tld" %>

<link rel="stylesheet" type="text/css" href="style/st4.css"/>

<div id="header">

    <form id="localForm" action="languageSwitcherServlet">
        <div id="leftLocal">
            <c:choose>
                <c:when test="${newLocale == 'en'}">
                    <input type="submit" value="UK">
                    <input type="hidden" name="newLocal" value="uk">
                    <label> / EN</label>
                </c:when>
                <c:when test="${newLocale == 'uk'}">
                    <label>UK / </label>
                    <input type="submit" value="EN"/>
                    <input type="hidden" name="newLocal" value="en"/>
                </c:when>
                <c:otherwise>
                    <input type="submit" value="UK">
                    <input type="hidden" name="newLocal" value="uk">
                    <label> / EN</label>
                </c:otherwise>
            </c:choose>
            <mytld:role user="${mainUser}"/>
        </div>
    </form>


    <div id="leftHeader">

        <fmt:setLocale value="${newLocale}" scope="session"/>
        <fmt:setBundle var="rb" basename="lang"/>

        <c:choose>
<%--            moderator--%>
            <c:when test="${mainUser.role_id == 1 }">
                <form action="meeting_creation_page_for_moderators.jsp">
                    <input type="submit" value="<fmt:message key="header.jsp_button_moder_create_new_meeting" bundle="${rb}"/>" />
                </form>
                <form action="moderatorTableServlet">
                    <input type="submit" value="<fmt:message key="header.jsp_button_moder_show_all_meetings" bundle="${rb}"/>" />
                </form>
                <form action="moderReportTableServlet">
                    <input type="submit" value="<fmt:message key="header.jsp_button_moder_show_all_reports" bundle="${rb}"/>" />
                </form>
                <form action="countParticipantsTable">
                    <input type="submit" value="<fmt:message key="header.jsp_button_moder_show_all_participants_of_meeting" bundle="${rb}"/>" />
                </form>
            </c:when>
<%--            guest--%>
            <c:when test="${mainUser.role_id == 2 }">
                <form action="userTableServlet">
                    <input type="submit" value="<fmt:message key="header.jsp_button_guest_show_all_meetings" bundle="${rb}"/>" />
                </form>
                <form action="participationMeetingUserPage">
                    <input type="submit" value="<fmt:message key="header.jsp_button_guest_show_my_meetings" bundle="${rb}"/>" />
                </form>
            </c:when>
<%--            speaker--%>
            <c:when test="${mainUser.role_id == 3 }">
                <form action="speakerReportTableServlet">
                    <input type="submit" value="<fmt:message key="header.jsp_button_speaker_show_my_reports" bundle="${rb}"/>" />
                </form>
                <form action="speakerTableServlet">
                    <input type="submit" value="<fmt:message key="header.jsp_button_speaker_show_all_available_reports" bundle="${rb}"/>" />
                </form>
            </c:when>

        </c:choose>

    </div>

    <div id="rightHeader">
        <form action="deloginingServlet">
            <input type="submit" value="<fmt:message key="header.jsp_button_moder_leave" bundle="${rb}"/>" />
        </form>
    </div>

</div>

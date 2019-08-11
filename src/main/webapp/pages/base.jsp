<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.07.2019
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="${bundle}"/>

    <title>Base</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<ul class="menu">
    <li><a href="/"><img style="position: relative" height="20.75px"
                src="https://png2.kisspng.com/sh/8581204998f3db2695f56a76cda73f17/L0KzQYm3V8A0N6l1h5H0aYP2gLBuTfNwdaF6jNd7LXnmf7B6Tf1ib5JBgdDuLXnmf7A0VfI0apVmSagEY3a7RIe1WccyQGk5Sqs6NUOzRoWCUcExPmU4RuJ3Zx==/kisspng-computer-icons-magazine-icon-5b3bda169cf846.971884291530649110643.png">
    </a></li>
    <li><a href="/mySubscriptions"><fmt:message key="label.mainMenu.mySubscriptions"/></a></li>
    <li><a href="/subscribe"><fmt:message key="label.mainMenu.subscribe"/></a></li>
    <li><a href="/cart"><img height="15px" src="https://png2.kisspng.com/sh/cf6cca13a209854bdab8e8d5d88e3bae/L0KzQYi4UsE4N5Q1fJGAYUO5RLOAVcJkOGc4S5CEOUi3Q4a7UsE2OWQ6SKkCMEG0SIG6TwBvbz==/5a364b752c0633.9984354215135077011803.png">
        <fmt:message key="label.mainMenu.cart"/></a></li>
    <c:choose>
        <c:when test="${user.getFirstName() != null}">
            <li><a href="/userPage"><fmt:message key="label.mainMenu.userPage"/></a></li>
            <li><a href="/signOut"><fmt:message key="label.mainMenu.signOut"/> </a></li>
        </c:when>
        <c:otherwise>
            <li><a href="/authorisation"><fmt:message key="label.mainMenu.signIn"/></a></li>
            <li><a href="/registration"><fmt:message key="label.mainMenu.signUp"/> </a></li>
        </c:otherwise>
    </c:choose>
    <li>
    <form method="get">
    <select name="locale" style="margin: 20px 20px; float: right" onchange="this.form.submit()">
        <option value="en" ${locale eq 'en' ? 'selected' : ''}>EN</option>
        <option value="ru" ${locale eq 'ru' ? 'selected' : ''}>RU</option>
    </select>
    </form>
    </li>
</ul>
</body>
</html>

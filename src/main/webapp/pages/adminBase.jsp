<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2019
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<html>
<head>
    <title>AdminBase</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<ul class="menu">
    <li><a href="/adminMain">
        <i><fmt:message key="label.adminMenu.title"/> </i>
        <img style="position: relative" height="17px"
                src="https://png2.kisspng.com/sh/76d6a4387f5d45505177f29778f87d76/L0KzQYm3V8EzN5D7gJH0aYP2gLBuTgdzcaVuhtk2aX7pf7j5ggBpcZQyjARydHX1Pbn2jfV4d6NwRdd8c3H8PbLrjflvNZpoh9C2NXK3RrfqVMU6QGk5SqY3OUK8RYGAV8IyPWQ2S6kDN0W6RoO7V75xdpg=/kisspng-writing-infographic-writer-homework-essay-admin-icon-5b46fc45988424.9295077215313787576247.png">
    </a></li>
    <li><a href="/adminEditions"><fmt:message key="label.adminMenu.editions"/></a></li>
    <li><a href="/adminPayments"><fmt:message key="label.adminMenu.payments"/></a></li>
    <li><a href="/signOut"><fmt:message key="label.mainMenu.signOut"/></a></li>
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

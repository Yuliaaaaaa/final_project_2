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
                src="https://png2.kisspng.com/sh/ac958b1f951b567689686f98535bd33e/L0KzQYm3V8E2N6VmiJH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfVva5J1iAd1YYTodH73jCN1e5R3geJ9LXewg8bwlPUua51uRdNtbXnxPbrqjB4uPZM5edY5NkC8coK8UskvPWcATqICN0i0RYS4VsI6Pmc5TqUAMj7zfri=/kisspng-computer-icons-encapsulated-postscript-g-suite-cli-admin-icon-5b4ad0609b1529.5696077815316296646352.png">
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

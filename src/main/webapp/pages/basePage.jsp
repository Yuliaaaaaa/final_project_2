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
    <li><a><img style="position: relative" height="20.75px"
                src="https://png2.kisspng.com/sh/f8c25c656b2cb78a12f1c3e51e1bcc43/L0KzQYm3U8I5N6RAfZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6Tf1ib5JBgdDuLXTyh7BzjBFlNZ5mf9NFaX7oPYbogvJlOWRpTtRrYUO7PoGCUMQ1PmY5Sac7MkK4SIO6V8Q1OWQziNDw/kisspng-computer-icons-magazine-download-magazine-5abbd13d6bba38.0904465415222582374413.png">
    </a></li>
    <li><a href="#"><fmt:message key="label.mainMenu.mySubscriptions"/></a></li>
    <li><a href="#"><fmt:message key="label.mainMenu.subscribe"/></a></li>
    <li><a href="#"><img height="15px" src="https://png2.kisspng.com/sh/3d6a31c725a41ae4b0c574a170d51850/L0KzQYm3U8I4N6FuiZH0aYP2gLBuTgNpd6F1gdDwLXPkgsW0kB9nfKhmitc2b37vebBsTgNpd6F1gdDwLXPyfcH8lPVzNZpoRdNtZD33f37qggJ1NZN6jOZ4bj24cbOCg8hjaWY5StdvOT60QIe4VMc4P2I6SqQ6MkS8SIe6VMQ5NqFzf3==/kisspng-shopping-cart-software-online-shopping-computer-ic-add-to-cart-button-5ab9c8ba542ef9.1061477715221249863448.png">
        <fmt:message key="label.mainMenu.cart"/></a></li>
    <c:choose>
        <c:when test="${user.getFirstName() != null}">
            <li><a href="/userPage"><fmt:message key="label.mainMenu.userPage"/></a></li>
            <li><a href="/authorisation"><fmt:message key="label.mainMenu.signOut"/> </a></li>
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

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
    <%--<fmt:setBundle basename="glossary"/>
    <fmt:setLocale value="ru"/>--%>
    <fmt:setBundle basename="glossary"/>
    <fmt:setLocale value="${locale}"/>
    <title>Base</title>
    <style>
        body {
           /* background-image: url("http://bgfons.com/upload/paper_texture313.jpg");*/
            background-repeat: no-repeat;
            background-position: center;
            background-position: top;
            background-color: azure;
            background-size: auto;
        }

        .menu {
            font-family: 'Bookman Old Style', sans-serif;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            cursor: pointer;
            font-size: 18px;
            margin: 20px 150px 0px;
            padding: 0;
        }

        ul {
            background: #ffdda1;
                        box-shadow: 0px 1px 1px #bdc3c7;
            height: 60px;
            margin: 0 0 0px;
            width: 77%;
            display: block;
        }

        /* list */
        ul li {
            display: inline-block;
            float: left;
        }

        /* anchor */
        ul li a {
            color: darkblue;
            display: block;
            text-decoration: none;
            padding: 19.5px 10px;
            position: relative;
        }

        /* anchor line */
        ul li a:after {
            background: #203549;
            content: '';
            display: inline-block;
            height: 2px;
            margin: auto;
            position: absolute;;
            bottom: -0px;
            left: 0;
            right: 0;
            transition: all 300ms;
            width: 0%;
        }

        /* active */
        ul li.active a {
            font-weight: 600;
        }

        /* hover */
        ul li a:hover,
        ul li.active a {
            color: #203549;
        }

        /* active border */
        ul li a:hover:after,
        ul li.active a:after {
            width: 100%;
        }

        .box{
            width: 750px;
            height: 390px;
            background-image: url("https://png2.kisspng.com/sh/b60f76c1c6bac7f05c4e13c0e6a1fc6f/L0KzQYm3UcI5N5DufZH0aYP2gLBuTgBieJZ3RetubHzyh37ujBxlNZRmitZrb3H1dH73ggBmel46eahtY0i1Q7aBVMM5OV83Sqc7MkO3RoK8UccyPGU1UasENUG3PsH1h5==/kisspng-paper-yellow-gold-cardboard-paper-5a6dc823e84381.2252234615171440999514.png");
            background-size: 650px;
            padding: 40px 70px;
            margin: 20px 290px 0px;
            background-repeat: no-repeat;
            font-family: 'Bookman Old Style', sans-serif;
            font-size: 18px;
        }
        select#locale option[value="EN"] {
            background-image:url("https://png2.kisspng.com/sh/d93903efb9f54e071bc7e6157f06b16e/L0KzQYm3VMEzN5pvj5H0aYP2gLBuTfZtaZgyh9g2dHjoPcb1igRmbF54jNN9ZYOwdr3oh71wbl5oedDqZHGwdrr5kCQue6VqRed8YT3pfLLuTcVia5c9SNQBOHXkc7eCTsk0PGI4Sao9MUW1Q4a7WMM1OmY9TKY3cH7q/kisspng-flag-of-the-united-states-flag-of-canada-first-ste-usa-flag-5acf80b68eacf9.9341318415235483425844.png");
            background-size: 5px;
        }
        select#locale option[value="RU"] {
            background-image:url("https://png2.kisspng.com/sh/d93903efb9f54e071bc7e6157f06b16e/L0KzQYm3VMEzN5pvj5H0aYP2gLBuTfZtaZgyh9g2dHjoPcb1igRmbF54jNN9ZYOwdr3oh71wbl5oedDqZHGwdrr5kCQue6VqRed8YT3pfLLuTcVia5c9SNQBOHXkc7eCTsk0PGI4Sao9MUW1Q4a7WMM1OmY9TKY3cH7q/kisspng-flag-of-the-united-states-flag-of-canada-first-ste-usa-flag-5acf80b68eacf9.9341318415235483425844.png");
            background-size: 5px;
        }

        hr{
            width: 500px;
            position: relative;
        }

        h3 {
            font-family: 'Bookman Old Style', serif;
            font-size: 20px;
            font-weight: bold;
        }

        .btn {
            border: none;
            display: block;
            text-align: center;
            cursor: pointer;
            outline: none;
            overflow: hidden;
            position: relative;
            color: darkblue;
            font-weight: 600;
            font-size: 15px;
            background-color: transparent;
            padding: 10px 20px;
            margin: 0 auto;
            box-shadow: 0 5px 15px rgba(0,0,0,0.20);
        }

        .btn span {
            position: relative;
            z-index: 1;
        }

        .btn:after {
            content: "";
            position: absolute;
            left: 0;
            top: 0;
            height: 490%;
            width: 140%;
            background: tan;
            -webkit-transition: all .5s ease-in-out;
            transition: all .5s ease-in-out;
            -webkit-transform: translateX(-99%) translateY(-50%) rotate(45deg);
            transform: translateX(-99%) translateY(-50%) rotate(45deg);
        }

        .btn:hover:after {
            -webkit-transform: translateX(-9%) translateY(-25%) rotate(45deg);
            transform: translateX(-9%) translateY(-25%) rotate(45deg);
        }

        .error {
            color: firebrick;
            font-family: "Courier New";
            font-size: 12px;
            font-weight: lighter;
        }

    </style>
</head>
<body>
<ul class="menu">
    <li><a><img style="position: relative" height="20.75px"
                src="https://png2.kisspng.com/sh/f8c25c656b2cb78a12f1c3e51e1bcc43/L0KzQYm3U8I5N6RAfZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6Tf1ib5JBgdDuLXTyh7BzjBFlNZ5mf9NFaX7oPYbogvJlOWRpTtRrYUO7PoGCUMQ1PmY5Sac7MkK4SIO6V8Q1OWQziNDw/kisspng-computer-icons-magazine-download-magazine-5abbd13d6bba38.0904465415222582374413.png">
    </a></li>
    <li><a href="#"><fmt:message key="label.mainMenu.mySubscriptions"/></a></li>
    <li><a href="#"><fmt:message key="label.mainMenu.subscribe"/></a></li>
    <li><a href="#"><fmt:message key="label.mainMenu.cart"/> </a></li>
    <c:choose>
        <c:when test="${user.getFirstName() != null}">
            <li><a href="/userPage"><fmt:message key="label.mainMenu.userPage"/></a></li>
            <li><a href="/authorisation"><fmt:message key="label.mainMenu.signOut"/> </a></li>
        </c:when>
        <c:otherwise>
            <li><a href="/authorisation"><fmt:message key="label.mainMenu.signIn"/></a></li>
            <li><a href="#"><fmt:message key="label.mainMenu.signUp"/> </a></li>
        </c:otherwise>
    </c:choose>
    <select id="locale" style="margin: 20px 20px; float: right">
        <option>EN</option>
        <option>RU</option>
    </select>
</ul>
</body>
</html>

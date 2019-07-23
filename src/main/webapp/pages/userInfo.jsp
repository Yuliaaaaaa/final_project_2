<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.07.2019
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='basePage.jsp'/>


<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>UserInfo</title>
    <style>
        .sexPicture {
            height: 250px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.userPage"/></h3>
    <hr align="left">
    <table>
        <tr>
            <td>
                <c:if test="${user.getSex() eq 'm'.charAt(0)}">
                    <img class="sexPicture" style="padding-right: 30px"
                         src="https://png2.kisspng.com/sh/9ba778b90367f1dff73946ee478cf267/L0KzQYm3U8I6N5RriZH0aYP2gLBuTfJmaaNpReVybHjyhbb7lPUuepDAed59eT3pgrbsTfJmaaNpRdN3ZD3wf8b6lPFkcJYyTdNrY3TkSILshPE4bGgzSKoDM0W8Roi4VcIzO2M7SaYAOUe2RHB3jvc=/kisspng-beard-silhouette-royalty-free-beard-and-moustache-5abcda81eda7d7.0883596715223261459734.png">
                </c:if>
                <c:if test="${user.getSex() eq 'f'.charAt(0)}">
                    <img class="sexPicture" style="margin-left: -50px;"
                         src="https://png2.kisspng.com/sh/589aeedff0500a3162b63a495954e20e/L0KzQYm3VMExN5lxj5H0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfhicaN4jOt1ZT3rcbr5TfNiepYyj9H2ZX6weLLwkr02aZRofKk5ZUnndYW9Wb42OWQ5S6o6NEG4QoS6V8M5O2k7TKgCLoDxd1==/kisspng-computer-icons-hairstyle-hair-care-women-hair-5accd70e9de469.5134381415233738386467.png">
                </c:if>
            </td>
            <td align="right">
                <fmt:message key="userInfo.firstName"/>: <br>
                <fmt:message key="userInfo.lastName"/>: <br>
                <fmt:message key="userInfo.birthDate"/>: <br>
                <fmt:message key="label.authorisation.email"/>: <br>
                <c:if test="${user.getPhoneNumber() != 0}">
                    <fmt:message key="userInfo.phoneNumber"/>: <br>
                </c:if>
            </td>
            <td>
                <c:out value="${user.getFirstName()}"/> <br>
                <c:out value="${user.getLastName()}"/> <br>
                <c:out value="${user.getBirthDate()}"/> <br>
                <c:out value="${user.getEmail()}"/> <br>
                <c:if test="${user.getPhoneNumber() != 0}">
                    <c:out value="${user.getPhoneNumber()}"/> <br>
                </c:if>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

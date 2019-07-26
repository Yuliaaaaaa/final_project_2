<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.07.2019
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='adminPage.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>AddEdition</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.adminMenu.editions.add"/></h3><hr>
    <form id="submitForm" method="post">
    <table>
        <tr>
            <td valign="top">
                <fmt:message key="label.editions.name"/>:<br><br>
                <fmt:message key="label.editions.category"/>:<br><br>
                <fmt:message key="label.editions.periodicity"/>: <br><br>
                <fmt:message key="label.editions.price"/>:<br><br>
                <fmt:message key="label.editions.details"/>:
            </td>
            <td>
                <input type="text" name="title" value="${title}"><br><br>
                <select name="category">
                    <option ${category eq 'agriculture' ? 'selected' : ''} value="agriculture">
                        <fmt:message key="label.editions.category.agriculture"/></option>
                    <option ${category eq 'building' ? 'selected' : ''} value="building">
                        <fmt:message key="label.editions.category.building"/></option>
                    <option ${category eq 'economics' ? 'selected' : ''} value="economics">
                        <fmt:message key="label.editions.category.economics"/></option>
                    <option ${category eq 'fashion' ? 'selected' : ''} value="fashion">
                        <fmt:message key="label.editions.category.fashion"/></option>
                    <option ${category eq 'industry' ? 'selected' : ''} value="industry">
                        <fmt:message key="label.editions.category.industry"/></option>
                    <option ${category eq 'gardening' ? 'selected' : ''} value="gardening">
                        <fmt:message key="label.editions.category.gardening"/></option>
                    <option ${category eq 'science' ? 'selected' : ''} value="science">
                        <fmt:message key="label.editions.category.science"/></option>
                    <option ${category eq 'medicine' ? 'selected' : ''} value="medicine">
                        <fmt:message key="label.editions.category.medicine"/></option>
                    <option ${category eq 'newspaper' ? 'selected' : ''} value="newspaper">
                        <fmt:message key="label.editions.category.newspaper"/></option>
                    <option ${category eq 'mind-breaker' ? 'selected' : ''} value="mind-breaker">
                        <fmt:message key="label.editions.category.mindBreaker"/></option>
                    <option ${category eq 'for children' ? 'selected' : ''} value="for children">
                        <fmt:message key="label.editions.category.forChildren"/></option>
                    <option ${category eq 'for men' ? 'selected' : ''} value="for men">
                        <fmt:message key="label.editions.category.forMen"/></option>
                    <option ${category eq 'for women' ? 'selected' : ''} value="for women">
                        <fmt:message key="label.editions.category.forWomen"/></option>
                </select><br><br>
                <select name="periodicity">
                    <option ${periodicity eq 'daily' ? 'selected' : ''} value="daily">
                        <fmt:message key="label.editions.periodicity.daily"/></option>
                    <option ${periodicity eq 'weekly' ? 'selected' : ''} value="weekly">
                        <fmt:message key="label.editions.periodicity.weekly"/></option>
                    <option ${periodicity eq 'monthly' ? 'selected' : ''} value="monthly">
                        <fmt:message key="label.editions.periodicity.monthly"/></option>
                </select><br><br>
                <input type="number" step="0.1" min="0" name="price" value="${price}"><br><br>
                <textarea name="details" cols="30" rows="3"><c:out value="${details}"/></textarea>
            </td>
        </tr>
    </table>
        <c:if test="${fail}">
            <p class="error"><fmt:message key="error.wrongInput.allFieldsRequired"/></p>
        </c:if>
    <br><br>
    <button class="btn" onclick="this.form.submit()"><fmt:message key="label.adminMenu.editions.add"/></button>
    </form>
</div>
</body>
</html>

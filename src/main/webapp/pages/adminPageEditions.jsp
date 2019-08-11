<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2019
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='adminBase.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>AdminEditions</title>
    <script>
        <%@include file="../scripts/openMenu.js" %>
    </script>
</head>
<body>
<div class="box">
    <span style="position: fixed; left: 600px">
    <c:if test="${deleted}">
        <p style="color: cadetblue"><fmt:message key="label.adminMenu.editions.deleteSuccess"/></p>
    </c:if>
    <c:if test="${success}">
        <p style="color: cadetblue; float: bottom"><fmt:message key="label.adminMenu.editions.addSuccess"/></p>
    </c:if></span>
    <h3><fmt:message key="label.adminMenu.editions"/>
        <a href="/addEdition"><img height="15px"
                                   src="https://png2.kisspng.com/sh/2416bf5fed82f07ed916625ec9d4591a/L0KzQYm3WMEyN5RBR91yc4Pzfri0gBxqeF5miuY2dnXmhLF5TfdzaaFtgdV8LXHwdcPwgBFvNaNqfJ9scnB2g37wjPx2e6UyiN5Acz3mecPqjPUudZDzh59yY3BxPbf5hfUud5cyi9DycHnmf7B6Tf1wdpCyTdQBZkTlcofoVfE4OWozUKsBMES7RIG4VcM1OGM1TaU9Nke7RXB3jvc=/kisspng-clip-art-vector-graphics-american-red-cross-illust-plus-circle-mono-icon-free-of-snipicons-mono-5b6f4bb6a5a719.8960484015340205346785.png">
        </a></h3>
    <hr>
    <c:if test="${editions.size() == 0}">
        <fmt:message key="label.adminMenu.editions.emptyList"/>
    </c:if>
    <c:if test="${editions.size() > 0}">
        <ul class="table-fill">
            <li class="header">
                <div class="col" style="width: 40%"><fmt:message key="label.editions.name"/></div>
                <div class="col" style="width: 35%"><fmt:message key="label.editions.category"/></div>
                <div class="col" style="width: 15%"><fmt:message key="label.editions.price"/></div>
                <div class="col" style="width: 10%"></div>
            </li>
            <c:forEach items="${editions}" var="edition" varStatus="loop">
                <li onclick="openMenu(${loop.index})">
                    <div class="col" style="width: 40%">${edition.getEditionTitle()}</div>
                    <div class="col" style="width: 35%">
                        <c:choose>
                            <c:when test="${edition.getCategory() eq 'medicine'}">
                                <fmt:message key="label.editions.category.medicine"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'science'}">
                                <fmt:message key="label.editions.category.science"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'gardening'}">
                                <fmt:message key="label.editions.category.gardening"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'industry'}">
                                <fmt:message key="label.editions.category.industry"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'agriculture'}">
                                <fmt:message key="label.editions.category.agriculture"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'building'}">
                                <fmt:message key="label.editions.category.building"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'economics'}">
                                <fmt:message key="label.editions.category.economics"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'fashion'}">
                                <fmt:message key="label.editions.category.fashion"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'newspaper'}">
                                <fmt:message key="label.editions.category.newspaper"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'mind-breaker'}">
                                <fmt:message key="label.editions.category.mindBreaker"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'for men'}">
                                <fmt:message key="label.editions.category.forMen"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'for women'}">
                                <fmt:message key="label.editions.category.forWomen"/>
                            </c:when>
                            <c:when test="${edition.getCategory() eq 'for children'}">
                                <fmt:message key="label.editions.category.forChildren"/>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="col" style="width: 15%">${edition.getPrice()}</div>
                    <div class="col" style="width: 10%">
                        <form method="post" style="display: inline">
                            <button class="transparentButton" name="id" value="${edition.getEditionId()}">
                                <img height="17px"
                                     src="https://png2.kisspng.com/sh/633009adfe10a49c73fc1d9cec2d6a6d/L0KzQYm3V8IxN5p8iJH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfRmdJZ5fZ9yY3BxPYbpVcFnaWk7T9gBMnO7PoK8VMk2Pmk5Sac8MkC8SYO3VsUzOWkziNDw/kisspng-computer-icons-delete-icon-5b51fa867f62c8.1549568415320992065218.png">
                            </button>
                        </form>
                    </div>
                    <div name="details" style="display: none; width: 100%">
                        <fmt:message key="label.editions.periodicity"/>:
                        <c:choose>
                            <c:when test="${edition.getPeriodicity() eq 'daily'}">
                                <fmt:message key="label.editions.periodicity.daily"/>
                            </c:when>
                            <c:when test="${edition.getPeriodicity() eq 'weekly'}">
                                <fmt:message key="label.editions.periodicity.weekly"/>
                            </c:when>
                            <c:when test="${edition.getPeriodicity() eq 'monthly'}">
                                <fmt:message key="label.editions.periodicity.monthly"/>
                            </c:when>
                        </c:choose>
                        <br>
                        <fmt:message key="label.editions.details"/>: ${edition.getDescription()}
                    </div>
                </li>
            </c:forEach>
        </ul>
        <br>
        <form method="get" style="float: right;">
            <button class="pagination-btn" name="startIndex" ${start ? 'disabled' : ''} value="${startIndex-10}"
                    onclick="this.form.submit()">-10
            </button>
            <button class="pagination-btn" name="startIndex" ${end ? 'disabled' : ''} value="${startIndex+10}"
                    onclick="this.form.submit()">+10
            </button>
        </form>
    </c:if>
</div>
</body>
</html>

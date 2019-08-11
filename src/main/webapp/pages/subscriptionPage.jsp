<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.08.2019
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='base.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>Subscription</title>
    <script>
        <%@include file="../scripts/openMenu.js" %>
    </script>
</head>
<body>
<div class="box">
    <span style="position: fixed; left: 750px; top: 150px; font-size: 14px">
    <c:if test="${paid}">
        <p style="color: cadetblue"><fmt:message key="label.subscribe.paySuccess"/></p>
    </c:if>
    <c:if test="${addedToCart}">
        <p style="color: cadetblue"><fmt:message key="label.subscribe.addedInCart"/></p>
    </c:if>
        <c:if test="${notSelected}">
            <p class="error"><fmt:message key="error.wrongInput.nothingSelected"/>!</p>
        </c:if>
    </span>
    <h3><fmt:message key="label.mainMenu.subscribe"/></h3>
    <hr align="left">
    <c:if test="${editions.size() == 0}">
        <fmt:message key="label.subscribe.emptyList"/>
    </c:if>
    <c:if test="${editions.size() > 0}">
        <ul class="table-fill">
            <li class="header">
                <div class="col" style="width: 40%"><fmt:message key="label.editions.name"/></div>
                <div class="col" style="width: 40%"><fmt:message key="label.editions.category"/></div>
                <div class="col" style="width: 20%"><fmt:message key="label.editions.price"/></div>
            </li>
            <c:forEach items="${editions}" var="edition" varStatus="loop">
                <form method="post">
                    <input type="hidden" name="periodicity" value="${edition.getPeriodicity()}">
                    <input type="hidden" name="editionId" value="${edition.getEditionId()}">
                    <li onclick="openMenu(${loop.index})">
                        <div class="col" style="width: 40%">${edition.getEditionTitle()}</div>
                        <div class="col" style="width: 40%">
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
                        <div class="col" style="width: 20%"><input type="hidden" name="price"
                                                                   value="${edition.getPrice()}">
                                ${edition.getPrice()}
                        </div>
                    </li>
                    <div name="details" style="display: none; width: 100%; background-color: cornsilk">
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
                        <br>
                        <fmt:message key="label.subscribe.subscriptionFor"/>
                        <input onchange="countSum(${loop.index})" type="number" step="1" min="1" max="99" name="issues"
                               style="width: 35px">
                        <fmt:message key="label.subscribe.issues"/> <br>
                        <span style="float: left"><fmt:message key="label.subscribe.sum"/>:
                    <input name="disabledSum" disabled style="width: 50px"></span>
                        <input type="hidden" name="sum">
                        <button name="pay" value="true" style="float: right; margin: 0px 7px 7px; ; cursor: pointer"
                                onclick="this.form.submit()">
                            <fmt:message key="label.subscribe.pay"/></button>
                        <button name="cart" value="true" style="float: right; ; cursor: pointer"
                                onclick="this.form.submit()">
                            <fmt:message key="label.subscribe.inCart"/></button>
                    </div>
                </form>
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

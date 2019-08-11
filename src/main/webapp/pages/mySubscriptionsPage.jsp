<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.08.2019
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="../WEB-INF/tags/today.tld" %>

<jsp:include page='base.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>My subscriptions</title>
    <script>
        <%@include file="../scripts/openMenu.js" %>
    </script>
</head>
<body>
<div class="box">
    <span style="position: fixed; left: 700px; top: 130px;">
        <fmt:message key="label.subscribe.todayIs"/>: <date:today/>
    </span>
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
    <h3><fmt:message key="label.mainMenu.mySubscriptions"/></h3>
    <hr align="left">
    <ul class="table-fill">
        <li class="header">
            <div class="col" style="width: 5%"></div>
            <div class="col" style="width: 35%"><fmt:message key="label.editions.name"/></div>
            <div class="col" style="width: 30%"><fmt:message key="label.editions.category"/></div>
            <div class="col" style="width: 10%"><fmt:message key="label.editions.price"/></div>
            <div class="col" style="width: 20%"><fmt:message key="label.adminMenu.payments.quantity"/></div>
        </li>
        <c:forEach items="${subscriptions}" var="subscription" varStatus="loop">
            <form method="post">
                <li onclick="openMenu(${loop.index})">
                    <c:set value="${subscription.getEdition()}" var="edition" scope="page"/>
                    <div class="col" style="width: 5%">
                        <c:if test="${subscription.getIssuesQuantity() == 1}">
                            <img height="20px"
                                ${(locale eq 'ru') ? ("title='Срок действия подписки истекает'")
                                        : "title='Subscription Expires'"}
                                 src="https://png2.kisspng.com/sh/313cf13632a1e80f087f0aba27bb4f5d/L0KzQYm3VMI3N5xoj5H0aYP2gLBuTfNwdaF6jNd7LX3yhcTsTgBwcZ95feQ2bXHqebS0jf92e5Yyi9V7b3zvPcjvhfVtNaRoRdV1aXPuPbF1Tfl1NWZmfaQ5ZnS8c4m3gsEzNmM7T6U8MkG8QYa5VMc3PGc4S6o6OUSxgLBu/kisspng-computer-mouse-pointer-magic-mouse-scroll-wheel-sc-click-on-it-5ae20fd9c80b12.2673321915247646338194.png">
                        </c:if>
                    </div>
                    <div class="col" style="width: 35%">${edition.getEditionTitle()}</div>
                    <div class="col" style="width: 30%">
                        <input name="editionId" type="hidden" value="${edition.getEditionId()}">
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
                    <input type="hidden" value="${edition.getPrice()}" name="price"/>
                    <div class="col" style="width: 10%">${edition.getPrice()}</div>
                    <div class="col" style="width: 20%">
                            ${subscription.getIssuesQuantity()}
                    </div>
                </li>
                <div name="details" style="display: none; width: 100%; background-color: cornsilk">
                    <fmt:message key="label.editions.periodicity"/>:
                    <input type="hidden" name="periodicity" value="${edition.getPeriodicity()}">
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
                    <br><br>
                    <fmt:message key="label.subscribe.subscriptionExpires"/>:
                        ${subscription.getExpireDate()}
                    <input type="hidden" name="startDate" value="${subscription.getExpireDate()}"><br>
                    <fmt:message key="label.subscribe.extendFor"/>:
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
</div>
</body>
</html>

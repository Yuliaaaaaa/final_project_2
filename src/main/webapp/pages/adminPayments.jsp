<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.07.2019
  Time: 5:08
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
    <title>Admin Payments</title>
    <script>
        <%@include file="../scripts/openMenu.js" %>
    </script>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.adminMenu.payments"/></h3>
    <hr align="left">
    <ul class="table-fill">
        <li class="header">
            <div class="col" style="width: 40%"><fmt:message key="label.adminMenu.payments.user"/></div>
            <div class="col" style="width: 20%"><fmt:message key="label.subscribe.sum"/></div>
            <div class="col" style="width: 40%"><fmt:message key="label.adminMenu.payments.date"/></div>
        </li>
        <c:forEach items="${payments}" var="payment" varStatus="loop">
            <c:set var="user" value="${payment.getUser()}" scope="page"/>
            <li onclick="openMenu(${loop.index})">
                <div class="col" style="width: 40%">
                        ${user.getFirstName()} ${user.getLastName()}
                </div>
                <div class="col" style="width: 20%"> ${payment.getPaymentSum()}
                </div>
                <div class="col" style="width: 40%">${payment.getPaymentDate()}</div>
                <div name="details" style="display: none; width: 100%">
                    <div class="col" style="width: 30%"><fmt:message key="label.editions.name"/></div>
                    <div class="col" style="width: 30%"><fmt:message key="label.editions.category"/></div>
                    <div class="col" style="width: 15%"><fmt:message key="label.adminMenu.payments.quantity"/></div>
                    <c:set var="details" value="${payment.getPaymentDetails()}" scope="page"/>
                    <c:forEach items="${details}" var="detail">
                        <c:set var= "subscription" value="${detail.getSubscription()}" scope="page"/>
                        <c:set var= "edition" value="${subscription.getEdition()}" scope="page"/>
                        <div class="col" style="width: 30%">${edition.getEditionTitle()}</div>
                        <div class="col" style="width: 30%">
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
                        <div class="col" style="width: 15%">${subscription.getIssuesQuantity()}</div>
                        <br>
                    </c:forEach>
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
</div>

</body>
</html>

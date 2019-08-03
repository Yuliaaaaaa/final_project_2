<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.08.2019
  Time: 22:07
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
    <title>Cart</title>
    <script>
        <%@include file="../scripts/openMenu.js" %>
        function selectAll() {
            var selectAll = this.document.getElementById("selectAll");
            var ticks = this.document.getElementsByName('tick');
            if (selectAll.checked)
                ticks.forEach(checkItem);
            else
                ticks.forEach(uncheckItem);
            this.document.getElementsByName("tick").forEach()
        }
        function uncheckItem(value, index, ar) {
            document.getElementsByName('tick').item(index).checked = false;
        }
        function checkItem(value, index, ar) {
            document.getElementsByName('tick').item(index).checked = true;
        }

        function countSum(idx) {
            var price = this.document.getElementsByName("price").item(idx).value;
            var issues = this.document.getElementsByName("issues").item(idx).value;
            var shownSum = this.document.getElementById("shownSum");
            var sum = parseFloat(price*issues);
            if(this.document.getElementsByName("tick").item(idx).checked)
                shownSum.value = parseFloat(shownSum.value) + sum;
            else
                shownSum.value = parseFloat(shownSum.value) - sum;
            this.document.getElementById("inputSum").value = shownSum.value;
        }

    </script>
</head>
<body>
<div class="box">
    <h3 style="display: inline"><fmt:message key="label.mainMenu.cart"/></h3>
    <p style="margin-top: 3px; margin-left: 10px; display: inline">
        <input type="checkbox" onchange="selectAll()" id="selectAll">
        <fmt:message key="label.cart.chooseAll"/></p>
    <span style="position: fixed; left: 750px; top: 150px; font-size: 14px">
    <c:if test="${paid}">
        <p style="color: cadetblue;  margin-top: -5px"><fmt:message key="label.subscribe.paySuccess"/></p>
    </c:if>
    <c:if test="${deleted}">
        <p style="color: cadetblue; margin-top: -5px"><fmt:message key="label.cart.removedSuccessfully"/></p>
    </c:if></span>
    <hr align="left">
    <form method="post">
    <ul class="table-fill">
        <li class="header">
            <div class="col" style="width: 5%"></div>
            <div class="col" style="width: 35%"><fmt:message key="label.editions.name"/></div>
            <div class="col" style="width: 30%"><fmt:message key="label.editions.category"/></div>
            <div class="col" style="width: 10%"><fmt:message key="label.editions.price"/></div>
            <div class="col" style="width: 20%"><fmt:message key="label.adminMenu.payments.quantity"/></div>
        </li>
        <c:forEach items="${subscriptions}" var="subscription" varStatus="loop">
            <li onclick="openMenu(${loop.index})">
                <c:set value="${subscription.getEdition()}" var="edition" scope="page"/>
                <div class="col" style="width: 5%">
                    <input type="checkbox" onchange="countSum(${loop.index})"
                           value="${subscription.getSubscriptionId()}" name="tick">
                </div>
                <div class="col" style="width: 35%">${edition.getEditionTitle()}</div>
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
                <input type="hidden" value="${edition.getPrice()}" name="price"/>
                <div class="col" style="width: 10%">${edition.getPrice()}</div>
                <div class="col" style="width: 20%">
                    <input value="${subscription.getIssuesQuantity()}" name="issues" type="hidden">
                        ${subscription.getIssuesQuantity()}
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
        <fmt:message key="label.subscribe.sum"/>: <input id ="inputSum" name ="inputSum" type="hidden">
        <input id="shownSum" disabled value="0" style="width: 50px">
    <button name="pay" value="true"><fmt:message key="label.cart.pay"/></button>
    <button name="delete" value="true"><fmt:message key="label.cart.delete"/></button>
    </form>
</div>
</body>
</html>

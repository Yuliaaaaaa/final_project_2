<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.08.2019
  Time: 19:01
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
    <title>Admin main</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.adminMenu.main.welcome"/></h3>
    <hr align="left">
    <fmt:message key="label.adminMenu.main.ability"/>
</div>
</body>
</html>

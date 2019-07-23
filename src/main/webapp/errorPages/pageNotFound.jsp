<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.07.2019
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='../pages/basePage.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>PageNotFound</title>
</head>
<body>
<div class="box">
    <h3>404</h3>
    <hr align="left">
    <fmt:message key="error.pageNotFound"/>
</div>
</body>
</html>

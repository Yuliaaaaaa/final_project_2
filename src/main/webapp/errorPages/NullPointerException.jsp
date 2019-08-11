<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.08.2019
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='../pages/base.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>Error</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="error"/>!</h3>
    <hr align="left">
    <fmt:message key="error.NullPointer"/>
</div>
</body>
</html>

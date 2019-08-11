<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.07.2019
  Time: 22:44
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
    <title>Registration</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.signUp"/></h3>
    <hr align="left">
    <form method="post">
    <table>
        <tr>
            <td valign="top">
                <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.firstName"/>*:</div>
                <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.lastName"/>*:                 </div>
                <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.birthDate"/>*:                </div>
                <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.sex"/>*:                      </div>
                <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.phoneNumber"/>:              </div>
                <div style="margin-bottom: 13.5px"><fmt:message key="label.authorisation.email"/>*:         </div>
                <div style="margin-bottom: 13.5px"><fmt:message key="label.authorisation.password"/>*:      </div>
                <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.confirmPassword"/>*:          </div>
            </td>
            <td valign="top">
                <input style="margin-bottom: 10px" name="firstName" value="${firstName}"><br>
                <input style="margin-bottom: 10px" name="lastName" value="${lastName}"><br>
                <input style="margin-bottom: 10px" name="birthDate" type="date" value="${birthDate}"><br>
                <input type="radio" ${sex eq 'm' ? 'checked' : ''}
                       style="margin-left: 30px; margin-right: 0px; margin-bottom: 10px" class="radio"
                       name="sex" value="m">
                <img height="20px"
                     src="https://png2.kisspng.com/sh/575e224c636e80f27b22be6cfc06daba/L0KzQYm3VME6N6N1fZH0aYP2gLBuTfdmdpVqip98eX3lf720jfFtbV54gdl3LXPvecG0ggJ1NZZyhAZyY3Bxg368gfQ5P5Y9fqIEYkK4SHACWcU5OWQ7T6MAMkS0Q4i9UcUxO2o8RuJ3Zx==/kisspng-gender-symbol-male-sign-clip-art-emoticons-5ad87e8f09b258.9958136715241376150397.png">
                </input>
                <input type="radio" ${sex eq 'f' ? 'checked' : ''}
                       style="margin-left: 20px; margin-right: 0px; margin-bottom: 10px" class="radio"
                       name="sex" value="f">
                <img height="20px"
                     src="https://png2.kisspng.com/sh/35a20db5741f6f9971fae8ed04ff5efb/L0KzQYm3V8E4N5D9epH0aYP2gLBuTfdmdpVqip98eX3lf720hvVuaZ1qReVyZ36wdrb0if5qe6UyiAt2YnBvPYbpVPQ6ampmT9MCNkO3PoiBVsQzPWU9Sac8MUi0Qoi9UsUxOWcziNDw/kisspng-gender-symbol-female-sign-feminist-symbol-5b4d9b9a7a7634.7864254815318127625016.png">
                </input><br>
                <input style="margin-bottom: 10px" name="phoneNumber" value="${phoneNumber}"><br>
                <input style="margin-bottom: 10px" name="email" value="${email}"><br>
                <input style="margin-bottom: 10px" name="password" type="password" value="${password}"><br>
                <input name="password2" type="password" value="${password2}">
            </td>
        </tr>
    </table>
        <c:if test="${fnWrong}">
            <p class="error"><fmt:message key="error.wrongInput.incorrectName"/></p>
            </c:if>
        <c:if test="${lnWrong}">
        <p class="error"><fmt:message key="error.wrongInput.incorrectSurname"/></p>
        </c:if>
        <c:if test="${phWrong}">
        <p class="error"><fmt:message key="error.wrongInput.incorrectPhone"/></p>
        </c:if>
        <c:if test="${emailWrong}">
        <p class="error"><fmt:message key="error.wrongInput.incorrectEmail"/></p>
        </c:if>
        <c:if test="${passwordsNotEqual}">
        <p class="error"><fmt:message key="error.wrongInput.passwordsNotEqual"/></p>
        </c:if>
        <c:if test="${emptyFields}">
        <p class="error"><fmt:message key="error.wrongInput.allFieldsRequired"/>
        </c:if>
    <button class="btn" style="float: right; margin-right: 100px"><fmt:message key="label.mainMenu.signUp"/></button>
    </form>
</div>
</body>
</html>
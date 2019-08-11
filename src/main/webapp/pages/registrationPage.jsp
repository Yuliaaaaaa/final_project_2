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
                     src="https://png2.kisspng.com/sh/8b879a7314d4aed98e44cc22bc4a2c3a/L0KzQYm3VcA1N5xpfZH0aYP2gLBuTfdmdpVqip98eX3lf720gB9ueKZ5feQ2aXPyfsS0jP9od15yedC2NXHoc4S5UsM6PGc3Sao3NkS5QIe3VsMyPWM6TKQDN0e0RoGAWL5xdpg=/kisspng-gender-symbol-computer-icons-logo-man-5aec3223946218.6460606315254287716078.png">
                </input>
                <input type="radio" ${sex eq 'f' ? 'checked' : ''}
                       style="margin-left: 20px; margin-right: 0px; margin-bottom: 10px" class="radio"
                       name="sex" value="f">
                <img height="20px"
                     src="https://png2.kisspng.com/sh/ac39def56e6d2dd2181a6792f55b0c76/L0KzQYm3U8MxN6lpj5H0aYP2gLBuTfdmdpVqip98eX3lf720hvVuaZ1qRdhubXHvdX68gfJma2lofakCNXTmRnA8UsMyOmMASaMAMkK3RYO9WMY1QGkARuJ3Zx==/kisspng-gender-symbol-female-female-5abec8ce775dc6.5231229115224526864889.png">
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
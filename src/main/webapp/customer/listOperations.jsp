<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <script src="http://code.jquery.com/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"
            type="text/javascript"></script>

    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/layout.css"/>
    <title>SupinBank - Customer</title>
</head>
<body>
<jsp:include page="/partials/navbar.jsp"/>
<div class="container">
    <h1>Operations</h1>
    <hr/>

    <form method="get" class="form-inline ">
        <label>Select your account </label>

        <select name="accountId">
            <c:forEach items="${accounts}" var="account">
                <c:choose>
                    <c:when test="${account.id eq selectedAccount.id}">
                        <option value="${account.id}" selected="selected">${account.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${account.id}">${account.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <button type="submit" class="btn">See</button>
        <c:if test="${noAccountSelected}">
            <div class="help-inline alert alert-info">
                Please select an account
            </div>
        </c:if>
    <c:choose>
        <c:when test="${not empty selectedAccount}">
            <c:choose>
                <c:when test="${not empty selectedAccount.operations}">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Date</th>
                            <th>Wording</th>
                            <th>Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${selectedAccount.operations}" var="operation">
                            <tr>
                                <td>${operation.id}</td>
                                <td>${operation.date}</td>
                                <td>${operation.wording}</td>
                                <td>${operation.amount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h3>No operations on account ${selectedAccount.name}</h3>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <h3>No account found</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
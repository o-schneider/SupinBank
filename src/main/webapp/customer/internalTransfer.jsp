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
    <h1>Perform a transfer</h1>
    <hr/>

    <form method="post" class="form-horizontal">
        <div class="control-group  ${not empty accountError ? "error":""}">
            <label class="control-label" for="debitAccount">From</label>

            <div class="controls">
                <select name="debitAccount" id="debitAccount">
                    <c:forEach items="${user.accounts}" var="account">
                        <c:choose>
                            <c:when test="${account.id eq debitAccount.id}">
                                <option value="${account.id}" selected="selected">${account.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${account.id}">${account.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>

                <div class="help-inline">
                    ${accountError}
                </div>
            </div>
        </div>
        <div class="control-group  ${not empty accountError ? "error":""}">
            <label class="control-label" for="creditAccount">To</label>

            <div class="controls">
                <select name="creditAccount" id="creditAccount">
                    <c:forEach items="${user.accounts}" var="account">
                        <c:choose>
                            <c:when test="${account.id eq creditAccount.id}">
                                <option value="${account.id}" selected="selected">${account.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${account.id}">${account.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>

                <div class="help-inline">
                    ${accountError}
                </div>
            </div>
        </div>
        <div class="control-group ${not empty amountError ? "error":""}">
            <label class="control-label" for="debitAccount">Amount</label>

            <div class="controls">
                <input type="text" name="amount" class="input-xlarge" id="amount" value="${amount}">

                <div class="help-inline">
                    <c:forEach items="${amountError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty wordingError ? "error":""}">
            <label class="control-label" for="wording">Wording</label>

            <div class="controls">
                <input type="text" name="wording" class="input-xlarge" id="wording" value="${wording}">

                <div class="help-inline">
                    <c:forEach items="${wordingError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Perform transfer"/>
        </div>
    </form>
</div>
</body>
</html>
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
<jsp:include page="/partials/navbar.jsp" />

<div class="container">
    <h1>Perform a transfer</h1>
    <hr/>

    <form method="post" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="debitAccount">Debit account</label>

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
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Credit account BBAN</label>

            <div class="controls">
                <input type="text" class="input-medium" name="bankCode">
                <input type="text" class="input-medium" name="branchCode" >
                <input type="text" class="input-xlarge" name="accountNumber">
                <input type="text" class="input-mini" name="key" size="2">
                <c:if test="${not empty creditAccountError}">
                    <div class="help-block alert alert-error">
                            ${creditAccountError}
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="debitAccount">Amount</label>

            <div class="controls">
                <input type="text" name="amount" class="input-xlarge" id="amount" value="${amount}">
                <c:if test="${not empty amountError}">
                    <div class="help-block alert alert-error">
                        <ul>
                            <c:forEach items="${amountError}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="wording">Wording</label>

            <div class="controls">
                <input type="text" name="wording" class="input-xlarge" id="wording" value="${wording}">
                <c:if test="${not empty wordingError}">
                    <div class="help-block alert alert-error">
                        <ul>
                            <c:forEach items="${wordingError}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Perform transfer"/>
        </div>
    </form>
</div>
</body>
</html>
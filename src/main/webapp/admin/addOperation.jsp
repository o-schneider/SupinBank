<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/layout.css"/>
    <link rel="script" href="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"/>
    <title>SupinBank - Admin</title>
</head>
<body>
<jsp:include page="/partials/navbar.jsp" />
<div class="container">
    <h1>New operation</h1>
    <hr/>
    <fieldset>
        <legend>Account ${account.name} owned by ${account.accountOwner.firstName} ${account.accountOwner.lastName}</legend>

        <form method="post" class="form-horizontal">
            <div class="control-group">
                <label class="control-label" for="amount">Amount</label>

                <div class="controls">
                    <input type="text" name="amount" class="input-xlarge" id="amount" value="${operation.amount}">
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
                    <input type="text" name="wording" class="input-xlarge" id="wording" value="${operation.wording}">
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
                <input type="hidden" value="${account.id}" name="account">
                <input type="submit" class="btn btn-primary" value="Ok"/>
            </div>
        </form>
    </fieldset>
</div>
</form>
</div>
</body>
</html>

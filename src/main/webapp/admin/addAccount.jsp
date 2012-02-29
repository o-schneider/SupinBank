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
<jsp:include page="/partials/navbar.jsp"/>
<div class="container">
    <jsp:include page="/partials/generalError.jsp"/>
    <h1>Add account</h1>
    <hr/>
    <fieldset>
        <legend>Account for ${customer.firstName} ${customer.lastName}</legend>

        <form method="post" class="form-horizontal">
            <div class="control-group ${not empty nameError ? "error":""}">
                <label class="control-label" for="name">Name</label>

                <div class="controls">
                    <input type="text" name="name" class="input-xlarge" id="name" value="${account.name}">

                    <div class="help-inline">
                        <c:forEach items="${nameError}" var="error" varStatus="loop">
                            <c:if test="${loop.index gt 0}">,</c:if>
                            ${error}
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="plan">Interest plan</label>

                <div class="controls">
                    <select name="plan" id="plan">
                        <c:forEach items="${interestPlans}" var="plan">
                            <option value="${plan.id}">${plan.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-actions">
                <input type="submit" class="btn btn-primary" value="Add account"/>
            </div>
        </form>
    </fieldset>
</div>
</form>
</div>
</body>
</html>

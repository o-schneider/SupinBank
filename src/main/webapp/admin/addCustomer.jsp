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
    <h1>Add a new Customer</h1>
    <hr/>

    <form method="post" class="form-horizontal">
        <div class="control-group ${not empty firstNameError ? "error":""}">
            <label class="control-label" for="firstname">First name</label>

            <div class="controls">
                <input type="text" name="firstname" class="input-xlarge" id="firstname" value="${customer.firstName}">

                <div class="help-inline">
                    <c:forEach items="${firstNameError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty lastNameError ? "error":""}">
            <label class="control-label" for="lastname">Last name</label>

            <div class="controls">
                <input type="text" name="lastname" class="input-xlarge" id="lastname" value="${customer.lastName}">

                <div class="help-inline">
                    <c:forEach items="${lastNameError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty emailError ? "error":""}">
            <label class="control-label" for="email">Email</label>

            <div class="controls">
                <input type="text" name="email" class="input-xlarge" id="email" value="${customer.email}">

                <div class="help-inline">
                    <c:forEach items="${emailError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty addressError ? "error":""}">
            <label class="control-label" for="email">Address</label>

            <div class="controls">
                <input type="text" name="address" class="input-xlarge" id="address" value="${customer.address}">

                <div class="help-inline">
                    <c:forEach items="${addressError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty zipCodeError ? "error":""}">
            <label class="control-label" for="zip">ZIP Code</label>

            <div class="controls">
                <input type="text" name="zip" class="input-small" id="zip" value="${customer.zipCode}">

                <div class="help-inline">
                    <c:forEach items="${zipCodeError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty cityError ? "error":""}">
            <label class="control-label" for="city">City</label>

            <div class="controls">
                <input type="text" name="city" class="input-xlarge" id="city" value="${customer.city}">

                <div class="help-inline">
                    <c:forEach items="${cityError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="control-group ${not empty phoneError ? "error":""}">
            <label class="control-label" for="phone">Phone</label>

            <div class="controls">
                <input type="text" name="phone" class="input-xlarge" id="phone" value="${customer.phone}">

                <div class="help-inline">
                    <c:forEach items="${phoneError}" var="error" varStatus="loop">
                        <c:if test="${loop.index gt 0}">,</c:if>
                        ${error}
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Add customer"/>
        </div>
    </form>
</div>
</body>
</html>

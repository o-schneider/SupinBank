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
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${pageContext.servletContext.contextPath}">SupinBank</a>
            <ul class="nav">
                <li>
                    <a href="${pageContext.servletContext.contextPath}/admin/customers">List customers</a>
                </li>
                <li class="active">
                    <a href="${pageContext.servletContext.contextPath}/admin/customers/new">Add customer</a>
                </li>
            </ul>

            <ul class="nav pull-right">
                <li>
                    <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <h1>Add a new Customer</h1>
    <hr/>

    <form method="post" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="firstname">First name</label>

            <div class="controls">
                <input type="text" name="firstname" class="input-xlarge" id="firstname" value="${customer.firstName}">
                <c:if test="${not empty firstNameError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${firstNameError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="lastname">Last name</label>

            <div class="controls">
                <input type="text" name="lastname" class="input-xlarge" id="lastname" value="${customer.lastName}">
                <c:if test="${not empty lastNameError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${lastNameError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email</label>

            <div class="controls">
                <input type="text" name="email" class="input-xlarge" id="email" value="${customer.email}">
                <c:if test="${not empty emailError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${emailError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Address</label>

            <div class="controls">
                <input type="text" name="address" class="input-xlarge" id="address" value="${customer.address}">
                <c:if test="${not empty addressError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${addressError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="zip">ZIP Code</label>

            <div class="controls">
                <input type="text" name="zip" class="input-small" id="zip" value="${customer.zipCode}">
                <c:if test="${not empty zipCodeError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${zipCodeError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="city">City</label>

            <div class="controls">
                <input type="text" name="city" class="input-xlarge" id="city" value="${customer.city}">
                <c:if test="${not empty cityError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${cityError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="phone">Phone</label>

            <div class="controls">
                <input type="text" name="phone" class="input-xlarge" id="phone" value="${customer.phone}">
                <c:if test="${not empty phoneError}">
                    <div class="help-block alert alert-error">
                    <ul>
                        <c:forEach items="${phoneError}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Add customer"/>
        </div>
    </form>
</div>
</body>
</html>

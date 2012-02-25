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
    <title>SupinBank - Welome</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${pageContext.servletContext.contextPath}">SupinBank</a>
            <c:if test="${admin}">
                <ul class="nav">
                    <li class="active">
                        <a href="${pageContext.servletContext.contextPath}/admin/customers">List customers</a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/admin/customers/new">Add customer</a>
                    </li>
                </ul>
            </c:if>

            <c:choose>
                <c:when test="${not empty user}">
                    <ul class="nav pull-right">
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <form method="post" class="login-form form-inline pull-right">
                        <input type="text" name="email" placeholder="Email"/>
                        <input type="password" name="password" class="input-small" placeholder="Password"/>
                        <input type="submit" class="btn">Sign in</input>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div class="container">
    <c:if test="${loginFailed}">
        <div id="loginErrorMsg" class="alert alert-error">
            <p>${loginFailedMsg}</p>
        </div>
    </c:if>

    <h1>Welcome!</h1>
    <hr/>
    <h2>Welcome to SupinBank</h2>

</div>
</body>
</html>

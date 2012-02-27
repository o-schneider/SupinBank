<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <script src="http://code.jquery.com/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/layout.css"/>
    <title>SupinBank - Welcome</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${pageContext.servletContext.contextPath}">SupinBank</a>


            <c:choose>
                <c:when test="${not empty user}">
                    <c:if test="${admin}">
                        <ul class="nav">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/admin/customers">List customers</a>
                            </li>
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/admin/customers/new">Add customer</a>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${not admin}">
                        <ul class="nav">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/customer/accounts">My accounts</a>
                            </li>
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/customer/operations">My
                                    operations</a>
                            </li>
                            <li class="dropdown">
                                <a href="#"
                                   class="dropdown-toggle"
                                   data-toggle="dropdown">
                                    Perform a transfer
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="${pageContext.servletContext.contextPath}/customer/transfer/internal">Within your accounts</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.servletContext.contextPath}/customer/transfer/external">To an external account</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </c:if>
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
                        <input type="submit" class="btn" value="Sign in"/>
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

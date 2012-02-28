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
    <title>SupinBank - Customer</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${pageContext.servletContext.contextPath}">SupinBank</a>
            <ul class="nav">
                <li class="active">
                    <a href="${pageContext.servletContext.contextPath}/customer/accounts">My accounts</a>
                </li>
                <li>
                    <a href="${pageContext.servletContext.contextPath}/customer/operations">My operations</a>
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
            <ul class="nav pull-right">
                <li>
                    <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <h1>${user.firstName} ${user.lastName} accounts</h1>
    <hr/>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td><a href="${pageContext.servletContext.contextPath}/customer/operations?accountId=${account.id}">${account.name}</a></td>
                    <td>${account.amount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
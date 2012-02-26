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
                    <a href="#">My operations</a>
                </li>
                <li>
                    <a href="#">Perform a transfer</a>
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
            <c:forEach items="${user.accounts}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.name}</td>
                    <td>${account.amount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
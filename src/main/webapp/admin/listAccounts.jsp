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
    <h1>${customer.firstName} ${customer.lastName} accounts</h1>
    <hr/>
    <p>Email : ${customer.email}</p>
    <div class="pull-right">
        <form method="post">
            <input type="hidden" name="id" class="${customer.id}" />
            <input type="submit" class="btn btn-primary" value="Add account"/>
        </form>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Amount</th>
                <th>Interest plan</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${customer.accounts}" var="account">
                <tr>
                    <td>${account.id}</td>
                    <td>${account.name}</td>
                    <td>${account.amount}</td>
                    <td>${account.interestPlan.name}</td>
                    <td><a href="${pageContext.servletContext.contextPath}/admin/operations/new?id=${account.id}">Add operation</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
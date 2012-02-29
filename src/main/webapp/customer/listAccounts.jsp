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
<jsp:include page="/partials/navbar.jsp" />
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
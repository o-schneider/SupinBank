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
<jsp:include page="/partials/navbar.jsp" />
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

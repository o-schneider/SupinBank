<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Refresh" content="5;url=${pageContext.servletContext.contextPath}"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/layout.css"/>
    <link rel="script" href="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"/>
    <title>SupinBank - Bye bye</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#">SupinBank</a>
        </div>
    </div>
</div>
<div class="container">
    <h1>Bye bye</h1>
    <hr/>
    <p>You have been logged out and will be redirected to the home page. <br/>
        <a href="${pageContext.servletContext.contextPath}">Click here if you don't want to wait.</a>
    </p>
</div>
</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="resources/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="resources/css/layout.css"/>
    <link rel="script" href="resources/js/bootstrap.min.js"/>
    <title>SupinBank</title>
    <style>

    </style>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#">SupinBank</a>
            <form method="POST" class="login-form form-inline">
                <input type="text" value="email" />
                <input type="password" name="password" class="input-small"/>
                <input type="submit" class="btn" >Sign in</input>
            </form>
        </div>
    </div>
</div>
<div class="container">
    <h1>Welcome!</h1>
    <hr/>
    <h2>Welcome to SupinBank</h2>
</div>
</body>
</html>

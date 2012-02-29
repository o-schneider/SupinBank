<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty generalError}">
    <div class="alert alert-error">
        <p>${generalError}</p>
    </div>
</c:if>
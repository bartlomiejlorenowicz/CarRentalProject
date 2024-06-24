<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Kontakt</title>
    <link href="<c:url value='/static/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/static/css/sb-admin-2.min.css' />" rel="stylesheet">
</head>
<body>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid">
    <h1 class="h3 mb-4 text-gray-800">Kontakt</h1>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <form action="<c:url value='/contact' />" method="post">
        <div class="form-group">
            <label for="message">Wiadomość:</label>
            <textarea id="message" name="message" class="form-control" rows="5" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Wyślij</button>
    </form>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>

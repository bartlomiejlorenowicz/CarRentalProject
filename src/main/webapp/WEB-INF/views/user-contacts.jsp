<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Moje Wiadomości</title>
    <link href="<c:url value='/static/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/static/css/sb-admin-2.min.css' />" rel="stylesheet">
</head>
<body>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid content-wrapper">
    <h1 class="h3 mb-4 text-gray-800">Moje Wiadomości</h1>
    <a href="<c:url value='/' />" class="btn btn-secondary btn-back mb-4">Powrót do dashboardu</a>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Wiadomości</h6>
        </div>
        <div class="card-body">
            <c:forEach var="contact" items="${userContacts}">
                <div class="border-bottom mb-3 pb-2">
                    <p><strong>Data:</strong> ${contact.contactDate}</p>
                    <p><strong>Wiadomość:</strong> ${contact.message}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>

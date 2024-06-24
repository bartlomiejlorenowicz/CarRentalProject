<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Szczegóły Samochodu</title>
    <link href="<c:url value='/static/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/static/css/sb-admin-2.min.css' />" rel="stylesheet">
    <style>
        .content-wrapper {
            margin-left: 250px;
            padding: 20px;
        }

        .container-fluid {
            overflow: hidden;
        }
    </style>
</head>
<body>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid content-wrapper">
    <h1 class="h3 mb-4 text-gray-800">Szczegóły Samochodu</h1>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">${car.make} ${car.model}</h6>
        </div>
        <div class="card-body">
            <p><strong>Rok:</strong> ${car.year}</p>
            <p><strong>Typ:</strong> ${car.type}</p>
            <p><strong>Cena za dzień:</strong> ${car.price}</p>
            <p><strong>Średnia Ocena:</strong> ${car.averageRating}</p>
            <a href="<c:url value='/users/dashboard' />" class="btn btn-secondary btn-back">Powrót do dashboardu</a>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Lista Samochodów</title>
    <link href="<c:url value='/static/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/static/css/sb-admin-2.min.css' />" rel="stylesheet">
    <style>
        .sidebar-left {
            position: fixed;
            top: 0;
            height: 100%;
            width: 200px;
            background-color: #f8f9fc;
            padding-top: 20px;
            z-index: 1;
        }
        .sidebar-left {
            left: 0;
        }
        .content-wrapper {
            margin-left: 220px; /* Dodanie marginesu aby uwzględnić szerokość panelu bocznego */
        }
    </style>
</head>
<body>

<jsp:include page="header-conditional.jsp" />

<div id="wrapper">
    <div id="content-wrapper" class="d-flex flex-column content-wrapper">
        <div id="content">
            <div class="container-fluid">
                <h1 class="h3 mb-4 text-gray-800">Lista Samochodów</h1>
                <a href="<c:url value='/users/dashboard' />" class="btn btn-secondary btn-back mb-4">Powrót do dashboardu</a>
                <div class="row">
                    <c:forEach var="car" items="${carPage.content}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">${car.make} ${car.model}</h6>
                                </div>
                                <div class="card-body">
                                    <p><strong>Rok:</strong> ${car.year}</p>
                                    <p><strong>Typ:</strong> ${car.type}</p>
                                    <p><strong>Cena:</strong> ${car.price}</p>
                                    <a href="<c:url value='/cars/review?carId=${car.carId}' />" class="btn btn-primary">Oceń</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pagination">
                    <c:if test="${carPage.hasPrevious()}">
                        <a href="?page=${carPage.number - 1}&size=${carPage.size}" class="btn btn-primary">Previous</a>
                    </c:if>
                    <c:if test="${carPage.hasNext()}">
                        <a href="?page=${carPage.number + 1}&size=${carPage.size}" class="btn btn-primary">Next</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>

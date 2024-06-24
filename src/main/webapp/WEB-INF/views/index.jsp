<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="container-fluid">
    <!-- Login Message for Non-Logged In Users -->
    <c:if test="${empty sessionScope.user}">
        <div class="login-message">
            Zaloguj się, aby wypożyczyć samochód lub załóż konto.
            <br>
            <a class="btn btn-primary" href="<c:url value='/users/register' />">Rejestracja</a>
        </div>
    </c:if>
    <div class="main-content">
        <div class="text-center">
            <img src="<c:url value='/static/img/car-image.jpg' />" alt="Car Image" class="img-fluid">
        </div>
        <form class="rental-form" action="/cars/search" method="post">
            <h2 class="text-center">Wyszukaj samochód</h2>
            <input type="text" name="make" placeholder="Marka">
            <input type="text" name="model" placeholder="Model">
            <input type="number" name="year" placeholder="Rok">
            <input type="number" name="price" placeholder="Cena maksymalna">
            <select name="type">
                <option value="">Typ</option>
                <c:forEach var="type" items="${carTypes}">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary btn-block">Wyszukaj</button>
        </form>
        <div class="text-center">
            <a class="btn btn-secondary mt-3" href="<c:url value='/cars/top-rated' />">Top Rated Cars</a>
        </div>
    </div>
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp" />

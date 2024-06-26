<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header-conditional.jsp" />

<!-- Main Content -->
<div class="container-fluid">
    <!-- Login Message for Non-Logged In Users -->
    <c:if test="${empty sessionScope.user}">
        <div class="login-message text-center my-4">
            Zaloguj się, aby wypożyczyć samochód lub załóż konto.
            <br>
            <a class="btn btn-primary mt-2" href="<c:url value='/users/register' />">Rejestracja</a>
        </div>
    </c:if>
    <div class="main-content row justify-content-center">
        <div class="col-lg-6">
            <div class="text-center my-4">
                <img src="<c:url value='/static/img/car-image.jpg' />" alt="Car Image" class="img-fluid">
            </div>
            <form class="rental-form border p-4 rounded" action="/cars/search" method="post">
                <h2 class="text-center mb-4">Wyszukaj samochód</h2>
                <div class="form-group">
                    <input type="text" class="form-control mb-3" name="make" placeholder="Marka">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control mb-3" name="model" placeholder="Model">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control mb-3" name="year" placeholder="Rok">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control mb-3" name="price" placeholder="Cena maksymalna">
                </div>
                <div class="form-group">
                    <select name="type" class="form-control mb-3">
                        <option value="">Typ</option>
                        <c:forEach var="type" items="${carTypes}">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Wyszukaj</button>
            </form>
            <div class="text-center mt-4">
                <a class="btn btn-secondary" href="<c:url value='/cars/top-rated' />">Top Rated Cars</a>
            </div>
        </div>
    </div>
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp" />

<!-- Inline CSS for styling -->
<style>
    .container-fluid {
        padding: 2rem 0;
    }
    .login-message {
        margin-top: 2rem;
    }
    .main-content {
        margin-top: 2rem;
    }
    .rental-form {
        background-color: #f8f9fa;
    }
</style>

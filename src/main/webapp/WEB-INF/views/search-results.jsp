<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />

<div class="container">
    <h2>Wyniki wyszukiwania</h2>
    <c:if test="${not empty cars}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Marka</th>
                <th>Model</th>
                <th>Rok</th>
                <th>Cena</th>
                <th>Typ</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>${car.make}</td>
                    <td>${car.model}</td>
                    <td>${car.year}</td>
                    <td>${car.price}</td>
                    <td>${car.type}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty cars}">
        <div class="alert alert-warning">Nie znaleziono samochodów spełniających podane kryteria.</div>
    </c:if>
</div>

<jsp:include page="footer.jsp" />

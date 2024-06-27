<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid" style="margin-left: 220px;">
    <h1 class="h3 mb-4 text-gray-800">Your Reservations</h1>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Reservations List</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Car</th>
                        <th>Reservation Date</th>
                        <th>Return Date</th>
                        <th>Daily Price</th>
                        <th>Rental Days</th>
                        <th>Total Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="reservation" items="${userReservations}">
                        <tr>
                            <td>${reservation.car.make} ${reservation.car.model}</td>
                            <td>${reservation.reservationDate}</td>
                            <td>${reservation.returnDate}</td>
                            <td>${reservation.dailyPrice}</td>
                            <td>${reservation.rentalDays}</td>
                            <td>${reservation.totalPrice}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="text-center mt-4">
                    <a href="<c:url value='/users/dashboard' />" class="btn btn-secondary">Powrót do dashboardu</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

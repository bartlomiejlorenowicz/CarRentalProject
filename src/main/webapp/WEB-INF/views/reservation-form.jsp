<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-xl-8 col-lg-10 col-md-12">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">New Reservation</h1>
                                </div>
                                <form action="<c:url value='/reservations/save' />" method="post">
                                    <div class="form-group">
                                        <label for="car">Car:</label>
                                        <select id="car" name="carId" class="form-control">
                                            <c:forEach var="car" items="${cars}">
                                                <option value="${car.carId}">${car.make} ${car.model}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="returnDate">Return Date:</label>
                                        <input type="datetime-local" class="form-control" id="returnDate" name="returnDate" value="${currentDateTime}" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Save Reservation</button>
                                </form>
                                <div class="text-center mt-4">
                                    <a href="<c:url value='/' />" class="btn btn-secondary btn-user btn-block">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

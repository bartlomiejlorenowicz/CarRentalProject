<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header-conditional.jsp" />

<style>
    .container-fluid {
        padding: 0 15px;
    }

    .card {
        max-width: 100%;
    }

    .form-control {
        width: 100%;
    }

    .text-center h1 {
        font-size: 1.5rem;
    }

    .btn-user {
        width: 100%;
    }

    .row {
        margin: 0;
    }
</style>

<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
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
                                        <select id="car" name="car.carId" class="form-control">
                                            <c:forEach var="car" items="${cars}">
                                                <option value="${car.carId}">${car.make} ${car.model}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="returnDate">Return Date:</label>
                                        <input type="datetime-local" class="form-control" id="returnDate" name="returnDate" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">Save Reservation</button>
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

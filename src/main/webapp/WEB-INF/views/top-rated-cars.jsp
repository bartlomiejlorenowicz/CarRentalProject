<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-2">
            <!-- Sidebar content here (if any) -->
        </div>
        <div class="col-lg-10">
            <h1 class="h3 mb-4 text-gray-800">Top Rated Cars</h1>
            <div class="row">
                <c:forEach var="car" items="${topRatedCars}">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">${car.make} ${car.model}</h6>
                            </div>
                            <div class="card-body">
                                <p><strong>Year:</strong> ${car.year}</p>
                                <p><strong>Type:</strong> ${car.type}</p>
                                <p><strong>Price:</strong> ${car.price}</p>
                                <p><strong>Average Rating:</strong> ${car.averageRating}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

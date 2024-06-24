<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header-conditional.jsp" />

<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome, ${sessionScope.user.username}!</h1>
                                </div>
                                <div class="text-center">
                                    <p>Welcome to your dashboard. Here you can manage your account, view your reservations, and much more.</p>
                                    <a href="<c:url value='/cars' />" class="btn btn-primary btn-user btn-block">View Cars</a>
                                    <a href="<c:url value='/reservations/user' />" class="btn btn-secondary btn-user btn-block">View Reservations</a>
                                    <a href="<c:url value='/reservations/new' />" class="btn btn-secondary btn-user btn-block">Add Reservations</a>
                                    <a href="<c:url value='/profile' />" class="btn btn-info btn-user btn-block">Manage Profile</a>
                                    <a href="<c:url value='/users/logout' />" class="btn btn-danger btn-user btn-block">Logout</a>
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

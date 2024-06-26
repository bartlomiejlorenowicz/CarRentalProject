<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header-conditional.jsp" />

<style>
    .container-fluid {
        overflow: hidden; /* Prevent overflow */
    }
    .sidebar {
        position: fixed;
        top: 0;
        left: 0;
        height: 100%;
        width: 200px;
        background-color: #f8f9fa;
        overflow-y: auto; /* Ensure sidebar is scrollable */
        padding-top: 20px;
    }
    .content {
        margin-left: 220px; /* Adjust based on sidebar width */
        padding: 20px;
        max-width: calc(100% - 220px); /* Ensure the content does not overflow */
    }
    .card {
        overflow: hidden; /* Prevent card overflow */
    }
</style>

<div class="sidebar">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/dashboard' />">Dashboard</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/profile' />">Profile</a>
        </li>
        <!-- Add more sidebar links as needed -->
    </ul>
</div>

<div class="container-fluid content">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Manage Your Profile</h1>
                                </div>
                                <form action="<c:url value='/users/profile/update' />" method="post">
                                    <div class="form-group">
                                        <label for="username">Username:</label>
                                        <input type="text" class="form-control" id="username" name="username" value="${sessionScope.user.username}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <input type="email" class="form-control" id="email" name="email" value="${sessionScope.user.email}">
                                    </div>
                                    <div class="form-group">
                                        <label for="street">Street:</label>
                                        <input type="text" class="form-control" id="street" name="street" value="${sessionScope.user.address.street}">
                                    </div>
                                    <div class="form-group">
                                        <label for="city">City:</label>
                                        <input type="text" class="form-control" id="city" name="city" value="${sessionScope.user.address.city}">
                                    </div>
                                    <div class="form-group">
                                        <label for="zipCode">Zip Code:</label>
                                        <input type="text" class="form-control" id="zipCode" name="zipCode" value="${sessionScope.user.address.zipCode}">
                                    </div>
                                    <div class="form-group">
                                        <label for="country">Country:</label>
                                        <input type="text" class="form-control" id="country" name="country" value="${sessionScope.user.address.country}">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">Update Profile</button>
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

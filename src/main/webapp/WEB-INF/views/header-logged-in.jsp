<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Car Rental - Dashboard</title>
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
<div id="wrapper">
    <!-- Left Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion sidebar-left" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
            <div class="sidebar-brand-text mx-3">Car Rental</div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/users/dashboard' />">
                <i class="fas fa-fw fa-envelope"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <!-- Nav Item - Cars List -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/cars' />">
                <i class="fas fa-fw fa-car"></i>
                <span>Car list</span>
            </a>
        </li>
        <!-- Nav Item - My Reservation -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/reservations/user' />">
                <i class="fas fa-fw fa-star"></i>
                <span>My reservations</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/cars/top-rated' />">
                <i class="fas fa-fw fa-star"></i>
                <span>Top rated</span>
            </a>
        </li>
        <!-- Nav Item - Contact -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/contact/form' />">
                <i class="fas fa-fw fa-envelope"></i>
                <span>Contact</span>
            </a>
        </li>
    </ul>
    <!-- End of Left Sidebar -->

    <div id="content-wrapper" class="d-flex flex-column content-wrapper">
        <div id="content">
            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>
                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                ${sessionScope.user.username}
                            </span>
                            <img class="img-profile rounded-circle" src="<c:url value='/static/img/user_image.jpg' />" alt="User profile image">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<c:url value='/profile' />">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profil
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<c:url value='/users/logout' />">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Log out
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <!-- End of Topbar -->
        </div>
    </div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp -->
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Car Rental</title>
    <link href="<c:url value='/static/vendor/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/static/css/sb-admin-2.min.css' />" rel="stylesheet">
    <style>
        .main-content {
            display: flex;
            align-items: center;
            justify-content: center;
            height: calc(100vh - 56px); /* Subtract the height of the topbar */
            flex-direction: column;
        }
        .main-content img {
            max-width: 100%;
            height: auto;
        }
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
        .nav-link-login {
            font-size: 1.2rem;
            font-weight: bold;
        }
        .login-message {
            text-align: center;
            margin-top: 20px;
            font-size: 1.2rem;
            color: #dc3545; /* Red color for emphasis */
        }
        .rental-form {
            width: 100%;
            max-width: 600px;
            margin-top: 20px;
        }
        .rental-form input, .rental-form select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Left Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion sidebar-left" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Car Rental</div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Cars List -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/cars' />">
                <i class="fas fa-fw fa-car"></i>
                <span>Lista samochodów</span>
            </a>
        </li>
        <!-- Nav Item - Top Rated -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/top-rated' />">
                <i class="fas fa-fw fa-star"></i>
                <span>Najlepiej oceniane</span>
            </a>
        </li>
        <!-- Nav Item - Contact -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/contact' />">
                <i class="fas fa-fw fa-envelope"></i>
                <span>Kontakt</span>
            </a>
        </li>
    </ul>
    <!-- End of Left Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
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
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                            ${sessionScope.user.username}
                                    </span>
                                    <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="<c:url value='/logout' />">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link nav-link-login" href="<c:url value='/login' />">
                                    <i class="fas fa-fw fa-sign-in-alt"></i>
                                    <span>Login</span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </nav>
            <!-- End of Topbar -->

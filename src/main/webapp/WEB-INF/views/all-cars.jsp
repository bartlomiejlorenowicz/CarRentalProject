<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header-conditional.jsp" />

<div id="wrapper">
<%--    <!-- Left Sidebar -->--%>
<%--    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion sidebar-left" id="accordionSidebar">--%>
<%--        <!-- Sidebar - Brand -->--%>
<%--        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">--%>
<%--            <div class="sidebar-brand-icon rotate-n-15">--%>
<%--                <i class="fas fa-laugh-wink"></i>--%>
<%--            </div>--%>
<%--            <div class="sidebar-brand-text mx-3">Car Rental</div>--%>
<%--        </a>--%>
<%--        <!-- Divider -->--%>
<%--        <hr class="sidebar-divider my-0">--%>
<%--        <!-- Nav Item - Cars List -->--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="<c:url value='/cars' />">--%>
<%--                <i class="fas fa-fw fa-car"></i>--%>
<%--                <span>Lista samochodów</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <!-- Nav Item - My Reservation -->--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="<c:url value='/reservations/user' />">--%>
<%--                <i class="fas fa-fw fa-star"></i>--%>
<%--                <span>Moje rezerwacje</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <!-- Nav Item - Contact -->--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="<c:url value='/contact' />">--%>
<%--                <i class="fas fa-fw fa-envelope"></i>--%>
<%--                <span>Kontakt</span>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--    <!-- End of Left Sidebar -->--%>

    <div id="content-wrapper" class="d-flex flex-column content-wrapper">
        <div id="content">
<%--            <!-- Topbar -->--%>
<%--            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">--%>
<%--                <!-- Sidebar Toggle (Topbar) -->--%>
<%--                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">--%>
<%--                    <i class="fa fa-bars"></i>--%>
<%--                </button>--%>
<%--                <!-- Topbar Navbar -->--%>
<%--                <ul class="navbar-nav ml-auto">--%>
<%--                    <!-- Nav Item - User Information -->--%>
<%--                    <li class="nav-item dropdown no-arrow">--%>
<%--                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"--%>
<%--                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">--%>
<%--                                ${sessionScope.user.username}--%>
<%--                            </span>--%>
<%--                            <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">--%>
<%--                        </a>--%>
<%--                        <!-- Dropdown - User Information -->--%>
<%--                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">--%>
<%--                            <a class="dropdown-item" href="<c:url value='/profile' />">--%>
<%--                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>--%>
<%--                                Profil--%>
<%--                            </a>--%>
<%--                            <div class="dropdown-divider"></div>--%>
<%--                            <a class="dropdown-item" href="<c:url value='/users/logout' />">--%>
<%--                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>--%>
<%--                                Wyloguj się--%>
<%--                            </a>--%>
<%--                        </div>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
<%--            <!-- End of Topbar -->--%>

            <div class="container-fluid">
                <h1 class="h3 mb-4 text-gray-800">Lista Samochodów</h1>
                <a href="<c:url value='/users/dashboard' />" class="btn btn-secondary btn-back">Powrót do dashboardu</a>
                <div class="row">
                    <c:forEach var="car" items="${cars}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">${car.make} ${car.model}</h6>
                                </div>
                                <div class="card-body">
                                    <p><strong>Rok:</strong> ${car.year}</p>
                                    <p><strong>Typ:</strong> ${car.type}</p>
                                    <p><strong>Cena:</strong> ${car.price}</p>
                                    <a href="<c:url value='/cars/review?carId=${car.carId}' />" class="btn btn-primary">Oceń</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

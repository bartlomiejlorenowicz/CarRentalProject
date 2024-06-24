<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="header-conditional.jsp" />

<div class="container">
    <h2>Rejestracja</h2>
    <form action="${pageContext.request.contextPath}/users/register" method="post">
        <div class="form-group">
            <label for="username">Nazwa użytkownika:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Hasło:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="street">Ulica:</label>
            <input type="text" class="form-control" id="street" name="street" required>
        </div>
        <div class="form-group">
            <label for="city">Miasto:</label>
            <input type="text" class="form-control" id="city" name="city" required>
        </div>
        <div class="form-group">
            <label for="zipCode">Kod pocztowy:</label>
            <input type="text" class="form-control" id="zipCode" name="zipCode" required>
        </div>
        <div class="form-group">
            <label for="country">Kraj:</label>
            <input type="text" class="form-control" id="country" name="country" required>
        </div>
        <button type="submit" class="btn btn-primary">Zarejestruj</button>
    </form>
</div>

<jsp:include page="footer.jsp" />

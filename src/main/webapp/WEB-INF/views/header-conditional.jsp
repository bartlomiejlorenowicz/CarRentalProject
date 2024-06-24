<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <jsp:include page="header-logged-in.jsp" />
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp" />
    </c:otherwise>
</c:choose>

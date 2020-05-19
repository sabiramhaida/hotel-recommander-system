<%@ page import="Dao.Hotel_Dao" %>
<%@ page import="Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@include file="UI_Components/navbar.jsp" %>


<c:forEach items="${hotels}" var="hotel">
    Item <c:out value = "${hotel.name}"/><p>
</c:forEach>


<%@include file="UI_Components/footer.jsp" %>
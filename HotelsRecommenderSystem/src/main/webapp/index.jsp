<%@ page import="Model.Hotel" %>
<%@ page import="Dao.Hotel_Dao" %>
<%@ page import="java.util.List" %>
<%@include file="UI_Components/navbar.jsp" %>
<c:set var = "hotels" scope = "session" value = "${Hotel_Dao.getHotels()}"/>
<!--


<c:forEach items="${hotels}" var="hotel">
    <c:out value="{hotel.getName()}"/>
    <c:out value="{hotel.getCountry()}"/>
</c:forEach>
<%@include file="UI_Components/footer.jsp" %>
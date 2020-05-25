<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="Dao.Hotel_Dao" %>
<%@ page import="Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@include file="UI_Components/navbar.jsp" %>
<!-- Page top Section end -->


<!-- Page top Section end -->

<!-- Single Property Section end -->
<section class="single-property-section spad" style="margin-top: 50px">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="single-property">
                    <div class="sp-image">
                        <img src="img/property/big.jpg" alt="">
                        <div class="sp-badge new">New</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="property-header">
                                <h3><c:out value="${hotel.name}"/>
                                </h3>
                                <h5><c:out value="${hotel.country}"/>, <c:out value="${hotel.region}"/>
                                </h5>
                            </div>
                        </div>
                        <div class="col-lg-4 text-left text-lg-right">
                            <div class="property-header">
                                <h3><c:out value="${hotel.price}"/>
                                </h3>
                            </div>
                        </div>
                    </div>

                    <div class="property-text">
                        <h4>Description</h4>
                        <div class="row justify-content-center">
                            <div class="col-lg-4">
                                <h5 style="margin-bottom: 20px">Property amenties</h5>
                                <ul class="list-group list-group-flush">
                                    <c:forEach items="${hotel.property_amenties}" var="property">
                                        <li class="list-group-item"><c:out value="${property}"/>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <div class="col-lg-4">
                                <h5 style="margin-bottom: 20px">Room features</h5>
                                <ul class="list-group list-group-flush">
                                    <c:forEach items="${hotel.room_features}" var="property">
                                        <li class="list-group-item"><c:out value="${property}"/>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>

                            <div class="col-lg-4">
                                <h5 style="margin-bottom: 20px">Hotel style</h5>
                                <ul class="list-group list-group-flush">
                                    <c:forEach items="${hotel.hotel_style}" var="property">

                                        <li class="list-group-item"><c:out value="${property}"/>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>
                        </div>


                    </div>
                    <div style="margin-top: 30px">
                        <h4 style="margin-bottom: 30px">Detail</h4>
                        <div class="property-feature">
                            <div class="row">
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Hotel score reviews</h6>
                                        <p><c:out value="${hotel.hotel_score_reviews}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Location score</h6>
                                        <p><c:out value="${hotel.location_score}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Cleanliness score</h6>
                                        <p><c:out value="${hotel.cleanliness_score}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Service score</h6>
                                        <p><c:out value="${hotel.service_score}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Value score</h6>
                                        <p><c:out value="${hotel.value_score}"/>
                                        </p>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- Property Section -->
                <section class="property-section">
                    <div class="container">
                        <div class="section-title">
                            <h2>Similar hotels</h2>
                        </div>
                        <div class="row">
                            <c:forEach items="${recommended_hotels}" var="hotel">
                                <div class="col-lg-3">
                                    <div class="property-item">
                                        <div class="pi-image">
                                            <img src="img/property/1.jpg" alt="">
                                            <div class="pi-badge new">New</div>
                                        </div>
                                        <h3>$<c:out value="${hotel.price}"/></h3>
                                        <h5><c:out value="${hotel.name}"/></h5>
                                        <p>Hotel class : <c:out value="${hotel.hotel_class}"/></p>
                                        <h5><c:out value="${hotel.country}"/></h5>
                                        <a href="./hotelPage?hotel_id=<c:out value = "${hotel.hotelId}"/>" class="readmore-btn">Find out more</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </section>

                <!-- Property Section end -->
            </div>

    </div>
</section>
<!-- Section end -->
<%@include file="UI_Components/footer.jsp" %>
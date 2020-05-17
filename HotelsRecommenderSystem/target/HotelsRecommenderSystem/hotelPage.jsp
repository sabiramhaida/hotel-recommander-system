<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="Dao.Hotel_Dao" %>
<%@ page import="Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@include file="UI_Components/navbar.jsp" %>
<!-- Page top Section end -->
<%
    Hotel hotel = (Hotel) request.getAttribute("Hotel");
%>
<section class="page-top-section set-bg" data-setbg="img/page-top-bg.jpg">
    <div class="page-top-warp">
        <form class="main-search-form">
            <div class="search-type">
                <div class="st-item">
                    <input type="radio" name="st" id="buy" checked>
                    <label for="buy">Buy</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="rent">
                    <label for="rent">Rent</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="sell">
                    <label for="sell">Sell</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="property">
                    <label for="property">Property Value</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="agents">
                    <label for="agents">Agents</label>
                </div>
            </div>
            <div class="search-input si-v-2">
                <input type="text" placeholder="Search by state, postcode or suburb">
                <button class="site-btn" type="submit">Search</button>
                <button class="site-btn sb-light">Show Filters</button>
            </div>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Quis ipsum suspendisse ultrices gravida. </p>
        </form>
    </div>
</section>
<!-- Page top Section end -->

<!-- Single Property Section end -->
<section class="single-property-section spad">
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
                                <h3><%=hotel.getName()%>
                                </h3>
                                <h5><%=hotel.getCountry()%>, <%=hotel.getRegion()%>
                                </h5>
                            </div>
                        </div>
                        <div class="col-lg-4 text-left text-lg-right">
                            <div class="property-header">
                                <h3><%=hotel.getPrice()%>
                                </h3>
                                <p>3 Bedrooms Townhouse</p>
                            </div>
                        </div>
                    </div>
                    <div class="property-info-bar">
                        <div class="row">
                            <div class="col-lg-7">
                                <div class="pi-metas">
                                    <div class="pi-meta">3 Bed</div>
                                    <div class="pi-meta">2 Baths</div>
                                    <div class="pi-meta">1 Garage</div>
                                    <div class="pi-meta">2849 SF</div>
                                </div>
                            </div>
                            <div class="col-lg-5 text-left text-lg-right">
                                <a href="#" class="offer-btn">Make an Offer</a>
                            </div>
                        </div>
                    </div>
                    <div class="property-text">
                        <h4>Description</h4>
                        <div class="row justify-content-center">
                            <div class="col-lg-4">
                                <h5 style="margin-bottom: 20px">Property amenties</h5>
                                <ul class="list-group list-group-flush">
                                    <%
                                        for (String property_amenty : hotel.getProperty_amenties()) {
                                    %>
                                    <li class="list-group-item"><%=property_amenty%>
                                    </li>
                                    <% }%>
                                </ul>
                            </div>

                            <div class="col-lg-4">
                                <h5 style="margin-bottom: 20px">Room features</h5>
                                <ul class="list-group list-group-flush">
                                    <%
                                        for (String room_feature : hotel.getRoom_features()) {
                                    %>
                                    <li class="list-group-item"><%=room_feature%>
                                    </li>
                                    <% }%>
                                </ul>
                            </div>

                            <div class="col-lg-4">
                                <h5 style="margin-bottom: 20px">Hotel style</h5>
                                <ul class="list-group list-group-flush">
                                    <%
                                        for (String hotel_style : hotel.getHotel_style()) {
                                    %>
                                    <li class="list-group-item"><%=hotel_style%>
                                    </li>
                                    <% }%>
                                </ul>
                            </div>
                        </div>


                    </div>
                    <div style="margin-top: 30px">
                        <h4 style="margin-bottom: 30px">Detail</h4>
                        <div class="property-feature" >
                            <div class="row">
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Hotel score reviews</h6>
                                        <p><%=hotel.getHotel_score_reviews()%>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Location score</h6>
                                        <p><%=hotel.getLocation_score()%>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Cleanliness score</h6>
                                        <p><%=hotel.getCleanliness_score()%>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Service score</h6>
                                        <p><%=hotel.getService_score()%>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="pf-box">
                                        <h6>Value score</h6>
                                        <p><%=hotel.getService_score()%>
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
                            <%
                                List<String> hotelNames = (List<String> ) request.getAttribute("recommended_hotels");
                                List<Hotel> hotelsRecommendedList = new Hotel_Dao().getHotelsByName(hotelNames);
                                for (Hotel hotelRecommended : hotelsRecommendedList) {
                            %>
                            <div class="col-lg-3">
                                <div class="property-item">
                                    <div class="pi-image">
                                        <img src="img/property/1.jpg" alt="">
                                        <div class="pi-badge new">New</div>
                                    </div>
                                    <h3>$<%=hotelRecommended.getPrice()%></h3>
                                    <h5><%=hotelRecommended.getName()%></h5>
                                    <p>Hotel class : <%=hotelRecommended.getHotel_class()%></p>
                                    <h5><%=hotelRecommended.getCountry()%></h5>
                                    <a href="#" class="readmore-btn">Find out more</a>
                                </div>
                            </div>
                            <% }%>
                        </div>

                    </div>
                </section>

                <!-- Property Section end -->
            </div>
            <div class="col-lg-4 col-md-8 sidebar">
                <div class="agent-widget">
                    <img src="img/agents/1.jpg" alt="">
                    <div class="aw-text">
                        <h4>Christinne James</h4>
                        <h6>Real Estate Agent</h6>
                        <p>Fusce lobortis a enim eget tempus. Class aptent taciti sociosqu ad litora. Donec eget
                            efficitur ex. Donec eget dolor vitae eros feugiat tristique id vitae massa. </p>
                        <a href="#" class="readmore-btn">Contact the agent</a>
                    </div>
                </div>
                <div class="map-widget">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d14376.077865872314!2d-73.879277264103!3d40.757667781624285!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sbd!4v1546528920522"
                            style="border:0" allowfullscreen></iframe>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Section end -->
<%@include file="UI_Components/footer.jsp" %>
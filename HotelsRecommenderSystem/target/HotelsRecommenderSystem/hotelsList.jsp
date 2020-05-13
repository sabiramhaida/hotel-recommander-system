<%@ page import="Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.Hotel_Dao" %>
<%@include file="UI_Components/navbar.jsp" %>

<!-- Page top Section end -->
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
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum suspendisse ultrices gravida. </p>
        </form>
    </div>
</section>
<!-- Page top Section end -->

<!-- Blog Section end -->
<section class="blog-section spad">
    <div class="container">
        <div class="row">
            <%
                List<Hotel> hotels = new Hotel_Dao().getHotels();
                for(Hotel hotel : hotels){
            %>
            <div class="col-lg-4 col-md-6">
                <div class="blog-item">
                    <img src="img/blog/1.jpg" alt="">
                    <div class="blog-text">
                        <div class="blog-date">$<%= hotel.getPrice()%></div>
                        <h4><%= hotel.getName()%></h4>
                        <p>Fusce lobortis a enim eget tempus. Class aptent taciti sociosqu ad litora. Donec eget efficitur ex. Donec eget dolor vitae eros feugiat tristique id vitae massa. </p>
                        <div class="blog-date"><%= hotel.getCountry()%> </div>
                        <a href="./hotelPage?hotel_id=<%= hotel.getHotelId()%>" class="readmore-btn">Read More</a>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>

        <div class="site-pagination">
            <a href="#">01.</a>
            <a href="#" class="active">02.</a>
            <a href="#">03.</a>
            <a href="#">04.</a>
        </div>
    </div>
</section>
<!-- Blog Section end -->
<%@include file="UI_Components/footer.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.Hotel_Dao" %>
<%@include file="UI_Components/navbar.jsp" %>

<!-- Page top Section end -->
<section id="searchSection" class="page-top-section set-bg" data-setbg="img/page-top-bg.jpg" style="height: 500px">
    <div class="page-top-warp">
            <div class="search-type" id="searchDivFloor1">
                <div class="st-item">
                    <input type="radio" name="st" id="name" checked>
                    <label for="name">Hotel name</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="price">
                    <label for="price">Price</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="class">
                    <label for="class">Hotel class</label>
                </div>
                <div class="st-item">
                    <input type="radio" name="st" id="country">
                    <label for="country">Country</label>
                </div>
            </div>
        <div id="searchBasedOnValue">
            <div class="search-input si-v-2">
                <input id="search_content" type="text" placeholder="Search by state, postcode or suburb">
                <button id="search" class="site-btn">Search</button>
            </div>
        </div>
        <div id="searchBasedOnRange" >
            <div class="search-input si-v-2">
                <input id="minValue" type="number" name="min" placeholder="Min">
            </div>
            <div class="search-input si-v-2">
                <input id="maxValue" type="number" name="max" placeholder="Max">
            </div>
            <button id="search_range" class="site-btn">Search</button>
        </div>
    </div>
</section>


<!-- Page top Section end -->

<!-- Blog Section end -->
<section class="blog-section spad">
    <div class="container paginatedItemsContainer">
        <div class="row content1">
<c:forEach items="${hotels}" var="hotel">

            <div class="col-lg-4 col-md-6">
                <div class="blog-item">
                    <img src="img/blog/1.jpg" alt="">
                    <div class="blog-text">
                        <div class="blog-date">$<c:out value = "${hotel.price}"/></div>
                        <h4><c:out value = "${hotel.name}"/></h4>
                        <p>Adresse : <c:out value = "${hotel.location}"/> </p>
                        <div class="blog-date"><c:out value = "${hotel.country}"/> </div>
                        <a href="./hotelPage?hotel_id=<c:out value = "${hotel.hotelId}"/>" class="readmore-btn">View hotel</a>
                    </div>
                </div>
            </div>
</c:forEach>
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
<script>
    $(document).ready(function(){
        $("#searchBasedOnRange").hide();
    });
    $("#price").click(function(){
        $("#searchBasedOnValue").hide();
        console.log("click is working");
        $("#searchBasedOnRange").show();
    });
    $("#class").click(function(){
        $("#searchBasedOnValue").hide();
        $("#searchBasedOnRange").show();
    });
    $("#name").click(function(){
        $("#searchBasedOnValue").show();
        $("#searchBasedOnRange").hide();
    });
    $("#country").click(function(){
        $("#searchBasedOnValue").show();
        $("#searchBasedOnRange").hide();
    });
</script>
<script>

    $( "#search" ).click(function() {
        if($('#name').is(':checked')) {
            var name = $("#search_content").val();
            window.location.href = '?filter=name&name=' + name;
        }
        else if($('#country').is(':checked')){
            var name = $("#search_content").val();
            window.location.href = '?filter=country&name=' + name;
        }
    });
    $( "#search_range" ).click(function() {
        if($('#price').is(':checked')) {
            var min = $("#minValue").val();
            var max = $("#maxValue").val();
            window.location.href = '?filter=price&min=' + min +'&max=' + max;
        }
        else if($('#class').is(':checked')){
            var min = $("#minValue").val();
            var max = $("#maxValue").val();
            window.location.href = '?filter=class&min=' + min +'&max=' + max;
        }
    });
</script>

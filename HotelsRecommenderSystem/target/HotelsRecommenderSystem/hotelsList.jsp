<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.Hotel_Dao" %>
<%@include file="UI_Components/navbar.jsp" %>
<style>
    .pagination > .active > a
    {
        color: white;
        background-color: red !Important;
        border: solid 1px red !Important;
        color: white !Important;
    }

    .pagination > li > a
    {
        background-color: white;
        color: grey !Important;
    }
</style>


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
                <input id="search_content" type="text" placeholder="Type here !!!">
                <button id="search" class="site-btn">Search</button>
            </div>
        </div>
        <div id="searchBasedOnRange">
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
<section class="content blog-section spad">
    <div class="container paginatedItemsContainer ">
        <div class="row" id="jar">
            <c:forEach items="${hotels}" var="hotel">

                <div class="col-lg-4 col-md-6">
                    <div class="blog-item">
                        <img src="img/blog/1.jpg" alt="">
                        <div class="blog-text">
                            <div class="blog-date">$<c:out value="${hotel.price}"/></div>
                            <h4><c:out value="${hotel.name}"/></h4>
                            <p>Adresse : <c:out value="${hotel.location}"/></p>
                            <div class="blog-date"><c:out value="${hotel.country}"/></div>
                            <a href="./hotelPage?hotel_id=<c:out value = "${hotel.hotelId}"/>" class="readmore-btn">View
                                hotel</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div style="margin-bottom: 40px">
            <nav>
                <ul class="pagination justify-content-center pagination-sm ">
                </ul>
            </nav>
        </div>



    </div>
</section>
<!-- Blog Section end -->
<%@include file="UI_Components/footer.jsp" %>
<script>
    $(document).ready(function () {
        $("#searchBasedOnRange").hide();
    });
    $("#price").click(function () {
        $("#searchBasedOnValue").hide();
        console.log("click is working");
        $("#searchBasedOnRange").show();
    });
    $("#class").click(function () {
        $("#searchBasedOnValue").hide();
        $("#searchBasedOnRange").show();
    });
    $("#name").click(function () {
        $("#searchBasedOnValue").show();
        $("#searchBasedOnRange").hide();
    });
    $("#country").click(function () {
        $("#searchBasedOnValue").show();
        $("#searchBasedOnRange").hide();
    });
</script>
<script>

    $("#search").click(function () {
        if ($('#name').is(':checked')) {
            var name = $("#search_content").val();
            window.location.href = '?filter=name&name=' + name;
        } else if ($('#country').is(':checked')) {
            var name = $("#search_content").val();
            window.location.href = '?filter=country&name=' + name;
        }
    });
    $("#search_range").click(function () {
        if ($('#price').is(':checked')) {
            var min = $("#minValue").val();
            var max = $("#maxValue").val();
            window.location.href = '?filter=price&min=' + min + '&max=' + max;
        } else if ($('#class').is(':checked')) {
            var min = $("#minValue").val();
            var max = $("#maxValue").val();
            window.location.href = '?filter=class&min=' + min + '&max=' + max;
        }
    });
</script>



<script type="text/javascript">
    function getPageList(totalPages, page, maxLength) {
        if (maxLength < 5) throw "maxLength must be at least 5";

        function range(start, end) {
            return Array.from(Array(end - start + 1), (_, i) => i + start);
        }

        var sideWidth = maxLength < 9 ? 1 : 2;
        var leftWidth = (maxLength - sideWidth * 2 - 3) >> 1;
        var rightWidth = (maxLength - sideWidth * 2 - 2) >> 1;
        if (totalPages <= maxLength) {
            // no breaks in list
            return range(1, totalPages);
        }
        if (page <= maxLength - sideWidth - 1 - rightWidth) {
            // no break on left of page
            return range(1, maxLength - sideWidth - 1)
                .concat([0])
                .concat(range(totalPages - sideWidth + 1, totalPages));
        }
        if (page >= totalPages - sideWidth - 1 - rightWidth) {
            // no break on right of page
            return range(1, sideWidth)
                .concat([0])
                .concat(
                    range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages)
                );
        }
        // Breaks on both sides
        return range(1, sideWidth)
            .concat([0])
            .concat(range(page - leftWidth, page + rightWidth))
            .concat([0])
            .concat(range(totalPages - sideWidth + 1, totalPages));
    }

    $(function() {
        // Number of items and limits the number of items per page
        var numberOfItems = $("#jar > div").length;
        console.log('Total length is : ' + numberOfItems);
        var limitPerPage = 6;
        // Total pages rounded upwards
        var totalPages = Math.ceil(numberOfItems / limitPerPage);
        // Number of buttons at the top, not counting prev/next,
        // but including the dotted buttons.
        // Must be at least 5:
        var paginationSize = 5;
        var currentPage;

        function showPage(whichPage) {
            if (whichPage < 1 || whichPage > totalPages) return false;
            currentPage = whichPage;
            $("#jar > div")
                .hide()
                .slice((currentPage - 1) * limitPerPage, currentPage * limitPerPage)
                .show();
            // Replace the navigation items (not prev/next):
            $(".pagination li").slice(1, -1).remove();
            getPageList(totalPages, currentPage, paginationSize).forEach(item => {
                $("<li>")
                    .addClass(
                        "page-item " +
                        (item ? "current-page " : "") +
                        (item === currentPage ? "active " : "")
                    )
                    .append(
                        $("<a>")
                            .addClass("page-link")
                            .attr({
                                href: "javascript:void(0)",
                                color : "red"
                            })
                            .text(item || "...")
                    )
                    .insertBefore("#next-page");
            });
            return true;
        }

        // Include the prev/next buttons:
        $(".pagination").append(
            $("<li>").addClass("page-item").attr({ id: "previous-page" }).append(
                $("<a>")
                    .addClass("page-link")
                    .attr({
                        href: "javascript:void(0)"
                    })
                    .text("Prev")
            ),
            $("<li>").addClass("page-item").attr({ id: "next-page" }).append(
                $("<a>")
                    .addClass("page-link")
                    .attr({
                        href: "javascript:void(0)"
                    })
                    .text("Next")
            )
        );
        // Show the page links
        $("#jar").show();
        showPage(1);

        // Use event delegation, as these items are recreated later
        $(
            document
        ).on("click", ".pagination li.current-page:not(.active)", function() {
            return showPage(+$(this).text());
        });
        $("#next-page").on("click", function() {
            return showPage(currentPage + 1);
        });

        $("#previous-page").on("click", function() {
            return showPage(currentPage - 1);
        });
        $(".pagination").on("click", function() {
            $("html,body").animate({ scrollTop: 0 }, 0);
        });
    });


</script>
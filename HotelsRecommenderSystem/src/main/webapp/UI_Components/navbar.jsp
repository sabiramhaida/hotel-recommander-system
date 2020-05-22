
<!DOCTYPE html>
<html lang="zxx">
<head>
    <title>Mondy | Real Estate HTML Template</title>
    <meta charset="UTF-8">
    <meta name="description" content="Real estate HTML Template">
    <meta name="keywords" content="real estate, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="shortcut icon"/>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i,900%7cRoboto:400,400i,500,500i,700,700i&display=swap" rel="stylesheet">


    <!-- Stylesheets -->
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/slicknav.min.css"/>

    <!-- Main Stylesheets -->
    <link rel="stylesheet" href="css/style.css"/>


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header Section -->
<header class="header-section">
    <a href="index.html" class="site-logo">
        <img src="img/logo.png" alt="">
    </a>
    <nav class="header-nav">
        <ul class="main-menu">
            <li><a href="./homepage" class="active">Home</a></li>
            <li><a href="./hotelsList?filter=all">Hotels list</a></li>
            <li><a href="./contact">Contact</a></li>
            <li><a href="./about">About us</a></li>
        </ul>
        <div class="header-right">
            <div class="user-panel">
                <%if (session.getAttribute("username")!=null){%>
                <a href="./userArea" class="login"> <%=session.getAttribute("username")%> </a>
                <%}else{%>
                <a href="./signInPage" class="login">Sign in</a>
                <a href="./signUpPage" class="register">Join us</a>
                <%}%>
            </div>
        </div>
    </nav>
</header>
<!-- Header Section end -->


<%@include file="UI_Components/navbar.jsp" %>
<html>

<head>
    <link rel="stylesheet" href="css/joinstyle.css">
    <link rel="stylesheet" href="css/loginFont.css">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <title>Sign in</title>
</head>

<body>

<div class="main">
    <p class="sign" align="center">Sign Up </p>
        <form class="form1" action="./signUpPage" method="post">
        <input class="un " type="text" align="center" placeholder="Username" name = "username">
        <input class="un " type="text" align="center" placeholder="Full Name" name = "fullname">
        <input class="pass" type="password" align="center" placeholder="Password" name = "password">
        <input class="pass" type="text" align="center" placeholder="country" name = "country">
        <input class="pass" type="text" align="center" placeholder="city" name = "city">
        <button class="submit"  type = "submit" align="center">Create Account</button>
        <p class="forgot" align="center"><a href="./signInPage">Do you have  already an Account? Sign in !</a></p>

    </form>
</div>
</body>
</html>




<%@include file="UI_Components/footer.jsp" %>
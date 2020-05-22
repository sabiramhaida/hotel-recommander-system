<%@include file="UI_Components/navbar.jsp" %>
<html>

<head>
<link rel="stylesheet" href="css/loginStyle.css">
<link rel="stylesheet" href="css/loginFont.css">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<title>Sign in</title>
</head>

<body>

<div class="main">
<p class="sign" align="center">Sign in</p>
<form class="form1" action="./signInPage" method="post">
<input class="un " type="text" align="center" placeholder="Username" name = "username">
<input class="pass" type="password" align="center" placeholder="Password" name = "password">
<button class="submit"  type = "submit" align="center">Sign in</button>
<p class="forgot" align="center"><a href="./signUpPage">Don't have an Account? Register Now !</a></p>

</form>
</div>
</body>
</html>




<%@include file="UI_Components/footer.jsp" %>
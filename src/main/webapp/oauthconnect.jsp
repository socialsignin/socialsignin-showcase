<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <html>
 <head>
 </head>
 <body>
   <p>


<p>Please connect your local account with another third party provider</p>
<authz:authorize access="!hasRole('ROLE_USER_FACEBOOK')">

  <form class="login"action="http://localhost:8080/connect/facebook" method="POST">
	<p><input type="submit" value="Connect with Facebook" /></p>
</form> 
</authz:authorize>

<authz:authorize access="!hasRole('ROLE_USER_TWITTER')">

 <form class="login"action="http://localhost:8080/connect/twitter" method="POST">
	<p><input type="submit" value="Connect with Twitter" /></p>
</form> 
</authz:authorize>

<authz:authorize access="!hasRole('ROLE_USER_LASTFM')">

 <form class="login"action="http://localhost:8080/connect/lastfm" method="POST">
	<p><input type="submit" value="Connect with LastFm" /></p>
</form> 
</authz:authorize>

<authz:authorize access="!hasRole('ROLE_USER_MIXCLOUD')">

 <form class="login"action="http://localhost:8080/connect/mixcloud" method="POST">
	<p><input type="submit" value="Connect with Mixcloud" /></p>
</form> 
</authz:authorize>

<authz:authorize access="!hasRole('ROLE_USER_LINKEDIN')">

 <form class="login"action="http://localhost:8080/connect/linkedin" method="POST">
	<p><input type="submit" value="Connect with LinkedIn" /></p>
</form> 
</authz:authorize>

</body>
</html>


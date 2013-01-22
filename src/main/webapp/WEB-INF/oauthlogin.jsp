<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>



<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <authz:authorize access="!hasRole('ROLE_USER')">


<h2>Please log in with any third party provider you have previously connected with SocialSignin Showcase</h2>
<p>(Or if this is your first login to SocialSignin Showcase, choose any provider to create an account)
<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">


  <form class="login"action="/signin/<c:out value="${entry.key}" />" method="POST">
	<p><input type="submit" value="Login with <c:out value="${entry.key}" />" /></p>
</form> 


</c:forEach>


</authz:authorize>

 <authz:authorize access="hasRole('ROLE_USER')">
	
	You are already logged in
 
 </authz:authorize>

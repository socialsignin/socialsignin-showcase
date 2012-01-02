<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>



<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <authz:authorize access="!hasRole('ROLE_USER')">


<p>Please log in with a third party provider</p>

<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">


  <form class="login"action="http://localhost:8080/signin/<c:out value="${entry.key}" />" method="POST">
	<p><input type="submit" value="Login with <c:out value="${entry.key}" />" /></p>
</form> 


</c:forEach>


</authz:authorize>

 <authz:authorize access="hasRole('ROLE_USER')">
	
	You are already logged in
 
 </authz:authorize>

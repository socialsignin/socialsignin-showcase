<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>

   <p>


<p>Please connect your local account with another third party provider</p>


<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">


<authz:authorize access="!hasRole('${entry.value}')">

  <form class="login" action="http://localhost:8080/connect/<c:out value="${entry.key}" />" method="POST">
	<p><input type="submit" value="Connect with <c:out value="${entry.key}" />" /></p>
</form> 
</authz:authorize>


</c:forEach>


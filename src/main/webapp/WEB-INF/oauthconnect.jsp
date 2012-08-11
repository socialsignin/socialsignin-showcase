<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>
<tiles:useAttribute name="provider" ignore="true" />

<c:if test="${not empty exception}">
<p><c:out value="${exception.message}" /></p>
</c:if>


<p>Please connect your local account with 
<c:choose>
<c:when test="${not empty provider}">
<c:out value="${provider}" />
</c:when>
<c:otherwise>
another third party provider
</c:otherwise>
</c:choose>
</p>


<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">

<c:if test="${empty provider or provider eq entry.key}">


<authz:authorize access="!hasRole('${entry.value}')">

  <form class="login" action="http://localhost:8080/connect/<c:out value="${entry.key}" />" method="POST">
	<p><input type="submit" value="Connect with <c:out value="${entry.key}" />" /></p>
</form> 
</authz:authorize>

</c:if>


</c:forEach>


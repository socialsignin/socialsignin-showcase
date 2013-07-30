<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>
<tiles:useAttribute name="provider" ignore="true" />

<c:if test="${not empty exception}">
<p><c:out value="${exception.message}" /></p>

</c:if>


<h2>Please connect your SocialSignin Showcase account with 
<c:choose>
<c:when test="${not empty provider}">
<c:out value="${provider}" />
</c:when>
<c:otherwise>
another third party provider
</c:otherwise>
</c:choose>
</h2>


<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">

<c:if test="${empty provider or provider eq entry.key}">


<authz:authorize access="!hasRole('${entry.value}')">

  <form class="login" action="/connect/<c:out value="${entry.key}" />" method="POST">
	<p><input type="submit" value="Connect with <c:out value="${entry.key}" />" /></p>
</form> 
</authz:authorize>
<authz:authorize access="hasRole('${entry.value}')">
<c:if test="${not empty provider and provider eq entry.key}">
Please logout and login again with <c:out value="${provider}" /> to reconnect
</c:if>

</authz:authorize>


</c:if>


</c:forEach>


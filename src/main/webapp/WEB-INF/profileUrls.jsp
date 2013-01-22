<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>

<p><a href="/">Home</a></p>

<c:if test="${not empty profileUrls}">

<p>Your profile urls:</p>

<c:forEach var="profileUrl"
	items="${profileUrls}">
		<p><c:out value="${profileUrl}" /></p>
</c:forEach>

</c:if>



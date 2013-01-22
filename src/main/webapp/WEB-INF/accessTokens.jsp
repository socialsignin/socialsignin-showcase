<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>


<c:if test="${not empty connectionDataList}">

<p>Your access tokens:</p>
<table border=1>
<tr><th>Provider</th><th>Access Token</th></tr>
<c:forEach var="connectionData"
	items="${connectionDataList}">
	<c:if test="${connectionData.providerId != 'springSocialSecurity'}">
		<tr><td><c:out value="${connectionData.providerId}" /></td><td><c:out value="${connectionData.accessToken}" /></td></tr>
	</c:if>
</c:forEach>
</table>
</c:if>



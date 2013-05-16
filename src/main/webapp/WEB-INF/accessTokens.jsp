<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>


<c:if test="${not empty connectionDataList}">
<c:set var="lastFmIncluded" value="false" />
<p>Your access tokens:</p>
<table border=1>
<tr><th>Provider</th><th>Access Token</th><th>Access Token Secret</th></tr>
<c:forEach var="connectionData"
	items="${connectionDataList}">
	<c:if test="${connectionData.providerId != 'springSocialSecurity' and connectionData.providerId != 'lastfm'}">
		<tr><td><c:out value="${connectionData.providerId}" /></td><td><c:out value="${connectionData.accessToken}" /></td><td><c:out value="${connectionData.secret}" /></td></tr>
	</c:if>
	<c:if test="${connectionData.providerId == 'lastfm'}">
	<c:set var="lastFmIncluded" value="true" />
	</c:if>
</c:forEach>
</table>
<c:if test="${lastFmIncluded}">
<p>&nbsp;<p>
<table border=1>
<tr><th>Provider</th><th>Access Token</th><th>Session Key</th></tr>
<c:forEach var="connectionData"
	items="${connectionDataList}">
	<c:if test="${connectionData.providerId != 'springSocialSecurity' and connectionData.providerId == 'lastfm'}">
		<tr><td><c:out value="${connectionData.providerId}" /></td><td><c:out value="${connectionData.accessToken}" /></td><td><c:out value="${connectionData.refreshToken}" /></td></tr>
	</c:if>
</c:forEach>
</table>
</c:if>
</c:if>



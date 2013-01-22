<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>

<c:choose>
<c:when test="${not empty playlistDescriptors}">

<h2>Your Playlists on CloudPlaylists.com</h2>

<c:forEach var="playlist" items="${playlistDescriptors.content}">
    	<p><a target="_blank" href="<c:out value="${playlist.url}" />"><c:out value="${playlist.displayName}" /></a></p>
</c:forEach>
</c:when>
<c:otherwise>
<p>You don't have any playlists yet</p>
</c:otherwise>
</c:choose>



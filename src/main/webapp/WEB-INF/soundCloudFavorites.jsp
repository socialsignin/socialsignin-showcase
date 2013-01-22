<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>

<p>Your latest SoundCloud Favorites:</p>

<c:forEach var="soundCloudFavorite" items="${soundCloudFavorites.content}">
    	<p><a href="<c:out value="${soundCloudFavorite.permalinkUrl}" />" target="_blank"><c:out value="${soundCloudFavorite.title}" /></a></p>
</c:forEach>



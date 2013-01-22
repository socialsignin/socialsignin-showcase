<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>

<p>Your latest ExFm Loved Songs:</p>

<c:forEach var="song" items="${songs.content}">
    	<p><a href="http://ex.fm/song/<c:out value="${song.id}" />" target="_blank"><c:out value="${song.title}" /></a></p>
</c:forEach>



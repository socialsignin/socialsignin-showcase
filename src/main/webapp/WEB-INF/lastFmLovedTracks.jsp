<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>

<p>Your latest LastFm Loved Tracks:</p>

<c:forEach var="track" items="${lastFmLovedTracks.content}">
    	<p><a href="<c:out value="${track.url}" />" target="_blank"><c:out value="${track.artist.name}" /> - <c:out value="${track.name}" /></a></p>
</c:forEach>



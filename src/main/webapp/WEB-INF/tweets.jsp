<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<p><a href="/">Home</a></p>

<p>Your latest tweets:</p>

<c:forEach var="tweet" items="${tweets}">
    	<p><c:out value="${tweet.text}" /></p>
</c:forEach>



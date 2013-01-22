<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="userName"/>

<authz:authorize access="!hasRole('ROLE_USER')">
	<p>You are not logged in. &nbsp;<a href="/login" />Login</a></p>
</authz:authorize>
<authz:authorize access="hasRole('ROLE_USER')">
						<p>You are logged in to SocialSignin Showcase as <c:out value="${userName}" />. &nbsp;<a
		href="/logout">Logout</a></p>
</authz:authorize>
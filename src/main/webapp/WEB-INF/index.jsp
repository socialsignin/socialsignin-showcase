<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:useAttribute name="registeredProviderRoleNamesByProviderName"/>
<tiles:useAttribute name="userName"/>


<h1>SocialSignin Showcase</h1>
<h2>Powered by spring-social</h2>
<p><a href="/accessTokens">View your current access tokens</a></p>
<p><a href="/profileUrls">View your connected profile urls</a></p>

<p><a href="/tweets">View your latest Tweets</a></p>
<p><a href="/exfmLovedSongs">View your latest ExFm Loved Songs</a></p>
<p><a href="/cloudPlaylists">View your Playlists on CloudPlaylists.com</a></p>
<p><a href="/soundCloudFavorites">View your recent SoundCloud Favorites</a></p>
<p><a href="/lastFmLovedTracks">View your recent LastFm loved tracks</a></p>


<authz:authorize access="hasRole('ROLE_USER')">

<h2>Connection Status:</h2>

</authz:authorize>

<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">


	<authz:authorize access="hasRole('${entry.value}')">
		<p>You are connected with <c:out value="${entry.key}" /></p>
	</authz:authorize>

</c:forEach>

<c:forEach var="entry"
	items="${registeredProviderRoleNamesByProviderName}">

	<authz:authorize
		access="hasRole('ROLE_USER') and !hasRole('${entry.value}')">
		<p><a href="/connect/${entry.key}">Connect</a> your account with <c:out
			value="${entry.key}" /></p>
	</authz:authorize>

</c:forEach>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty exception}">
<p><c:out value="${exception.message}" /></p>
</c:if>

<a href="/">Home</a>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>You are creating a new SocialSignin account</h2>
<p>(If you already have a SocialSignin account, please go back and login with the provider you used to register)
<p>Please choose your user name</p>

<form:form action="" method="post" modelAttribute="signUpForm">
<form:input path="userName"/>
<input type="submit" value="Complete Sign Up"/>
<form:errors path="*" />
</form:form>


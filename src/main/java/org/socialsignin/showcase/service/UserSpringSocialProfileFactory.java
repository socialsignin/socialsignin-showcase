package org.socialsignin.showcase.service;

import org.socialsignin.showcase.domain.User;
import org.socialsignin.springsocial.security.signup.AbstractSpringSocialProfileFactory;
import org.springframework.stereotype.Component;

/**
 * The default implementation of SpringSocialSecurity creates instances of 
 * SpringSocialSecurityProfile on user signup
 * 
 * By providing this factory instead we create custom local user details as User objects instead
 * 
 * @author Michael Lavelle
 *
 */
@Component
public class UserSpringSocialProfileFactory extends
		AbstractSpringSocialProfileFactory<User> {

	@Override
	public User instantiate() {
		return new User();
	}

}

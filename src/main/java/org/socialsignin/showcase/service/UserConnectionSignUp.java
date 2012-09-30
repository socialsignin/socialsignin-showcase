package org.socialsignin.showcase.service;

import org.socialsignin.showcase.domain.User;
import org.socialsignin.springsocial.security.signup.AbstractSpringSocialSecurityConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Optional implicit signup can be enabled by setting the connectionSignUp property
 * on the UsersConnectionRepository.
 * 
 * The default implementation of SpringSocialSecurity provides SpringSocialSecurityConnectionSignUp
 * which uses the default SignUpService and SpringSocialSecurityProfileFactory to create
 * SpringSocialProfile local users.
 * 
 * This implementation uses UserSignUpService and UserSpringSocialProfileFactory implementations
 * to create local User accounts, and can be enabled by optionally wiring this component
 * into the UsersConnectionRepository.
 * 
 * @author Michael Lavelle
 */
@Component
public class UserConnectionSignUp extends
AbstractSpringSocialSecurityConnectionSignUp<User, UserSignUpService, UserSpringSocialProfileFactory> {

}

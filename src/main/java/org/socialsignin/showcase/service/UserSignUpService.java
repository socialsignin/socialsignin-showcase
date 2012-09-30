package org.socialsignin.showcase.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.socialsignin.showcase.domain.User;
import org.socialsignin.springsocial.security.signup.AbstractSignUpService;
import org.socialsignin.springsocial.security.signup.UsernameAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The default implementation of SpringSocialSecurity provides a
 * ConnectionRepositorySignUpService which stores local user details as instances of
 * SpringSocialSecurityProfile in the connection repository itself
 * 
 * By providing this implementation of SignUpService instead we enable
 * custom local user details to be stored and initialised on signup as User instances.
 * 
 * @author Michael Lavelle
 *
 */
@Service
public class UserSignUpService extends AbstractSignUpService<User> {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	
	@Override
	public User getUserProfile(String userId) throws UsernameNotFoundException {
		
		User user = entityManager.find(User.class, userId);
		if (user == null) throw new UsernameNotFoundException(userId);
		return user;
		
	}

	@Override
	public boolean isUserIdAvailable(String userId) {
		
		return entityManager.find(User.class, userId) == null;
		
	}

	@Override
	protected void save(User user) throws UsernameAlreadyExistsException {
			
			try
			{
				if (!isUserIdAvailable(user.getUserName()))
				{
					throw new UsernameAlreadyExistsException(user.getUserName());
				}
				
				entityManager.persist(user);
				entityManager.flush();
			}
			catch (PersistenceException e)
			{
				// This optional catch is provided for the edge-case that another user
				// has chosen the same username in the time since we have just checked
				// for username availablity.  Due to the userName being the primary
				// key on the User table, this commit would always fail for this edge
				// case, and would be thrown on transaction commit, needing to be
				// handled at the application level.
				// Flushing and performing this check here gives an opportunity to 
				// handle here if required - throwing UsernameAlreadyExistsException
				// allows the sign up controller to handle this excaption
				if (isConstraintViolationException(e))
				{
					throw new UsernameAlreadyExistsException(user.getUserName());
				}
			}
	}
	
	
	private boolean isConstraintViolationException(PersistenceException e)
	{
		// NB:  This exposes underlying JPA implementation of hibernate, so is provided
		// here for showcase purposes only. A more robust solution could be implemented
		// here, or alternatively this check could be skipped entirely here and handled
		// at the application level
		return e.getCause() != null && e.getCause() instanceof ConstraintViolationException;
	}


}

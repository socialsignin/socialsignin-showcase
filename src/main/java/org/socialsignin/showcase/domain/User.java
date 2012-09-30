package org.socialsignin.showcase.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.socialsignin.springsocial.security.api.SpringSocialProfile;
/**
 * The default implementation of SpringSocialSecurity stores local user details
 * as SpringSocialSecurityProfile instances within the ConnectionRepository itself
 * 
 * By providing this entity we enable custom local user details to be stored
 * and initialised on signup as User instances.
 * 
 * @author Michael Lavelle
 *
 */
@Entity
public class User implements SpringSocialProfile
{

	@Id
	private String userName;
	private String displayName;
	private String imageUrl;
	private String profileUrl;
	private String password;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

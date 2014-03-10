package org.socialsignin.showcase.config.social;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.socialsignin.springsocial.security.connect.SpringSocialSecurityConnectionFactory;
import org.socialsignin.springsocial.security.signup.SpringSocialSecurityConnectionSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.cloudplaylists.connect.CloudPlaylistsConnectionFactory;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.exfm.connect.ExFmConnectionFactory;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.lastfm.pseudooauth2.connect.LastFmPseudoOAuth2ConnectionFactory;
import org.springframework.social.mixcloud.connect.MixcloudConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.soundcloud.connect.SoundCloudConnectionFactory;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@PropertySource("classpath:/environment.properties")
public class SocialConfig implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;
	
	// Handle to users connection repository - allows us to set connection sign up in post construct
	private JdbcUsersConnectionRepository jdbcUsersConnectionRepository;
	
	@Autowired(required=false)
	private SpringSocialSecurityConnectionSignUp springSocialSecurityConnnectionSignUp;

	@Override
	public void addConnectionFactories(
			ConnectionFactoryConfigurer cfConfig,
			Environment env) {
		 cfConfig.addConnectionFactory(new TwitterConnectionFactory(
	                env.getProperty("twitter.consumerKey"),
	                env.getProperty("twitter.consumerSecret")
	        ));
		 
	        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
	                env.getProperty("facebook.clientId"),
	                env.getProperty("facebook.clientSecret")
	        ));
	
	        cfConfig.addConnectionFactory(new SoundCloudConnectionFactory(
	                env.getProperty("soundcloud.consumerKey"),
	                env.getProperty("soundcloud.consumerSecret"),
	                env.getProperty("soundcloud.redirectUri")
	        ));
	

	        cfConfig.addConnectionFactory(new LastFmPseudoOAuth2ConnectionFactory(
	                env.getProperty("lastfm.consumerKey"),
	                env.getProperty("lastfm.consumerSecret")
	        ));
	        
	        cfConfig.addConnectionFactory(new CloudPlaylistsConnectionFactory(
	                env.getProperty("cloudplaylists.consumerKey"),
	                env.getProperty("cloudplaylists.consumerSecret"),
	                env.getProperty("cloudplaylists.oauthAuthorizeUrl"),
	                env.getProperty("cloudplaylists.oauthTokenUrl"),
	                env.getProperty("cloudplaylists.oauthApiBaseUrl")
	        ));
	        
	        cfConfig.addConnectionFactory(new ExFmConnectionFactory(
	                env.getProperty("exfm.consumerKey"),
	                env.getProperty("exfm.consumerSecret"),
	                env.getProperty("exfm.oauthAuthorizeUrl"),
	                env.getProperty("exfm.oauthTokenUrl"),
	                env.getProperty("exfm.oauthApiBaseUrl")
	        ));
	        
	        cfConfig.addConnectionFactory(new MixcloudConnectionFactory(
	                env.getProperty("mixcloud.consumerKey"),
	                env.getProperty("mixcloud.consumerSecret")
	        ));
	        
	        cfConfig.addConnectionFactory(new SpringSocialSecurityConnectionFactory());
	}

	/**
	 * This is only needed because the official spring-social-security from SpringSocial is on the classpath
	 * @return
	 */
	@Override
	public UserIdSource getUserIdSource() {
		 return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator connectionFactoryLocator) {
		jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
                
        );
		return jdbcUsersConnectionRepository;
		
	}
	
	@PostConstruct
	// Registers a mechanism for implicit sign up if user id available from provider
	// Remove if explicit user name selection is required
	public void registerConnectionSignUp()
	{
		jdbcUsersConnectionRepository.setConnectionSignUp(springSocialSecurityConnnectionSignUp);
	}


}

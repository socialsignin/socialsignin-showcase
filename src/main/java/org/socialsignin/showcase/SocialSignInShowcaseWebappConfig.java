/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.socialsignin.showcase;

import java.util.Properties;

import javax.sql.DataSource;

import org.socialsignin.springsocial.security.config.annotation.EnableSpringSocialSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.cloudplaylists.config.annotation.EnableCloudPlaylists;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.exfm.config.annotation.EnableExFm;
import org.springframework.social.facebook.config.annotation.EnableFacebook;
import org.springframework.social.lastfm.config.annotation.EnableLastFm;
import org.springframework.social.mixcloud.config.annotation.EnableMixcloud;
import org.springframework.social.soundcloud.config.annotation.EnableSoundCloud;
import org.springframework.social.twitter.config.annotation.EnableTwitter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
//Swap in the below annotation instead of no-arg version if implicit sign up is required
//@EnableJdbcConnectionRepository(connectionSignUpRef="springSocialSecurityConnectionSignUp")
@EnableJdbcConnectionRepository
@EnableSpringSocialSecurity
@EnableSoundCloud(appId = "${soundcloud.consumerKey}", appSecret = "${soundcloud.consumerSecret}", redirectUri="${soundcloud.redirectUri}")
@EnableTwitter(appId = "${twitter.consumerKey}", appSecret = "${twitter.consumerSecret}")
@EnableLastFm(appId = "${lastfm.consumerKey}", appSecret = "${lastfm.consumerSecret}")
@EnableExFm(appId = "${exfm.consumerKey}", appSecret = "${exfm.consumerSecret}", oauthApiBaseUrl = "${exfm.oauthApiBaseUrl}", oauthAuthorizeUrl = "${exfm.oauthAuthorizeUrl}", oauthTokenUrl = "${exfm.oauthTokenUrl}")
@EnableFacebook(appId = "${facebook.clientId}", appSecret = "${facebook.clientSecret}")
@EnableCloudPlaylists(appId = "${cloudplaylists.consumerKey}", appSecret = "${cloudplaylists.consumerSecret}")
@EnableMixcloud(appId = "${mixcloud.consumerKey}", appSecret = "${mixcloud.consumerSecret}")
public class SocialSignInShowcaseWebappConfig {

	
	@Bean
	public HandlerExceptionResolver defaultHandlerExceptionResolver()
	{
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.socialsignin.springframework.social.security.signin.NonUniqueConnectionException", "connect/providerConnect");
		mappings.put("org.springframework.social.ExpiredAuthorizationException", "connect/providerConnect");
		mappings.put("org.springframework.social.connect.NotConnectedException", "connect/providerConnect");
		resolver.setDefaultErrorView("exception");
		
		resolver.setExceptionMappings(mappings);
		return resolver;
	}
	
	

	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql")
            .build();
    }

	
	@Bean
	public UserIdSource userIdSource() {
		return new UserIdSource() {			
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

	@Bean
	public RequestMappingHandlerMapping handlerMapping() throws Exception {

		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		return mapping;
	}
	

	@Bean
	public RequestMappingHandlerAdapter handlerAdapter() throws Exception {

		RequestMappingHandlerAdapter mapping = new RequestMappingHandlerAdapter();

		return mapping;

	}

}

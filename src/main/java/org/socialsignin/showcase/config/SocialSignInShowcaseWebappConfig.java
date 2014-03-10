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
package org.socialsignin.showcase.config;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.social.ApiException;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableSocial
//Swap in the below annotation instead of no-arg version if implicit sign up is required
//@EnableJdbcConnectionRepository(connectionSignUpRef="springSocialSecurityConnectionSignUp")
//@EnableJdbcConnectionRepository
//@EnableSpringSocialSecurity
//@EnableSoundCloud(appId = "${soundcloud.consumerKey}", appSecret = "${soundcloud.consumerSecret}", redirectUri="${soundcloud.redirectUri}")
//@EnableTwitter(appId = "${twitter.consumerKey}", appSecret = "${twitter.consumerSecret}")
//@EnableLastFm(appId = "${lastfm.consumerKey}", appSecret = "${lastfm.consumerSecret}")
//@EnableExFm(appId = "${exfm.consumerKey}", appSecret = "${exfm.consumerSecret}", oauthApiBaseUrl = "${exfm.oauthApiBaseUrl}", oauthAuthorizeUrl = "${exfm.oauthAuthorizeUrl}", oauthTokenUrl = "${exfm.oauthTokenUrl}")
//@EnableFacebook(appId = "${facebook.clientId}", appSecret = "${facebook.clientSecret}")
//@EnableCloudPlaylists(appId = "${cloudplaylists.consumerKey}", appSecret = "${cloudplaylists.consumerSecret}")
//@EnableMixcloud(appId = "${mixcloud.consumerKey}", appSecret = "${mixcloud.consumerSecret}")
public class SocialSignInShowcaseWebappConfig {

	
	

	
	@Bean
	public HandlerExceptionResolver defaultHandlerExceptionResolver()
	{
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver()
		{

			@Override
			protected ModelAndView getModelAndView(String viewName,
					Exception ex, HttpServletRequest request) {
				return addProviderToModelIfAvailable(super.getModelAndView(viewName, ex,request),ex);
			}

			@Override
			protected ModelAndView getModelAndView(String viewName, Exception ex) {
				return addProviderToModelIfAvailable(super.getModelAndView(viewName, ex),ex);
			}
			
			private ModelAndView addProviderToModelIfAvailable(ModelAndView mav,Exception ex)
			{
				if (ex instanceof ApiException)
				{
					mav.addObject("provider", ((ApiException)ex).getProviderId());
				}
				return mav;
			}
			
			
			
		};
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
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql")
            .build();
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

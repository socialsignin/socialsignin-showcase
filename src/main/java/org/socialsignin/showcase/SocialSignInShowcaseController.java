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

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocialSignInShowcaseController {

	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;

	private String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		return authentication == null ? null : authentication.getName();
	}

	private Map<String, String> getRegisteredProviderRoleNamesByProviderName() {
		Map<String, String> registeredProviderRoleNamesByProviderName = new HashMap<String, String>();
		for (String registeredProviderId : connectionFactoryRegistry
				.registeredProviderIds()) {
			registeredProviderRoleNamesByProviderName.put(registeredProviderId,
					"ROLE_USER_" + registeredProviderId.toUpperCase());
		}
		return registeredProviderRoleNamesByProviderName;

	}

	@RequestMapping("/login")
	public String login(Map model) {

		model.put("registeredProviderRoleNamesByProviderName",
				getRegisteredProviderRoleNamesByProviderName());
		return "oauthlogin";
	}

	@RequestMapping("/connectWithProvider")
	public String connect(Map model) {

		model.put("registeredProviderRoleNamesByProviderName",
				getRegisteredProviderRoleNamesByProviderName());
		return "oauthconnect";
	}

	@RequestMapping("/")
	public String helloPublicWorld(Map model) {
		model.put("userName", getAuthenticatedUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Public");

		model.put("registeredProviderRoleNamesByProviderName",
				getRegisteredProviderRoleNamesByProviderName());

		return "helloWorld";
	}

	@RequestMapping("/protected")
	public String helloProtectedWorld(Map model) {
		model.put("userName", getAuthenticatedUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Protected");

		model.put("registeredProviderRoleNamesByProviderName",
				getRegisteredProviderRoleNamesByProviderName());

		return "helloWorld";
	}

}

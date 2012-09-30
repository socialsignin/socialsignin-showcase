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
package org.socialsignin.showcase.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.socialsignin.provider.facebook.FacebookProviderService;
import org.socialsignin.provider.lastfm.LastFmProviderService;
import org.socialsignin.provider.linkedin.LinkedInProviderService;
import org.socialsignin.provider.mixcloud.MixcloudProviderService;
import org.socialsignin.provider.soundcloud.SoundCloudProviderService;
import org.socialsignin.provider.twitter.TwitterProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.lastfm.api.LastFm;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.mixcloud.api.Mixcloud;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocialSignInShowcaseController {
	
	@Autowired
	private LastFmProviderService lastFmProviderService;
	
	@Autowired
	private FacebookProviderService facebookProviderService;
	
	@Autowired
	private TwitterProviderService twitterProviderService;
	
	@Autowired
	private MixcloudProviderService mixcloudProviderService;
	
	@Autowired
	private SoundCloudProviderService soundCloudProviderService;
	
	@Autowired
	private LinkedInProviderService linkedInProviderService;
	
	@RequestMapping("/login")
	public String login(Map model) {
		return "oauthlogin";
	}

	@RequestMapping("/connectWithProvider")
	public String connect(Map model) {

		return "oauthconnect";
	}

	@RequestMapping("/")
	public String helloPublicWorld(Map model) {

		return "publicPage";
	}
	
	
	@RequestMapping("/protected")
	public String helloProtectedWorld(Map model) {

		List<String> profileUrls = new ArrayList<String>();
		
		LastFm lastFm = lastFmProviderService.getAuthenticatedApi();
		if (lastFm != null)
		{
			profileUrls.add(lastFm.userOperations().getUserProfile().getUrl());
		}
		
		Facebook facebook = facebookProviderService.getAuthenticatedApi();
		if (facebook != null)
		{
			profileUrls.add(facebook.userOperations().getUserProfile().getLink());	
		}
		
		Twitter twitter = twitterProviderService.getAuthenticatedApi();
		if (twitter != null)
		{
			profileUrls.add(twitter.userOperations().getUserProfile().getProfileUrl());
		}
		
		Mixcloud mixcloud = mixcloudProviderService.getAuthenticatedApi();
		if (mixcloud != null)
		{
			profileUrls.add(mixcloud.meOperations().getUserProfile().getUrl());
		}
		
		SoundCloud soundCloud = soundCloudProviderService.getAuthenticatedApi();
		if (soundCloud != null)
		{
			profileUrls.add(soundCloud.meOperations().getUserProfile().getPermalinkUrl());
		}
		
		LinkedIn linkedIn = linkedInProviderService.getAuthenticatedApi();
		if (linkedIn != null)
		{
			profileUrls.add(linkedIn.profileOperations().getProfileUrl());
		}
			
		model.put("profileUrls",
				profileUrls);
	
		return "protectedPage";
	}
	
	
	@RequestMapping("/protected/twitter")
	public String helloProtectedTwitterWorld(Map model) {

		List<String> profileUrls = new ArrayList<String>();
		
		Twitter twitter = twitterProviderService.getAuthenticatedApi();
		profileUrls.add(twitter.userOperations().getUserProfile().getProfileUrl());
		
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline();
		model.put("tweets", tweets);
	
		return "protectedPage";
	}

}

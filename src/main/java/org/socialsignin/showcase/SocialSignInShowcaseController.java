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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.socialsignin.provider.cloudplaylists.CloudPlaylistsProviderService;
import org.socialsignin.provider.exfm.ExFmProviderService;
import org.socialsignin.provider.facebook.FacebookProviderService;
import org.socialsignin.provider.lastfm.LastFmProviderService;
import org.socialsignin.provider.linkedin.LinkedInProviderService;
import org.socialsignin.provider.mixcloud.MixcloudProviderService;
import org.socialsignin.provider.soundcloud.SoundCloudProviderService;
import org.socialsignin.provider.twitter.TwitterProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.social.cloudplaylists.api.CloudPlaylists;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.exfm.api.ExFm;
import org.springframework.social.exfm.api.Song;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.lastfm.api.LastFm;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.mixcloud.api.Mixcloud;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.Track;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudplaylists.domain.PlaylistDescriptor;

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
	private ExFmProviderService exFmProviderService;
	
	@Autowired
	private SoundCloudProviderService soundCloudProviderService;
	
	@Autowired
	private LinkedInProviderService linkedInProviderService;
	
	@Autowired
	private CloudPlaylistsProviderService cloudPlaylistsProviderService;
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@RequestMapping("/login")
	public String login(Map model) {
		return "oauthlogin";
	}

	@RequestMapping("/connectWithProvider")
	public String connect(Map model) {

		return "oauthconnect";
	}

	@RequestMapping("/")
	public String index(Map model) {

		return "index";
	}
	
	

	@RequestMapping("/tweets")
	public String tweets(Map model) {
		
		Twitter twitter = twitterProviderService.getAuthenticatedApi();
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline();
		model.put("tweets", tweets);
		return "tweets";
	}
	
	@RequestMapping("/exfmLovedSongs")
	public String exfmLovedSongs(Map model) {
		
		ExFm exfm = exFmProviderService.getAuthenticatedApi();
		String exfmUserName = exfm.meOperations().getUserProfile().getUsername();
		Page<Song> songs = exfm.usersOperations().userOperations(exfmUserName).getLovedSongs();
		model.put("songs", songs);
		return "songs";
	}
	
	@RequestMapping("/soundCloudFavorites")
	public String soundCloudFavorites(Map model) {
		
		SoundCloud soundCloud = soundCloudProviderService.getAuthenticatedApi();
		Page<Track> soundCloudFavorites = soundCloud.meOperations().getFavorites();
		model.put("soundCloudFavorites", soundCloudFavorites);
		return "soundCloudFavorites";
	}
	
	@RequestMapping("/lastFmLovedTracks")
	public String lastFmLovedTracks(Map model) {
		
		LastFm lastFm = lastFmProviderService.getAuthenticatedApi();
		String lastFmUserName = lastFm.userOperations().getUserProfile().getName();
		Page<org.springframework.social.lastfm.api.Track> lastFmLovedTracks = lastFm.userOperations().getLovedTracks(lastFmUserName);
		model.put("lastFmLovedTracks", lastFmLovedTracks);
		return "lastFmLovedTracks";
	}
	
	@RequestMapping("/cloudPlaylists")
	public String cloudPlaylists(Map model) {
		
		CloudPlaylists cloudPlaylists = cloudPlaylistsProviderService.getAuthenticatedApi();
		Page<? extends PlaylistDescriptor> playlistDescriptors = cloudPlaylists.meOperations().getPlaylistDescriptors();
		model.put("playlistDescriptors", playlistDescriptors);
		return "cloudplaylists";
	}
	
	@RequestMapping("/accessTokens")
	public String accessTokens(Map model) {

		MultiValueMap<String, Connection<?>> connections = connectionRepository.findAllConnections();
		List<ConnectionData> connectionDataList = new ArrayList<ConnectionData>();
		for (Entry<String, List<Connection<?>>> connectionEntry : connections.entrySet()) {
			for (Connection<?> connection : connectionEntry.getValue())
			{
				connectionDataList.add(connection.createData());
			}
		}
		model.put("connectionDataList", connectionDataList);
	
		return "accessTokens";
	}
	
	
	
	@RequestMapping("/profileUrls")
	public String profileUrls(Map model) {

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
		
		CloudPlaylists cloudPlaylists = cloudPlaylistsProviderService.getAuthenticatedApi();
		if (cloudPlaylists != null)
		{
			profileUrls.add(cloudPlaylists.meOperations().getUserProfile().getProfileUrl());
		}
		
	
		model.put("profileUrls",
				profileUrls);
	
		return "profileUrls";
	}
}

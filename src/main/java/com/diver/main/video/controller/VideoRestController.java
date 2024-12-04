package com.diver.main.video.controller;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.diver.main.video.model.Video;
import com.diver.main.video.service.VideoServiceInterface;

// al 16/12/2022 è inutilizzato
@RestController
@RequestMapping(path = "/diver")
public class VideoRestController {
	
	@Autowired
	VideoServiceInterface vService;
	
	@GetMapping(value = "/videos")
	@RolesAllowed({"User","Admin"})
	public List<Video> retrieveVideos(){
		
		
		return	 vService.retrieveAllVideo();
		//	uService.changePwdUser(cred);
			
	
	
	}

}

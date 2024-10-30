package com.diver.main.video.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diver.main.video.model.Video;
import com.diver.main.video.repository.VideoDaoInterface;

@Service
public class VideoServiceImpl implements VideoServiceInterface {
	
	@Autowired
	VideoDaoInterface vDao;

	@Override
	public List<Video> retrieveAllVideo() {
		// TODO Auto-generated method stub
		return vDao.retrieveAllVideo();
	}

}

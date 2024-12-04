package com.diver.main.video.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diver.main.video.model.Video;

@Repository
public interface VideoJpaInterface extends JpaRepository<Video, Integer> {

	@Query("SELECT v FROM Video v JOIN v.esp e where e.id=?1")
	List<Video> getVideoFromExperienceId(int id);
}

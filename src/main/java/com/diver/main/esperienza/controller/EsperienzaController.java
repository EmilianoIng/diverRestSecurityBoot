package com.diver.main.esperienza.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.diver.main.esperienza.service.EsperienzaServiceInterface;
import com.diver.main.model.Esperienza;
import com.diver.main.model.dto.EspDtoOut;
import com.diver.main.model.dto.EsperienzaDtoInput;
import com.diver.main.model.dto.ExperienceDetailsDtoOut;
import com.diver.main.model.dto.ImmDtoOut;
import com.diver.main.model.dto.ImmFromSpring;
import com.diver.main.model.dto.NewsOfExperienceDtoOut;
import com.diver.main.model.dto.NewsExperienceDtoOut;
import com.diver.main.model.dto.VideoDtoInput;
import com.diver.main.model.dto.VideoDtoOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@RequestMapping(path = "/diver")
public class EsperienzaController {

	@Autowired
	EsperienzaServiceInterface espService;

	@GetMapping(value = "/video")
	@RolesAllowed({ "User", "Admin" })

	public List<VideoDtoOut> retrieveAllEsperienzeWithVideoDetail() {

		return espService.retrieveAllEsperienzeWithVideoDetail();
	}
	
	@GetMapping(value = "/news")
	//@RolesAllowed({ "User", "Admin" })

	public NewsOfExperienceDtoOut retrieveNews() throws IOException {

		return espService.retrieveNews();
	}

	@DeleteMapping(value = "/video/{id}")
	@RolesAllowed({ "User", "Admin" })
	public boolean deleteVideo(@PathVariable int id) throws IOException {

		return this.espService.deleteVideo(id);

	}
	@DeleteMapping(value = "/immagine/{idImm}")
	@RolesAllowed({ "User", "Admin" })
	public boolean deleteImmagine(@PathVariable int idImm) throws IOException {

		return this.espService.deleteImmagine(idImm);

	}
	
	@DeleteMapping(value = "/esperienza/{id}")
	@RolesAllowed({ "User", "Admin" })
	public boolean deleteExperience(@PathVariable int id) throws IOException {

		return this.espService.deleteExperience(id);

	}
	
	@DeleteMapping(value = "/gita/esperienza/{id}")
	@RolesAllowed({ "User", "Admin" })
	public boolean deleteGitaFromIdEsperienza(@PathVariable int id) throws IOException {

		return this.espService.deleteGitaFromIdExperience(id);

	}
	
	@DeleteMapping(value = "/viaggio/{idViaggio}")
	@RolesAllowed({ "User", "Admin" })
	public boolean deleteViaggio(@PathVariable int idViaggio) throws IOException {

		return this.espService.deleteViaggio(idViaggio);

	}
	
	

	@GetMapping(value = "/video/{id}")
	@RolesAllowed({ "User", "Admin" })
	public VideoDtoOut getVideo(@PathVariable int id) throws IOException {

		return this.espService.getVideoById(id);

	}
	@GetMapping(value = "/video/esperienza/{id}")
	@RolesAllowed({ "User", "Admin" })
	public List<VideoDtoOut> getVideoOfExperience(@PathVariable int id) throws IOException {

		return this.espService.getVideoByExperienceId(id);

	}

	@GetMapping(value = "/immagini")
	@RolesAllowed({ "User", "Admin" })

	public List<ImmDtoOut> retrieveAllMainImage() {

		return espService.retrieveAllMainImage();
	}
	@PatchMapping(value = "/immagini/{travel}/{nameEsp}")
	@RolesAllowed({ "User", "Admin" })

	public List<ImmDtoOut> modifyMainImageOfTravel(@PathVariable String travel,@PathVariable String nameEsp) {

		return espService.modifyMainImageOfTravel(travel,nameEsp);
	}
	@GetMapping(value = "/immagini/{nome}")
	@RolesAllowed({ "User", "Admin" })

	public List<ImmDtoOut> retrieveAllImageOfTravel(@PathVariable String nome) {

		return espService.retrieveAllMainExperienceImageOfTravel(nome);
	}
	
	@GetMapping(value = "/gite")
	@RolesAllowed({ "User", "Admin" })

	public List<ExperienceDetailsDtoOut> retrieveMainImagesOfTrip() throws IOException {

		return espService.retrieveAllTripsImage();
	}
	@GetMapping(value = "/immagini/esperienze/{tipo}/{id}")
	@RolesAllowed({ "User", "Admin" })

	public List<ExperienceDetailsDtoOut> retrieveAllImageOfExperience(@PathVariable String tipo,@PathVariable Integer id) throws IOException {

		return espService.retrieveAllExperienceImageOfTravel(id,tipo);
	}

	@GetMapping(value = "/esperienze")
	@RolesAllowed({ "User", "Admin" })

	public List<EspDtoOut> retrieveAllExperience() {

		return this.espService.retrieveAllExperience();
	}

	@GetMapping(value = "/esperienze/{travel}")
	@RolesAllowed({ "User", "Admin" })

	public List<String> retrieveAllExperienceOfTravel( @PathVariable("travel") String travelName) {

		return this.espService.retrieveAllExperienceOfTravel(travelName);
	}
	
	@PostMapping(value = "/esperienze")
	@RolesAllowed({ "User", "Admin" })

	public List<String> insertExperienceInTravel( @RequestPart("imageFile") MultipartFile file,
			@RequestPart("detail") String detail) throws IOException {
System.out.println("dettaglio esp "+detail);
		Esperienza e=this.espService.addExperienceOfTravel(detail);
		this.espService.saveImage(file, e.getId(), detail);
		String travelName=e.getViaggio().getNome();
		return this.espService.retrieveAllExperienceOfTravel(travelName);
	}
	// @RequestMapping(path = "/video", method = RequestMethod.POST, consumes = {
	// MediaType.MULTIPART_FORM_DATA_VALUE })
	@PostMapping(value = "/video")
	@RolesAllowed({ "User", "Admin" })

	public ResponseEntity<Object> saveVideo(@RequestPart("imageFile") MultipartFile file,
			@RequestPart("detail") String detail) throws IOException {

		// return espService.retrieveAllEsperienzeWithVideoDetail();
		System.out.println("video da inserire " + file.getOriginalFilename());
		System.out.println("test " + detail);

		int id = this.espService.saveVideo(file, detail);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PatchMapping(value = "/video")
	@RolesAllowed({ "User", "Admin" })
	public void updateVideoAndImage(@RequestPart("imageFile") MultipartFile file, @RequestPart("detail") String detail)
			throws IOException {
		System.out.println("video da inserire " + file.getOriginalFilename());
		System.out.println("dettagli video " + detail);
		this.espService.updateVideoAndImage(file, detail);

	}
	@PatchMapping(value = "/esperienza/immagine")
	@RolesAllowed({ "User", "Admin" })
	public void updateEsperienzaMainImage(@RequestPart("imageFile") MultipartFile file, @RequestPart("detail") String detail) throws JsonMappingException, JsonProcessingException, IOException {
System.out.println("update immagine");
		this.espService.updateExperienceImage(file, detail);

	}
	@PostMapping(value = "/esperienza/immagine")
	@RolesAllowed({ "User", "Admin" })
	public void uploadEsperienzaDetailImage(@RequestPart("imageFile") MultipartFile file, @RequestPart("detail") String detail) throws JsonMappingException, JsonProcessingException, IOException {
System.out.println("update immagine "+detail);
		this.espService.saveExperienceImage(file, detail);

	}
	@PatchMapping(value = "/video/{id}")
	@RolesAllowed({ "User", "Admin" })
	public void updateVideoWithoutImage(@PathVariable int id, @RequestBody VideoDtoInput video) {

		System.out.println("dettagli video " + video);
		this.espService.updateVideoWithoutImage(id, video);

	}
	@PatchMapping(value = "/esperienza/{id}")
	@RolesAllowed({ "User", "Admin" })
	public void updateEsperienzaWithoutImage(@PathVariable int id, @RequestBody EsperienzaDtoInput esp) {

		System.out.println("dettagli video " + esp);
		this.espService.updateExperienceWithoutImage(id, esp);
		//this.espService.updateVideoWithoutImage(id, video);

	}
	
	@PatchMapping(value = "/immagine/{id}")
	@RolesAllowed({ "User", "Admin" })
	public void resetMainImageOfTravel(@PathVariable int id) {
		this.espService.resetMainImageOfTravel(id);
		//this.espService.updateVideoWithoutImage(id, video);

	}
	

	
	@PatchMapping(value = "/viaggi/{oldName}/{newName}")
	@RolesAllowed({ "User", "Admin" })
	public List<ImmDtoOut> updateImmagineWithoutImage(@PathVariable String oldName, @PathVariable String newName) {

		
	return	this.espService.modifyNameOfTravel(oldName,newName);

	}

	@GetMapping(value = "/media-image")
	@RolesAllowed({ "User", "Admin" })
	public ImmFromSpring getVideoImageWithMediaType(@RequestParam String name) throws IOException {
		System.out.println("ricerca immagine " + name);
		System.out.println("array di byte " + this.espService.getImage(name).length);
		ImmFromSpring immDto = new ImmFromSpring(name, MediaType.IMAGE_JPEG_VALUE, this.espService.getImage(name));
		return immDto;
	}

	
	
	@PostMapping(value = "/viaggi")
	@RolesAllowed({ "User", "Admin" })

	public ResponseEntity<Object> saveViaggi(@RequestPart("imageFile") MultipartFile file,
			@RequestPart("detail") String detail) throws IOException, ParseException {

		// return espService.retrieveAllEsperienzeWithVideoDetail();
		System.out.println("viaggio da inserire " + file.getOriginalFilename());
		System.out.println("test " + detail);

		int id = this.espService.saveViaggio(file,detail);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/gite")
	@RolesAllowed({ "User", "Admin" })

	public ResponseEntity<Object> saveGite(@RequestPart("imageFile") MultipartFile file,
			@RequestPart("detail") String detail) throws IOException, ParseException {

		// return espService.retrieveAllEsperienzeWithVideoDetail();
		System.out.println("viaggio da inserire " + file.getOriginalFilename());
		System.out.println("test " + detail);

		int id = this.espService.saveGita(file,detail);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/viaggi/images")
	@RolesAllowed({ "User", "Admin" })

	public ResponseEntity<Object> saveTravelDescriptionImage(@RequestPart("imageFile") MultipartFile file,
			@RequestPart("detail") String detail) throws IOException {

		// return espService.retrieveAllEsperienzeWithVideoDetail();
		System.out.println("immagine descrittiva viaggio da inserire " + file.getOriginalFilename());
		System.out.println("test " + detail);

		Esperienza e = this.espService.retrieveExperienceByName(detail);
       int id= this.espService.saveDescriptionImageOfTravel(e, file, detail);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
	

}

package com.diver.main.esperienza.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diver.main.esperienza.repository.EsperienzaDaoInterface;
import com.diver.main.esperienza.repository.EsperienzaJpaInterface;
import com.diver.main.esperienza.repository.NewmaintravelimageInterface;
import com.diver.main.exception.EntityNoFoundException;
import com.diver.main.gita.repository.GitaRepoJpaInterface;
import com.diver.main.immagine.repository.ImmagineJpaInterface;
import com.diver.main.mapper.ImmaginiMapper;
import com.diver.main.mapper.VideoMapper;
import com.diver.main.model.Esperienza;
import com.diver.main.model.Gita;
import com.diver.main.model.Immagine;
import com.diver.main.model.Viaggio;
import com.diver.main.model.dto.EntityToDelete;
import com.diver.main.model.dto.EspDtoOut;
import com.diver.main.model.dto.EsperienzaDtoInput;
import com.diver.main.model.dto.ExperienceDetailsDtoOut;
import com.diver.main.model.dto.ExperienceOfTravelDTOInterface;
import com.diver.main.model.dto.GitaFromExperienceInterface;
import com.diver.main.model.dto.GiteInnerEsperienceDTOInterface;
import com.diver.main.model.dto.ImmDtoOut;
import com.diver.main.model.dto.NewsOfExperienceDtoOut;
import com.diver.main.model.dto.ViaggiDeleteDTOInterface;
import com.diver.main.model.dto.VideoDTOInterface;
import com.diver.main.model.dto.NewsExperienceDtoOut;
import com.diver.main.model.dto.VideoDtoInput;
import com.diver.main.model.dto.VideoDtoOut;
import com.diver.main.model.view.Newmaintravelimage;
import com.diver.main.viaggi.repository.ViaggiJpaInterface;
import com.diver.main.video.model.Video;
import com.diver.main.video.repository.VideoJpaInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EsperienzaServiceImpl implements EsperienzaServiceInterface {
	
@Autowired
EsperienzaDaoInterface espDao;

@Autowired
EsperienzaJpaInterface espJpa;

@Autowired
VideoMapper videoMapper;

@Autowired
ImmaginiMapper immaginiMapper;

@Autowired
VideoJpaInterface videoJpa;

@Autowired
ImmagineJpaInterface immagineJpa;

@Autowired
ViaggiJpaInterface vJpaRepo;

@Autowired
NewmaintravelimageInterface newMainImageJpaRepo;

@Autowired
GitaRepoJpaInterface gitaJpaRepo;


@Value("${diver.path.image}")
private String pathImage;
	
	/*recuperoinformazioni dei video associate alle esperienze di gite o di viaggi*/
	@Override
	public List<VideoDtoOut> retrieveAllEsperienzeWithVideoDetail() {
		// TODO Auto-generated method stub

		return espJpa.viaggiGiteInnerEsperienzeInnerVideo().stream().map(e->videoMapper.toVideoDtoOut(e)).collect(Collectors.toList());
	}
	
	private List<VideoDtoOut> mappingVideoDtoOut(List<VideoDTOInterface> list) {
		List <VideoDtoOut> outVideo=new ArrayList<>();
		for(VideoDTOInterface result:list) {
			VideoDtoOut video=new VideoDtoOut();
			video.setNomeGitaViaggio(result.getNome_viaggio());
			video.setNome(result.getNome_esperienza());
			video.setThumbnail(result.getThumbnail());
			video.setYouTubeId(result.getYoutube_id());
			video.setDescrizioneVideo(result.getDescrizione());
			video.setId(result.getId());
			video.setDemo(result.getDemo()==0?false:true);
//			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" video demo "+result[7]);
		    outVideo.add(video);
		}
		
		System.out.println("outVideo "+outVideo);
		return outVideo;
	}
	
//	private List<VideoDtoOut> mappingVideoDtoOutOld(List<Object[]> list) {
//		List <VideoDtoOut> outVideo=new ArrayList<>();
//		for(Object[] result:list) {
//			VideoDtoOut video=new VideoDtoOut();
//			video.setNomeGitaViaggio((String)result[0]);
//			video.setNome((String)result[3]);
//			video.setThumbnail((String)result[4]);
//			video.setYouTubeId((String)result[5]);
//			video.setDescrizioneVideo((String)result[2]);
//			video.setId((Integer)result[6]);
//			video.setDemo((Byte)result[7]==0?false:true);
//			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" video demo "+result[7]);
//		    outVideo.add(video);
//		}
//		
//		System.out.println("outVideo "+outVideo);
//		return outVideo;
//	}

	@Override
	public List<ImmDtoOut> retrieveAllMainImage() {
		// TODO Auto-generated method stub
//		List<ImmDtoOut> list=new ArrayList<>();
		List<ImmDtoOut> list=this.espJpa.immaginiWhereMainIsTrue().stream()				
				.map(i->immaginiMapper.toVideoDtoOut(i)).collect(Collectors.toList());
//		list=this.mappingViaggiMainDtoOut(espDao.immaginiWhereMainIsTrue());
		return  this.addNewTravelImageToMainResut(list);
		
	}
	
	@Override
	public List<ImmDtoOut> retrieveAllMainExperienceImageOfTravel(String name) {
		// TODO Auto-generated method stub
//		List<ImmDtoOut> list=new ArrayList<>();
//		list=this.mappingViaggiMainDtoOut(this.espDao.allMainExperienceImageOfTravel(name));
		List<ImmDtoOut> list=this.espJpa.allMainExperienceImageOfTravel(name).stream().map(i->immaginiMapper.toVideoDtoOut(i)).collect(Collectors.toList());
		ImmDtoOut nVisit=new ImmDtoOut();
		nVisit.setNameViaggioGita("New Visit");
		nVisit.setPathImage("~/assets/images/newVisit.jpg");
		nVisit.setIsDemo(true);
		list.add(nVisit);
		return  list;
	}
	

	@Override
	public List<ExperienceDetailsDtoOut> retrieveAllExperienceImageOfTravel(Integer id,String tipo) throws IOException {
		// TODO Auto-generated method stub
//		List<ExperienceDetailsDtoOut> list=new ArrayList<>();
//	    list=this.mappingExperienceDtoOut(this.espDao.retrieveAllImageOfExperienceById(id,tipo));
		
		List<ExperienceDetailsDtoOut> list =
				tipo.equals("Viaggio")?
				(this.espJpa.retrieveAllImageOfExperienceById(id).stream()
			    .map(i -> {
			        ExperienceDetailsDtoOut dto = this.immaginiMapper.toAnyExperienceImmDtoOut(i);
			        if (!dto.getPathImage().startsWith("~")) {
			            try {
							dto.setPicByte(this.getImage(dto.getPathImage()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			        return dto;
			    })
			    .collect(Collectors.toList())):
			    	(this.espJpa.retrieveAllImageOfTripExperienceById(id).stream()
			    	.peek(elem->System.out.println("elem: id "+elem.getIdImmagine()+" elem: experienceImage "+elem.getExperienceImage()))
				    .map(i -> {
				        ExperienceDetailsDtoOut dto = this.immaginiMapper.toAnyExperienceImmDtoOut(i);
				        if (!dto.getPathImage().startsWith("~")) {
				            try {
								dto.setPicByte(this.getImage(dto.getPathImage()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				        }
				        return dto;
				    })
				    .collect(Collectors.toList())) ;


		return  list;
	}

	
	private List<ImmDtoOut> addNewTravelImageToMainResut(List<ImmDtoOut> list){
		
//		Immagine i=this.espDao.retrieveNewTravelImage();
		Immagine i=this.immagineJpa.findById(1).get();
		ImmDtoOut immOut=new ImmDtoOut();
		immOut.setId(i.getId());
		immOut.setNameViaggioGita("New Travel");
		immOut.setNameEsperienza("New Travel");
		immOut.setPathImage(i.getPathImage());
		immOut.setIsVertical(i.isVertical()==1?true:false);
		immOut.setIsMainImage(i.isMainImage()==1?true:false);
		immOut.setIsDemo(true);
		
		list.add(0,immOut);
		System.out.println("Lista delle immagini di copertina finale: "+list);
		return list;
	}
	
	private List<ImmDtoOut> mappingViaggiMainDtoOut(List<Object[]> list) {
		List <ImmDtoOut> outImm=new ArrayList<>();
		for(Object[] result:list) {
			ImmDtoOut imm=new ImmDtoOut();
			imm.setNameViaggioGita((String)result[0]);
			imm.setNameEsperienza((String)result[1]);
			imm.setPathImage((String)result[2]);
			imm.setId((Integer)result[3]);
			imm.setIsMainImage((Integer)result[4]==1?true:false);
			imm.setIsVertical((Integer)result[5]==1?true:false);
			imm.setIsExperienceImage((Integer)result[6]==1?true:false);
			imm.setIsDemo((Integer)result[7]==1?true:false);
			imm.setIdViaggio((Integer)result[8]);
			imm.setIdEsperienza((Integer)result[9]);
			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" ExperienceImage "+result[6]);
			outImm.add(imm);
		}
		
		System.out.println("outVideo "+outImm);
		return outImm;
	}
	
	private List<ExperienceDetailsDtoOut> mappingGiteMainExperienceDtoOut(List<Object[]> list) {
		List <ExperienceDetailsDtoOut> outImm=new ArrayList<>();
		for(Object[] result:list) {
			ExperienceDetailsDtoOut imm=new ExperienceDetailsDtoOut();
			imm.setNameEsperienza((String)result[7]);
			imm.setPathImage((String)result[1]);
			imm.setId((Integer)result[2]);
			imm.setVertical((Integer)result[3]==1?true:false);
			imm.setExperienceImage((Integer)result[4]==1?true:false);
			imm.setIdEsperienza((Integer)result[5]);
			imm.setDemo((Integer)result[6]==1?true:false);
			imm.setNameEsperienza(imm.getNameEsperienza()+" - "+(String)result[0]);
			//System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" ExperienceImage "+result[6]);
			outImm.add(imm);
		}
		
		System.out.println("outGiteMainimage "+outImm);
		return outImm;
	}
	
	private List<ExperienceDetailsDtoOut> mappingExperienceDtoOut(List<Object[]> list) {
		List <ExperienceDetailsDtoOut> outExp=new ArrayList<>();
		for(Object[] result:list) {
			ExperienceDetailsDtoOut expDetail=new ExperienceDetailsDtoOut();
			
			expDetail.setNameEsperienza((String)result[0]);
			expDetail.setPathImage((String)result[1]);
			expDetail.setId((Integer)result[2]);
			expDetail.setMainImage((Integer)result[3]==1?true:false);
			expDetail.setVertical((Integer)result[4]==1?true:false);
			expDetail.setExperienceImage((Integer)result[5]==1?true:false);
			expDetail.setDescrizione((String)result[6]);
			expDetail.setIdEsperienza((Integer)result[7]);
			expDetail.setDemo((Integer)result[8]==1?true:false);
			expDetail.setData((Date) result[9]);
			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" ExperienceImage "+result[6]+" ExperienceDate "+result[9]);
			outExp.add(expDetail);
		}
		
		System.out.println("outVideo "+outExp);
		return outExp;
	}

	@Override
	public List<EspDtoOut> retrieveAllExperience() {
	return	this.espJpa.allExperienceWithViaggioGitaDetail().stream().map(s->{
			EspDtoOut o=new EspDtoOut();
			o.setNomeViaggioGita(s);
			return o;
		}).collect(Collectors.toList());
//		List <String> list=this.espDao.allExperienceWithViaggioGitaDetail();
//		List<EspDtoOut> result=new ArrayList<>();
//		for(String s:list)
//		{
//			EspDtoOut o=new EspDtoOut();
//			o.setNomeViaggioGita( s);
//			result.add(o);
//		}
//		return result;
	}

	@Override
	public Integer saveVideo(MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		List<GiteInnerEsperienceDTOInterface> giteInnerJoinExperienceList=null;
			VideoDtoInput vIn=objectMapper.readValue(detail, VideoDtoInput.class);
			System.out.println("video recuperato: "+vIn);//video recuperato: VideoDtoInput [name=Ponza, videoId=rycgjh, specificPlace=Cala Dell'acqua, descrizione=Fuivhkh]
		  //Esperienza e=  this.espDao.retrieveEsperienzaFromId(retrieveVideoOrGitaId(vIn.getName()));
		List<ExperienceOfTravelDTOInterface> l= this.vJpaRepo.viaggiInnerJoinExperienceWhereviaggioNome(vIn.getName());
		Esperienza e=new Esperienza() ;
		if(l==null || l.size()==0) 
			 giteInnerJoinExperienceList = this.gitaJpaRepo.giteInnerJoinExperienceWheregitaNome(vIn.getName());
		
			e=this.espJpa.findById((l==null || l.size()==0)?
					giteInnerJoinExperienceList.get(0).getEsperienza_id():
						l.get(0).getEsperienza_id()
					).get();
		
			
			Video v= this.createVideoEntityToSave(e, vIn, file);
		  
		  byte[] bytes = file.getBytes();
          Path path = Paths.get(this.pathImage + file.getOriginalFilename());
          Files.write(path, bytes);
          return this.videoJpa.save(v).getId();
	
		
	}
	@Override
	@Transactional
	public Integer saveViaggio(MultipartFile file,String detail) throws IOException, ParseException {
		// test {"description":"Tulip resort Mars Alam Egitto","data":"2022-07-19T16:48:55.526Z","name":"Egitto","viaggioCopertina":1,"location":"Tulip resort - Mars Alam"}
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
        Viaggio v=new Viaggio();
        int idViaggio,idExperience,idImage=0;
        v.setNome(readTree.get("name").asText());
        v.setDemo(0);
         idViaggio=this.vJpaRepo.save(v).getId();
         idExperience=this.saveExperience(idViaggio, detail);
        idImage=this.saveImage(file, idExperience, detail);
		return  idViaggio;
	}
	@Override
	@Transactional
	public Integer saveGita(MultipartFile file, String detail) throws IOException, ParseException {
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
        Gita g =new Gita();
        int idGita,idExperience,idImage=0;
        g.setNome(readTree.get("name").asText());
        g.setDemo(0);
        Esperienza e = new Esperienza();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date d=sdf.parse(readTree.get("data").asText());
        e.setNome(readTree.get("location").asText());
        e.setDescrizione(readTree.get("description").asText());
		e.setData(d);
//		idExperience=this.espDao.saveExperience(e);
		e=this.espJpa.save(e);
		g.setExperience(e);
		Gita gita = this.gitaJpaRepo.save(g);
//        idGita=this.espDao.saveGita(g);
        idImage=this.saveImage(file, e.getId(), detail);
		// TODO Auto-generated method stub
		return gita.getId();
	}

	
	@Override
	@Transactional
	public Integer saveExperience(int idViaggio,String detail) throws IOException, ParseException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
		Esperienza e = new Esperienza();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date d=sdf.parse(readTree.get("data").asText());//new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(readTree.get("data").asText());
		e.setNome(readTree.get("location").asText());
		e.setDescrizione(readTree.get("description").asText());
		e.setData(d);
		Optional<Viaggio> byId = this.vJpaRepo.findById(idViaggio);
		if(byId.isEmpty())
			throw new EntityNoFoundException("Viaggio non trovato");
			
		Viaggio v=byId.get();
		e.setViaggio(v);
		 
		return this.espJpa.save(e).getId();
	} 
	
	@Override
	@Transactional
	public Integer saveImage(MultipartFile file, int idExperience,String detail) throws IOException {
		// TODO Auto-generated method stub
		 Immagine i = new Immagine();
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
        i.setMainImage(readTree.get("viaggioCopertina").asInt());
        i.setVertical(readTree.get("isVertical").asInt());
		byte[] bytes = file.getBytes();
	       Path path = Paths.get(this.pathImage + file.getOriginalFilename());
	       Files.write(path, bytes);
	      Optional<Esperienza> byId = this.espJpa.findById(idExperience);
	      if(byId.isEmpty()) 
	    	  throw new EntityNoFoundException("esperienza non presente!!");
  
    	  Esperienza e=byId.get();
//	       Esperienza e=this.espDao.retrieveEsperienzaFromId(idExperience);
	     i.setEspImm(e);
	     i.setPathImage(file.getOriginalFilename());
	     boolean b=readTree.get("esperienzaCopertina").asInt()==1?true:false;
	    System.out.println("test  "+b);
	     i.setExperienceImage(readTree.get("esperienzaCopertina").asInt());
//		return this.espDao.saveImmagine(i);
	      return this.immagineJpa.save(i).getId();
	}
	
	@Override
	@Transactional
	public void updateVideoAndImage(MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		Video v=this.retrieveVideoFromVideoDtoJson(detail);
		byte[] bytes = file.getBytes();
        Path path = Paths.get(this.pathImage + file.getOriginalFilename());
        Files.write(path, bytes);
		String oldFileToDelete =v.getThumbnail();
		Path pathToDelete = Paths.get(this.pathImage+oldFileToDelete);
		if(Files.deleteIfExists(pathToDelete))
		v.setThumbnail(file.getOriginalFilename());
		else
			v.setThumbnail(oldFileToDelete);
		this.videoJpa.save(v);
		
	}

	@Override
	@Transactional
	public void updateExperienceImage(MultipartFile file, String detail)
			throws JsonMappingException, JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		Immagine i=this.retrieveMainExperienceImage(file, detail);
		
		 byte[] bytes = file.getBytes();
	       Path path = Paths.get(this.pathImage + file.getOriginalFilename());
	       Files.write(path, bytes);
		 this.espDao.updateImmagine(i);
		
	}
	
	private Immagine retrieveMainExperienceImage(MultipartFile file, String detail) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
        Immagine i= this.espDao.getImageFromId(readTree.get("id").asInt());
        String oldFileToDelete=i.getPathImage();
        Path pathToDelete = Paths.get(this.pathImage+oldFileToDelete);
    	Files.deleteIfExists(pathToDelete);
    	Optional<Esperienza> byId = this.espJpa.findById(readTree.get("idEsperienza").asInt());
    	if(byId.isEmpty())
    		throw new EntityNoFoundException("Esperienza non trovata");
    	Esperienza e = byId.get();
//        Esperienza e=this.espDao.getEsperienzaFromId(readTree.get("idEsperienza").asInt());
        Gita g=e.getGita();
        Viaggio v=e.getViaggio();
        if(g!=null && g.getDemo()==1) {
        g.setDemo(0);
//        this.espDao.updateGita(g);
        this.gitaJpaRepo.save(g);
        }
        else if(v!=null && v.getDemo()==1) {
        	v.setDemo(0);
        	this.vJpaRepo.save(v);
        }
        else
        	System.out.println("non è un caso demo");
        i.setPathImage(file.getOriginalFilename());
        i.setVertical(readTree.get("vertical").asInt());
        return i;
	}
	
	private Video retrieveVideoFromVideoDtoJson(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(json);
//        Video v=this.espDao.getVideoFromId(readTree.get("id").asInt());
        Video v=this.videoJpa.findById(readTree.get("id").asInt()).get();
        v.setId(readTree.get("id").asInt());
        v.setDescrizione(readTree.get("descrizione").asText());
        v.setYoutube(readTree.get("videoId").asText());
        v.setNome(readTree.get("specificPlace").asText());
        return v;
		
	}
	private Video createVideoEntityToSave(Esperienza e,VideoDtoInput vIn,MultipartFile file) {
		
		Video v=new Video();
		  v.setDescrizione(vIn.getDescrizione());
		  v.setEsp(e);
		  v.setNome(vIn.getSpecificPlace());
		  v.setYoutube(vIn.getVideoId());
		  v.setThumbnail(file.getOriginalFilename());
		  return v;
		
	}

	@Override
	public byte[] getImage(String name) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("percorso file: "+this.pathImage+name);
		Path path = Paths.get(this.pathImage+name);		
		FileSystemResource fIn=new FileSystemResource(path);
		InputStream in =fIn.getInputStream();
		System.out.println("input "+in);
		byte[] readAllBytes = Files.readAllBytes(path);
		System.out.println("readAllbytes length "+readAllBytes.length);		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(readAllBytes.length);
		outputStream.write(readAllBytes);
		return outputStream.toByteArray();
		
	}

	@Override
	public boolean deleteVideo(int id) throws IOException {
		Optional<Video> byId = this.videoJpa.findById(id);
		if(byId.isPresent())
		{this.videoJpa.delete(byId.get());
//		String nameOfFile=this.espDao.removeVideo(id);
		String nameOfFile=byId.get().getNome();
		Path path = Paths.get(this.pathImage+nameOfFile);		
		return Files.deleteIfExists(path);
		
		}
		return false;
	}

	@Override
	public VideoDtoOut getVideoById(int id) {
		// TODO Auto-generated method stub
		return espJpa.viaggioGitaInnerEsperienzeInnerVideoId(id).stream().map(e->videoMapper.toVideoDtoOut(e)).collect(Collectors.toList()).get(0);
	}

	@Override
	@Transactional
	public void updateVideoWithoutImage(int id, VideoDtoInput vIn) {
		// TODO Auto-generated method stub
		Video v=null;
		Optional<Video> byId = this.videoJpa.findById(id);
		if(byId.isEmpty())
			throw new EntityNotFoundException("Video non trovato");
	    v=byId.get();
//		Video v=this.espDao.getVideoFromId(id);
		v=this.updateRacord(v, vIn);
		this.videoJpa.save(v);
		
	}
	
	private Video updateRacord(Video v,VideoDtoInput vIn) {
		v.setDescrizione(vIn.getDescrizione());
		v.setYoutube(vIn.getVideoId());
		v.setNome(vIn.getSpecificPlace());
		return v;
	}

	@Override
	public Esperienza retrieveExperienceByName(String detail) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
        return this.espJpa.findByNome(readTree.get("location").asText() ).get(0);
//		return this.espDao.getEsperienzaByName(readTree.get("location").asText());
	}

	@Override
	@Transactional
	public Integer saveDescriptionImageOfTravel(Esperienza e, MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		 Immagine i = new Immagine();
		ObjectMapper objectMapper = new ObjectMapper();
	       JsonNode readTree = objectMapper.readTree(detail);
	       i.setMainImage(readTree.get("viaggioCopertina").asInt());
	        i.setVertical(readTree.get("isVertical").asInt());
	        i.setEspImm(e);
	        i.setPathImage(file.getOriginalFilename());
		    i.setExperienceImage(0);
		    byte[] bytes = file.getBytes();
		       Path path = Paths.get(this.pathImage + file.getOriginalFilename());
		       Files.write(path, bytes);
		    return this.immagineJpa.save(i).getId();
	}
	
	private List<String> mappingNameOfTravel(List <Object[]> l){
		List<String> nameOfExperience=new ArrayList<>();
		for(Object[] result:l) {
			String name=""+result[2];
			nameOfExperience.add(name);
			
		}
		return nameOfExperience;
	}

	@Override
	@Transactional
	public List<String> retrieveAllExperienceOfTravel(String name) {
		// TODO Auto-generated method stub
//		List <Object[]> l= this.espDao.viaggiInnerJoinExperienceWhereviaggioNome(name);
//		return this.mappingNameOfTravel(l);
	return	this.vJpaRepo.viaggiInnerJoinExperienceWhereviaggioNome(name).stream().map(e->e.getNome_esperienza()).collect(Collectors.toList());
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public Esperienza addExperienceOfTravel(String detail) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
	       JsonNode readTree = objectMapper.readTree(detail);
	       String testData = readTree.get("data").asText();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	        Date d=new Date();
			try {
				d = sdf.parse(testData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       Viaggio v= this.vJpaRepo.findByNome(readTree.get("name").asText()).get(0) ;  
//		Viaggio v=this.espDao.getViaggioFromName(readTree.get("name").asText());
		Esperienza e=new Esperienza();
		e.setNome(readTree.get("location").asText());
		e.setDescrizione(readTree.get("description").asText());
		e.setData(d);
		e.setViaggio(v);
//		e.setId(espJpa.save(e).getId());
//		this.espDao.saveExperience(e);
		return espJpa.save(e);
	}


	@Override
	@Transactional
	public List<ImmDtoOut> modifyMainImageOfTravel(String name,String nameEsp) {
		// TODO Auto-generated method stub
		Newmaintravelimage oldMainImage = this.newMainImageJpaRepo.findByMainImageAndNomeViaggio(1,name);
		
		Newmaintravelimage newMainImage = this.newMainImageJpaRepo.findByNomeEsperienzaAndExperienceImage(nameEsp, 1);
		Optional<Immagine> old = this.immagineJpa.findById(oldMainImage.getIdImmagine());
		old.get().setMainImage(0);
		this.immagineJpa.save(old.get());
		Optional<Immagine> newImage = this.immagineJpa.findById(newMainImage.getIdImmagine());
		newImage.get().setMainImage(1);
		this.immagineJpa.save(newImage.get());
		//		int idOldMainImage=this.mappingViaggiMainDtoOut(this.espDao.immagineOfTravelWhereMainIsTrue(name)).get(0).getId();
//		Immagine toChange=this.espDao.getImageFromId(idOldMainImage);
//		toChange.setMainImage(0);
//		this.espDao.updateImmagine(toChange);
//		int idNewMainImage=this.mappingViaggiMainDtoOut(this.espDao.allCopertinaImageOfExperience(nameEsp)).get(0).getId();
//		Immagine newMainImage=this.espDao.getImageFromId(idNewMainImage);
//		newMainImage.setMainImage(1);
//		this.espDao.updateImmagine(newMainImage);
		return this.retrieveAllMainImage();
//		return null;
	}

	@Override
	@Transactional
	public List<ImmDtoOut> modifyNameOfTravel(String oldname, String newName) {
		// TODO Auto-generated method stub
//		Viaggio v=this.espDao.getViaggioFromName(oldname);
		Viaggio v = this.vJpaRepo.findByNome(oldname).get(0);
		v.setNome(newName);
		this.vJpaRepo.save(v);
		List<ImmDtoOut> list=new ArrayList<>();
		list= this.espJpa.immaginiWhereMainIsTrue().stream().map(i->this.immaginiMapper.toVideoDtoOut(i)).collect(Collectors.toList());
//		list=this.mappingViaggiMainDtoOut(espDao.immaginiWhereMainIsTrue());
		return  this.addNewTravelImageToMainResut(list);
	}
	
	@Override
	@Transactional
	public boolean deleteImmagine(int id) throws IOException {
		// TODO Auto-generated method stub
		boolean op=false;
		Optional<Immagine> byId = this.immagineJpa.findById(id);
		if(byId.isPresent()) {
		this.immagineJpa.deleteById(id);
//		Immagine i=this.espDao.getImageFromId(id);
		Path path = Paths.get(this.pathImage+byId.get().getPathImage());
		 op=Files.deleteIfExists(path);
//		this.espDao.removeImmagine(id);
		}
		return op;
	}

	@Override
	@Transactional
	public boolean deleteViaggio(int idViaggio) throws IOException {
		// TODO Auto-generated method stub
		EntityToDelete e=this.mappingEntityToDelete(this.espJpa.retrieveIdEntitytoDeleteByIdTravel(idViaggio));
		for(String pathImage:e.getPathImmToDelete())
		{
			Path path = Paths.get(this.pathImage+pathImage);
			Files.deleteIfExists(path);
		}
		for(Integer immImage:e.getIdImmToDelete())
			this.immagineJpa.deleteById(immImage);
//			this.espDao.removeImmagine(immImage);
		for(Integer idExp:e.getIdEsperienzeToDelete())
			this.espJpa.deleteById(idExp);
//			this.espDao.removeExperience(idExp);
//		this.espDao.removeViaggio(idViaggio);
		this.vJpaRepo.deleteById(idViaggio);
		return true;
	}


	private EntityToDelete mappingEntityToDelete(List<ViaggiDeleteDTOInterface> list) {
		List<Integer> idListImmagini=new ArrayList<>();
		Set<Integer> idListEsperienze=new TreeSet<>();
		List<String> pathImmToDelete=new ArrayList<>();
		for(ViaggiDeleteDTOInterface result:list) {
			idListImmagini.add(result.getId_immagine());
			idListEsperienze.add(result.getId_esperienza());
			pathImmToDelete.add(result.getPathImage());

		}
		EntityToDelete e=new EntityToDelete(idListImmagini,idListEsperienze,pathImmToDelete);
		System.out.println("entity to delete creata "+e);
		return e;
	}

	@Override
	@Transactional
	public void updateExperienceWithoutImage(int id, EsperienzaDtoInput exIn) {
		// TODO Auto-generated method stub
//		Esperienza e=this.espDao.getEsperienzaFromId(id);
		Optional<Esperienza> byId = this.espJpa.findById(id);
		if(byId.isEmpty())
			throw new EntityNotFoundException("Esperienza non trovata");
			Esperienza e = byId.get();
		if(!e.getDescrizione().equals(exIn.getDescrizione().trim()) || !e.getNome().equals(exIn.getNameEsperienza().trim()) || e.getData().compareTo(exIn.getData())!=0) {
		e.setDescrizione(exIn.getDescrizione().trim());
		e.setNome(exIn.getNameEsperienza().trim());
		e.setData(exIn.getData());
		System.out.println("modifica esperienza");
		this.espJpa.save(e);
		}
		System.out.println("NameGita "+exIn.getNameGita()+" gita "+e.getGita());
		Gita gEsperienza=e.getGita();
		if(gEsperienza!=null && !exIn.getNameGita().trim().equals(gEsperienza.getNome()))
			{
			System.out.println("modifica gita");
			gEsperienza.setNome(exIn.getNameGita().trim());
			this.gitaJpaRepo.save(gEsperienza);
			}
		
		
	}

	@Override
	@Transactional
	public Integer saveExperienceImage(MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		Immagine i= new Immagine();
		ObjectMapper objectMapper = new ObjectMapper();
	       JsonNode readTree = objectMapper.readTree(detail);
	       Optional<Esperienza> byId = this.espJpa.findById(readTree.get("idExperience").asInt());
	       if(byId.isEmpty())
	    	   throw new EntityNotFoundException("Entity non trovata");
//	    i.setEspImm(this.espDao.getEsperienzaFromId(readTree.get("idExperience").asInt()));
	    i.setEspImm(byId.get());
		i.setVertical(readTree.get("vertical").asInt());
		i.setPathImage(file.getOriginalFilename());
		i.setMainImage(0);
		i.setExperienceImage(0);
		byte[] bytes = file.getBytes();
	    Path path = Paths.get(this.pathImage + file.getOriginalFilename());
	    Files.write(path, bytes);
//		return this.espDao.saveImmagine(i);
	   return this.immagineJpa.save(i).getId();
	   
	}

	@Override
	@Transactional
	public boolean deleteExperience(int id) throws IOException {
		// TODO Auto-generated method stub
		this.espJpa.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public void resetMainImageOfTravel(int id) {
//		Immagine i=this.espDao.getImageFromId(id);
//		i.setMainImage(1);
//		this.espDao.updateImmagine(i);
		Optional<Immagine> byId = this.immagineJpa.findById(id);
		if(byId.isEmpty())
			throw new EntityNotFoundException();
		byId.get().setMainImage(1);
		this.immagineJpa.save(byId.get());
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExperienceDetailsDtoOut> retrieveAllTripsImage() throws IOException {
		// TODO Auto-generated method stub
//		List<Object[]> listObjectResult = this.espDao.immaginiGiteWhereExperienceImageIsTrue();
		List<ExperienceDetailsDtoOut> listObjectResult =this.espJpa.immaginiGiteWhereExperienceImageIsTrue().stream().map(e->this.immaginiMapper.toExperienceImmDtoOut(e)).collect(Collectors.toList());
		ExperienceDetailsDtoOut nVisit=new ExperienceDetailsDtoOut();
		nVisit.setNameEsperienza("New Excursion");
		nVisit.setPathImage("~/assets/images/newTrip.jpg");
		nVisit.setDemo(true);
//		List<ExperienceDetailsDtoOut> immMapped = this.mappingGiteMainExperienceDtoOut(listObjectResult);
		listObjectResult.add(nVisit);
		for(ExperienceDetailsDtoOut e:listObjectResult)
	    	if(!e.isDemo())
	    		e.setPicByte(this.getImage(e.getPathImage()));
		return listObjectResult;
	}
	
    @Transactional
	@Override
	public boolean deleteGitaFromIdExperience(int id) throws IOException {
		// TODO Auto-generated method stub
		int idGita=0;
		List<Integer> idImmToDelete=new ArrayList<Integer>();
		List<String> pathsImmToDelete=new ArrayList<String>();
//		List<Object[]> listObjectResult =this.espDao.getGitaFromIdExperience(id);
		List<GitaFromExperienceInterface> gitaFromIdExperience = this.espJpa.getGitaFromIdExperience(id);
		idImmToDelete = gitaFromIdExperience.stream()
			    .map(GitaFromExperienceInterface::getId_imm)
			    .toList();

		pathsImmToDelete = gitaFromIdExperience.stream()
			    .map(GitaFromExperienceInterface::getPathImage)
			    .toList();

			// Ottieni l'ultimo `idGita` se la lista non è vuota
			if (!gitaFromIdExperience.isEmpty()) {
			    idGita = gitaFromIdExperience.get(gitaFromIdExperience.size() - 1).getId_gita();
			}

		//		for(Object[] result:listObjectResult) {
//			
//		idGita=(Integer)result[0];
//		idImmToDelete.add((Integer)result[1]);
//		pathsImmToDelete.add((String)result[2]);
//
//		}
		for(int idImm:idImmToDelete) {
			this.immagineJpa.deleteById(idImm);
		}
		for(String path:pathsImmToDelete) {
			Path pathToDelete = Paths.get(this.pathImage+path);
			Files.deleteIfExists(pathToDelete);
		}
		this.espJpa.deleteById(id);
		this.gitaJpaRepo.deleteById(idGita);
//		this.espDao.removeGita(idGita);
//		System.out.println("idGita "+idGita);
//		this.espDao.removeExperience(id);
//		this.espDao.removeGita(idGita);
		return true;
	}

	@Override
	public List<VideoDtoOut> getVideoByExperienceId(int id) {
		// TODO Auto-generated method stub
		List<Video> list = this.videoJpa.getVideoFromExperienceId(id);
		return list.stream().map(v->this.videoMapper.fromVideoToVideoDtoOut(v)).collect(Collectors.toList());
//		List<VideoDtoOut> listOut=new ArrayList<VideoDtoOut>();
//		for(Video v:list)
//		{
//			VideoDtoOut vOut= new VideoDtoOut();
//			vOut.setDemo(v.isDemo());
//			vOut.setDescrizioneVideo(v.getDescrizione());
//			vOut.setNome(v.getNome());
//			vOut.setThumbnail(v.getThumbnail());
//			vOut.setYouTubeId(v.getYoutube());
//			vOut.setId(v.getId());
//			listOut.add(vOut);
//		}
//		return listOut;
	}

	@Override
	public NewsOfExperienceDtoOut retrieveNews() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("retrieve news");
		NewsOfExperienceDtoOut dtoOut =new NewsOfExperienceDtoOut();
		
		 List<NewsExperienceDtoOut> listVideo = this.mappingNewsVideoDtoOut(this.espDao.viaggioGitaInnerEsperienzeInnerVideoLastUpdate());
		 for(NewsExperienceDtoOut e:listVideo)
			 if(!e.getThumbnail().startsWith("~"))
		    		e.setPicByte(this.getImage(e.getThumbnail()));
		 dtoOut.setNewsVideo(listVideo);
		 List<NewsExperienceDtoOut> listViaggi = this.mappingNewsViaggiOrGitaDtoOut(this.espDao.viaggioInnerEsperienzeInnerImmaginiLastUpdate());
		 for(NewsExperienceDtoOut e:listViaggi)
			 if(!e.getThumbnail().startsWith("~"))
		    		e.setPicByte(this.getImage(e.getThumbnail()));
		 dtoOut.setNewsViaggi(listViaggi);
		 List<NewsExperienceDtoOut> listGite = this.mappingNewsViaggiOrGitaDtoOut(this.espDao.gitaInnerEsperienzeInnerImmaginiLastUpdate());
		 for(NewsExperienceDtoOut e:listGite)
			 if(!e.getThumbnail().startsWith("~"))
		    		e.setPicByte(this.getImage(e.getThumbnail()));
		 dtoOut.setNewsGite(listGite);
		 return dtoOut;
		 
	}

	private List<NewsExperienceDtoOut> mappingNewsViaggiOrGitaDtoOut(List<Object[]> list) {
		List <NewsExperienceDtoOut> outOrGitaViaggio=new ArrayList<>();
		for(Object[] result:list) {
			NewsExperienceDtoOut viaggioOrGita=new NewsExperienceDtoOut();
			viaggioOrGita.setNomeViaggioGita((String)result[0]);
			viaggioOrGita.setNomeEsperienza((String)result[1]);
			viaggioOrGita.setEsperienzaId((Integer)result[2]);
			viaggioOrGita.setThumbnail((String)result[3]);
			viaggioOrGita.setVertical((Byte)result[4]==0?false:true);
			viaggioOrGita.setMainImage((Byte)result[5]==0?false:true);
			viaggioOrGita.setExperienceImage((Byte)result[6]==0?false:true);
			viaggioOrGita.setDemo((Byte)result[8]==0?false:true);
			viaggioOrGita.setLastUpdate((Date)result[7]);
			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" video demo "+result[7]);
			outOrGitaViaggio.add(viaggioOrGita);
		}
		
		System.out.println("outVideo "+outOrGitaViaggio);
		return outOrGitaViaggio;
	}

	


	private List<NewsExperienceDtoOut> mappingNewsVideoDtoOut(List<Object[]> list) {
		List <NewsExperienceDtoOut> outVideo=new ArrayList<>();
		for(Object[] result:list) {
			NewsExperienceDtoOut video=new NewsExperienceDtoOut();
			video.setNomeViaggioGita((String)result[0]);
			video.setNomeEsperienza((String)result[1]);
			video.setNomeVideo((String)result[2]);
			video.setThumbnail((String)result[3]);
			video.setVideoId((Integer)result[5]);
			video.setYouTubeId((String)result[4]);
			video.setEsperienzaId((Integer)result[7]);
			video.setDemo((Byte)result[6]==0?false:true);
			video.setLastUpdate((Date)result[8]);
			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" video demo "+result[7]);
		    outVideo.add(video);
		}
		
		System.out.println("outVideo "+outVideo);
		return outVideo;
	}

	



}

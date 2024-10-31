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
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diver.main.esperienza.repository.EsperienzaDaoInterface;
import com.diver.main.model.Esperienza;
import com.diver.main.model.Gita;
import com.diver.main.model.Immagine;
import com.diver.main.model.Viaggio;
import com.diver.main.model.dto.EntityToDelete;
import com.diver.main.model.dto.EspDtoOut;
import com.diver.main.model.dto.EsperienzaDtoInput;
import com.diver.main.model.dto.ExperienceDetailsDtoOut;
import com.diver.main.model.dto.ImmDtoOut;
import com.diver.main.model.dto.NewsOfExperienceDtoOut;
import com.diver.main.model.dto.NewsExperienceDtoOut;
import com.diver.main.model.dto.VideoDtoInput;
import com.diver.main.model.dto.VideoDtoOut;
import com.diver.main.video.model.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EsperienzaServiceImpl implements EsperienzaServiceInterface {
	
@Autowired
EsperienzaDaoInterface espDao;
	
	/*recuperoinformazioni dei video associate alle esperienze di gite o di viaggi*/
	@Override
	public List<VideoDtoOut> retrieveAllEsperienzeWithVideoDetail() {
		// TODO Auto-generated method stub
		
		return this.mappingVideoDtoOut(espDao.viaggiGiteInnerEsperienzeInnerVideo());
	}
	
	private List<VideoDtoOut> mappingVideoDtoOut(List<Object[]> list) {
		List <VideoDtoOut> outVideo=new ArrayList<>();
		for(Object[] result:list) {
			VideoDtoOut video=new VideoDtoOut();
			video.setNomeGitaViaggio((String)result[0]);
			video.setNome((String)result[3]);
			video.setThumbnail((String)result[4]);
			video.setYouTubeId((String)result[5]);
			video.setDescrizioneVideo((String)result[2]);
			video.setId((Integer)result[6]);
			video.setDemo((Byte)result[7]==0?false:true);
			System.out.println(" dimensione result "+result.length+" viaggio nome "+result[0]+" esperienza nome "+result[1]+" video nome"+result[2]+" video thumbnail "+result[3]+" video youtube "+result[4]+" video descrizione "+result[5]+" video demo "+result[7]);
		    outVideo.add(video);
		}
		
		System.out.println("outVideo "+outVideo);
		return outVideo;
	}

	@Override
	public List<ImmDtoOut> retrieveAllMainImage() {
		// TODO Auto-generated method stub
		List<ImmDtoOut> list=new ArrayList<>();
		list=this.mappingViaggiMainDtoOut(espDao.immaginiWhereMainIsTrue());
		return  this.addNewTravelImageToMainResut(list);
		
	}
	
	@Override
	public List<ImmDtoOut> retrieveAllMainExperienceImageOfTravel(String name) {
		// TODO Auto-generated method stub
		List<ImmDtoOut> list=new ArrayList<>();
		list=this.mappingViaggiMainDtoOut(this.espDao.allMainExperienceImageOfTravel(name));
		ImmDtoOut nVisit=new ImmDtoOut();
		nVisit.setNameViaggioGita("New Visit");
		nVisit.setPathImage("~/assets/images/newVisit.jpg");
		nVisit.setDemo(true);
		list.add(nVisit);
		return  list;
	}
	

	@Override
	public List<ExperienceDetailsDtoOut> retrieveAllExperienceImageOfTravel(Integer id,String tipo) throws IOException {
		// TODO Auto-generated method stub
		List<ExperienceDetailsDtoOut> list=new ArrayList<>();
	    list=this.mappingExperienceDtoOut(this.espDao.retrieveAllImageOfExperienceById(id,tipo));
	    for(ExperienceDetailsDtoOut e:list)
	    	if(!e.getPathImage().startsWith("~"))
	    		e.setPicByte(this.getImage(e.getPathImage()));
		return  list;
	}

	
	private List<ImmDtoOut> addNewTravelImageToMainResut(List<ImmDtoOut> list){
		
		Immagine i=this.espDao.retrieveNewTravelImage();
		ImmDtoOut immOut=new ImmDtoOut();
		immOut.setId(i.getId());
		immOut.setNameViaggioGita("New Travel");
		immOut.setNameEsperienza("New Travel");
		immOut.setPathImage(i.getPathImage());
		immOut.setVertical(i.isVertical()==1?true:false);
		immOut.setMainImage(i.isMainImage()==1?true:false);
		immOut.setDemo(true);
		
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
			imm.setMainImage((Integer)result[4]==1?true:false);
			imm.setVertical((Integer)result[5]==1?true:false);
			imm.setExperienceImage((Integer)result[6]==1?true:false);
			imm.setDemo((Integer)result[7]==1?true:false);
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
		// TODO Auto-generated method stub
		List <String> list=this.espDao.allExperienceWithViaggioGitaDetail();
		List<EspDtoOut> result=new ArrayList<>();
		for(String s:list)
		{
			EspDtoOut o=new EspDtoOut();
			o.setNomeViaggioGita( s);
			result.add(o);
		}
		return result;
	}

	@Override
	public Integer saveVideo(MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
	
			VideoDtoInput vIn=objectMapper.readValue(detail, VideoDtoInput.class);
			System.out.println("video recuperato: "+vIn);//video recuperato: VideoDtoInput [name=Ponza, videoId=rycgjh, specificPlace=Cala Dell'acqua, descrizione=Fuivhkh]
		  //Esperienza e=  this.espDao.retrieveEsperienzaFromId(retrieveVideoOrGitaId(vIn.getName()));
		List <Object[]> l= this.espDao.viaggiInnerJoinExperienceWhereviaggioNome(vIn.getName());
		Esperienza e=new Esperienza() ;
		if(l==null || l.size()==0)
			l=this.espDao.giteInnerJoinExperienceWheregitaNome(vIn.getName());
			e=this.espDao.retrieveEsperienzaFromId((Integer)l.get(0)[1]);
		
			
			Video v= this.createVideoEntityToSave(e, vIn, file);
		  
		  byte[] bytes = file.getBytes();
          Path path = Paths.get(this.pathFile + file.getOriginalFilename());
          Files.write(path, bytes);
          return this.espDao.saveVideo(v);
	
		
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
         idViaggio=this.espDao.saveViaggio(v);
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
		idExperience=this.espDao.saveExperience(e);
		g.setExperience(e);
        idGita=this.espDao.saveGita(g);
        idImage=this.saveImage(file, idExperience, detail);
		// TODO Auto-generated method stub
		return idGita;
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
		Viaggio v=this.espDao.getViaggioFromId(idViaggio);
		e.setViaggio(v);
		 
		return this.espDao.saveExperience(e);
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
	       Path path = Paths.get(this.pathFile + file.getOriginalFilename());
	       Files.write(path, bytes);
	      
	       Esperienza e=this.espDao.retrieveEsperienzaFromId(idExperience);
	     i.setEspImm(e);
	     i.setPathImage(file.getOriginalFilename());
	     boolean b=readTree.get("esperienzaCopertina").asInt()==1?true:false;
	    System.out.println("test  "+b);
	     i.setExperienceImage(readTree.get("esperienzaCopertina").asInt());
	    
	     
		return this.espDao.saveImmagine(i);
	}
	
	@Override
	@Transactional
	public void updateVideoAndImage(MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		Video v=this.retrieveVideoFromVideoDtoJson(detail);
		byte[] bytes = file.getBytes();
        Path path = Paths.get(this.pathFile + file.getOriginalFilename());
        Files.write(path, bytes);
		String oldFileToDelete =v.getThumbnail();
		Path pathToDelete = Paths.get(this.pathFile+oldFileToDelete);
		if(Files.deleteIfExists(pathToDelete))
		v.setThumbnail(file.getOriginalFilename());
		else
			v.setThumbnail(oldFileToDelete);
		this.espDao.updateVideo(v);
		
	}

	@Override
	@Transactional
	public void updateExperienceImage(MultipartFile file, String detail)
			throws JsonMappingException, JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		Immagine i=this.retrieveMainExperienceImage(file, detail);
		
		 byte[] bytes = file.getBytes();
	       Path path = Paths.get(this.pathFile + file.getOriginalFilename());
	       Files.write(path, bytes);
		 this.espDao.updateImmagine(i);
		
	}
	
	private Immagine retrieveMainExperienceImage(MultipartFile file, String detail) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode readTree = objectMapper.readTree(detail);
        Immagine i= this.espDao.getImageFromId(readTree.get("id").asInt());
        String oldFileToDelete=i.getPathImage();
        Path pathToDelete = Paths.get(this.pathFile+oldFileToDelete);
    	Files.deleteIfExists(pathToDelete);
        Esperienza e=this.espDao.getEsperienzaFromId(readTree.get("idEsperienza").asInt());
        Gita g=e.getGita();
        Viaggio v=e.getViaggio();
        if(g!=null && g.getDemo()==1) {
        g.setDemo(0);
        this.espDao.updateGita(g);
        }
        else if(v!=null && v.getDemo()==1) {
        	v.setDemo(0);
        	this.espDao.updateViaggio(v);
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
        Video v=this.espDao.getVideoFromId(readTree.get("id").asInt());
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
		System.out.println("percorso file: "+this.pathFile+name);
		Path path = Paths.get(this.pathFile+name);		
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
		String nameOfFile=this.espDao.removeVideo(id);
		Path path = Paths.get(this.pathFile+nameOfFile);
		
		return Files.deleteIfExists(path);
	}

	@Override
	public VideoDtoOut getVideoById(int id) {
		// TODO Auto-generated method stub
		return this.mappingVideoDtoOut(espDao.viaggioGitaInnerEsperienzeInnerVideoId(id)).get(0);
	}

	@Override
	@Transactional
	public void updateVideoWithoutImage(int id, VideoDtoInput vIn) {
		// TODO Auto-generated method stub
		Video v=this.espDao.getVideoFromId(id);
		v=this.updateRacord(v, vIn);
		this.espDao.updateVideo(v);
		
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
		return this.espDao.getEsperienzaByName(readTree.get("location").asText());
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
		       Path path = Paths.get(this.pathFile + file.getOriginalFilename());
		       Files.write(path, bytes);
		    return this.espDao.saveImmagine(i);
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
		List <Object[]> l= this.espDao.viaggiInnerJoinExperienceWhereviaggioNome(name);
		return this.mappingNameOfTravel(l);
	}

	@Override
	@Transactional
	public Esperienza addExperienceOfTravel(String detail) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
	       JsonNode readTree = objectMapper.readTree(detail);
	       Date d=new Date(readTree.get("data").asText());
		Viaggio v=this.espDao.getViaggioFromName(readTree.get("name").asText());
		Esperienza e=new Esperienza();
		e.setNome(readTree.get("location").asText());
		e.setDescrizione(readTree.get("description").asText());
		e.setData(d);
		e.setViaggio(v);
		this.espDao.saveExperience(e);
		return e;
	}


	@Override
	@Transactional
	public List<ImmDtoOut> modifyMainImageOfTravel(String name,String nameEsp) {
		// TODO Auto-generated method stub
		int idOldMainImage=this.mappingViaggiMainDtoOut(this.espDao.immagineOfTravelWhereMainIsTrue(name)).get(0).getId();
		Immagine toChange=this.espDao.getImageFromId(idOldMainImage);
		toChange.setMainImage(0);
		this.espDao.updateImmagine(toChange);
		int idNewMainImage=this.mappingViaggiMainDtoOut(this.espDao.allCopertinaImageOfExperience(nameEsp)).get(0).getId();
		Immagine newMainImage=this.espDao.getImageFromId(idNewMainImage);
		newMainImage.setMainImage(1);
		this.espDao.updateImmagine(newMainImage);
		return this.retrieveAllMainImage();
	}

	@Override
	@Transactional
	public List<ImmDtoOut> modifyNameOfTravel(String oldname, String newName) {
		// TODO Auto-generated method stub
		Viaggio v=this.espDao.getViaggioFromName(oldname);
		v.setNome(newName);
		this.espDao.updateViaggio(v);
		List<ImmDtoOut> list=new ArrayList<>();
		list=this.mappingViaggiMainDtoOut(espDao.immaginiWhereMainIsTrue());
		return  this.addNewTravelImageToMainResut(list);
	}
	
	@Override
	@Transactional
	public boolean deleteImmagine(int id) throws IOException {
		// TODO Auto-generated method stub
		
		Immagine i=this.espDao.getImageFromId(id);
		Path path = Paths.get(this.pathFile+i.getPathImage());
		boolean op=Files.deleteIfExists(path);
		this.espDao.removeImmagine(id);
		return op;
	}

	@Override
	@Transactional
	public boolean deleteViaggio(int idViaggio) throws IOException {
		// TODO Auto-generated method stub
		EntityToDelete e=this.mappingEntityToDelete(this.espDao.retrieveIdEntitytoDeleteByIdTravel(idViaggio));
		for(String pathFile:e.getPathImmToDelete())
		{
			Path path = Paths.get(this.pathFile+pathFile);
			Files.deleteIfExists(path);
		}
		for(Integer immImage:e.getIdImmToDelete())
			this.espDao.removeImmagine(immImage);
		for(Integer idExp:e.getIdEsperienzeToDelete())
			this.espDao.removeExperience(idExp);
		this.espDao.removeViaggio(idViaggio);
		return true;
	}


	private EntityToDelete mappingEntityToDelete(List<Object[]> list) {
		List<Integer> idListImmagini=new ArrayList<>();
		Set<Integer> idListEsperienze=new TreeSet<>();
		List<String> pathImmToDelete=new ArrayList<>();
		for(Object[] result:list) {
			idListImmagini.add((Integer)result[1]);
			idListEsperienze.add((Integer)result[0]);
			pathImmToDelete.add((String)result[2]);

		}
		EntityToDelete e=new EntityToDelete(idListImmagini,idListEsperienze,pathImmToDelete);
		System.out.println("entity to delete creata "+e);
		return e;
	}

	@Override
	@Transactional
	public void updateExperienceWithoutImage(int id, EsperienzaDtoInput exIn) {
		// TODO Auto-generated method stub
		Esperienza e=this.espDao.getEsperienzaFromId(id);
		if(!e.getDescrizione().equals(exIn.getDescrizione().trim()) || !e.getNome().equals(exIn.getNameEsperienza().trim()) || e.getData().compareTo(exIn.getData())!=0) {
		e.setDescrizione(exIn.getDescrizione().trim());
		e.setNome(exIn.getNameEsperienza().trim());
		e.setData(exIn.getData());
		System.out.println("modifica esperienza");
		this.espDao.updateEsperienza(e);
		}
		System.out.println("NameGita "+exIn.getNameGita()+" gita "+e.getGita());
		Gita gEsperienza=e.getGita();
		if(gEsperienza!=null && !exIn.getNameGita().trim().equals(gEsperienza.getNome()))
			{
			System.out.println("modifica gita");
			gEsperienza.setNome(exIn.getNameGita().trim());
			this.espDao.updateGita(gEsperienza);
			}
		
		
	}

	@Override
	@Transactional
	public Integer saveExperienceImage(MultipartFile file, String detail) throws IOException {
		// TODO Auto-generated method stub
		Immagine i= new Immagine();
		ObjectMapper objectMapper = new ObjectMapper();
	       JsonNode readTree = objectMapper.readTree(detail);
		i.setEspImm(this.espDao.getEsperienzaFromId(readTree.get("idExperience").asInt()));
		i.setVertical(readTree.get("vertical").asInt());
		i.setPathImage(file.getOriginalFilename());
		i.setMainImage(0);
		i.setExperienceImage(0);
		byte[] bytes = file.getBytes();
	    Path path = Paths.get(this.pathFile + file.getOriginalFilename());
	    Files.write(path, bytes);
		return this.espDao.saveImmagine(i);
	}

	@Override
	@Transactional
	public boolean deleteExperience(int id) throws IOException {
		// TODO Auto-generated method stub
		this.espDao.removeExperience(id);
		return true;
	}

	@Override
	@Transactional
	public void resetMainImageOfTravel(int id) {
		Immagine i=this.espDao.getImageFromId(id);
		i.setMainImage(1);
		this.espDao.updateImmagine(i);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExperienceDetailsDtoOut> retrieveAllTripsImage() throws IOException {
		// TODO Auto-generated method stub
		List<Object[]> listObjectResult = this.espDao.immaginiGiteWhereExperienceImageIsTrue();
		ExperienceDetailsDtoOut nVisit=new ExperienceDetailsDtoOut();
		nVisit.setNameEsperienza("New Excursion");
		nVisit.setPathImage("~/assets/images/newTrip.jpg");
		nVisit.setDemo(true);
		List<ExperienceDetailsDtoOut> immMapped = this.mappingGiteMainExperienceDtoOut(listObjectResult);
		immMapped.add(nVisit);
		for(ExperienceDetailsDtoOut e:immMapped)
	    	if(!e.isDemo())
	    		e.setPicByte(this.getImage(e.getPathImage()));
		return immMapped;
	}
	
    @Transactional
	@Override
	public boolean deleteGitaFromIdExperience(int id) throws IOException {
		// TODO Auto-generated method stub
		int idGita=0;
		List<Integer> idImmToDelete=new ArrayList<Integer>();
		List<String> pathsImmToDelete=new ArrayList<String>();
		List<Object[]> listObjectResult =this.espDao.getGitaFromIdExperience(id);
		for(Object[] result:listObjectResult) {
			
		idGita=(Integer)result[0];
		idImmToDelete.add((Integer)result[1]);
		pathsImmToDelete.add((String)result[2]);

		}
		for(int idImm:idImmToDelete) {
			this.espDao.removeImmagine(idImm);
		}
		for(String path:pathsImmToDelete) {
			Path pathToDelete = Paths.get(this.pathFile+path);
			Files.deleteIfExists(pathToDelete);
		}
		System.out.println("idGita "+idGita);
		this.espDao.removeExperience(id);
		this.espDao.removeGita(idGita);
		return true;
	}

	@Override
	public List<VideoDtoOut> getVideoByExperienceId(int id) {
		// TODO Auto-generated method stub
		List<Video> list = this.espDao.getVideoFromExperienceId(id);
		List<VideoDtoOut> listOut=new ArrayList<VideoDtoOut>();
		for(Video v:list)
		{
			VideoDtoOut vOut= new VideoDtoOut();
			vOut.setDemo(v.isDemo());
			vOut.setDescrizioneVideo(v.getDescrizione());
			vOut.setNome(v.getNome());
			vOut.setThumbnail(v.getThumbnail());
			vOut.setYouTubeId(v.getYoutube());
			vOut.setId(v.getId());
			listOut.add(vOut);
		}
		return listOut;
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

	

	

	
	

	
	
	
	/*private Integer retrieveVideoOrGitaId(String nome) {
		Integer id=null;
		 id=this.espDao.retrieveIdViaggio(nome);
		 if(id!=null)
			 return this.espDao.retrieveIdEsperienza(id);
		 else
			 return this.espDao.retrieveIdGita(nome);
	}*/

}

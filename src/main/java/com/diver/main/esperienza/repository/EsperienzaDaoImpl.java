package com.diver.main.esperienza.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diver.main.model.Esperienza;
import com.diver.main.model.Gita;
import com.diver.main.model.Immagine;
import com.diver.main.model.Viaggio;
import com.diver.main.model.dto.VideoDtoOut;
import com.diver.main.utility.UtilityInterface;
import com.diver.main.video.model.Video;

@Repository
public class EsperienzaDaoImpl implements EsperienzaDaoInterface {

	@Autowired
	EntityManager em;

	@Override
	public List<Object[]> viaggiGiteInnerEsperienzeInnerVideo() {
	
		String nativeQuery = "SELECT  viaggi.nome AS 'nome_viaggio',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id,video.demo FROM  viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio inner join  video on  video.id_esperienza= esperienze.id union SELECT  gite.nome AS 'nome_gita',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id ,video.demo FROM  gite inner join  esperienze on  gite.id_experience= esperienze.id inner join  video on  video.id_esperienza= esperienze.id";
		Query q1 = em.createNativeQuery(nativeQuery);
		// Query q=em.createQuery(query1+ " union "+query2);
		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " video descrizione" + result[2] + " video nome " + result[3]
					+ " video thumbnail " + result[4] + " video youtube_id " + result[5] + " video demo " + result[7]);
		}
		return list;
	}

	@Override
	public List<Object[]> immaginiWhereMainIsTrue() {
		// SELECT diver.viaggi.nome AS 'nome_viaggio' , diver.esperienze.nome AS
		// 'nome_esperienza', diver.immagini.path_image, diver.immagini.id AS
		// 'id_immagine' , diver.immagini.main_image FROM diver.viaggi inner join
		// diver.esperienze on diver.viaggi.id=diver.esperienze.id_viaggio inner join
		// diver.immagini on diver.immagini.id_esp=diver.esperienze.id where
		// diver.immagini.main_image=1

		Query q = em.createQuery(
				"SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.mainImage, i.isVertical,i.experienceImage,t.demo,t.id AS id_viaggio,e.id  AS id_experience FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where i.mainImage=1");
		List<Object[]> list = q.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " immagine path_image " + result[2] + " immagine id "
					+ result[3] + " immagine main_image " + result[4] + " immagine vertical " + result[5]);
		}
		return list;
	}

	@Override
	public List<Object[]> immagineOfTravelWhereMainIsTrue(String name) {
		// SELECT diver.viaggi.nome AS 'nome_viaggio' , diver.esperienze.nome AS
		// 'nome_esperienza', diver.immagini.path_image, diver.immagini.id AS
		// 'id_immagine' , diver.immagini.main_image FROM diver.viaggi inner join
		// diver.esperienze on diver.viaggi.id=diver.esperienze.id_viaggio inner join
		// diver.immagini on diver.immagini.id_esp=diver.esperienze.id where
		// diver.immagini.main_image=1

		Query q = em.createQuery(
				"SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.mainImage, i.isVertical,i.experienceImage,t.demo,t.id AS id_viaggio,e.id AS id_experience FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where i.mainImage=1 and t.nome=?1");
		q.setParameter(1, name);
		List<Object[]> list = q.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " immagine path_image " + result[2] + " immagine id "
					+ result[3] + " immagine main_image " + result[4] + " immagine vertical " + result[5]);
		}
		return list;
	}

	@Override
	public List<Object[]> allMainExperienceImageOfTravel(String name) {
		// TODO Auto-generated method stub
		Query q = em.createQuery(
				"SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.mainImage, i.isVertical,i.experienceImage,t.demo,t.id AS id_viaggio,e.id AS id_experience FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where t.nome=?1 and i.experienceImage=true");
		q.setParameter(1, name);
		List<Object[]> list = q.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " immagine path_image " + result[2] + " immagine id "
					+ result[3] + " immagine main_image " + result[4] + " immagine vertical " + result[5]);
		}
		return list;
	}

	@Override
	public Immagine retrieveNewTravelImage() {
		// TODO Auto-generated method stub
		return em.find(Immagine.class, 1);
	}

	@Override
	public List<String> allExperienceWithViaggioGitaDetail() {
		// TODO Auto-generated method stub
		String q1 = "SELECT viaggi.nome AS 'nome_viaggio'  FROM viaggi ";
		String q2 = " SELECT gite.nome FROM gite";
		Query q = em.createNativeQuery(q1 + UtilityInterface.union + q2);
		List<String> list = q.getResultList();
		for (String result : list) {
			System.out.println(" dimensione result " + result);
		}
		return list;
	}

	/*
	 * @Override public Integer retrieveIdViaggio(String nome) { // TODO
	 * Auto-generated method stub String
	 * s="SELECT t.id FROM Viaggio t where t.nome=?1"; Query q=em.createQuery(s);
	 * q.setParameter(1, nome); List<Integer> l=q.getResultList(); if(l!=null)
	 * return l.get(0); else return null; }
	 * 
	 * @Override public Integer retrieveIdGita(String nome) { // TODO Auto-generated
	 * method stub String s="SELECT g.id_experience  FROM gite g where g.nome=?1";
	 * Query q=em.createQuery(s); q.setParameter(1, nome); List<Integer>
	 * l=q.getResultList(); if(l!=null) return l.get(0); else return null; }
	 * 
	 * @Override public Integer retrieveIdEsperienza(Integer id) { // TODO
	 * Auto-generated method stub String
	 * s="SELECT e.id  FROM Esperienza e where e.id_viaggio=?1"; Query
	 * q=em.createQuery(s); q.setParameter(1, id); List<Integer>
	 * l=q.getResultList(); if(l!=null) return l.get(0); else return null; }
	 */

	@Override
	public Esperienza retrieveEsperienzaFromId(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Esperienza.class, id);
	}

	@Override
	@Transactional
	public int saveVideo(Video v) {
		em.persist(v);
		return v.getId();
	}

	@Override
	public List<Object[]> viaggiInnerJoinExperienceWhereviaggioNome(String nome) {
		// TODO Auto-generated method stub
		String nativeQuery = "SELECT distinct  viaggi.nome AS 'nome_viaggio',   esperienze.id AS 'esperienza_id', esperienze.nome AS 'nome_esperienza'   FROM  viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio inner join immagini on esperienze.id=immagini.id_esp  where viaggi.nome=?1 order by immagini.main_image desc";
		Query q1 = em.createNativeQuery(nativeQuery);
		q1.setParameter(1, nome);
		// Query q=em.createQuery(query1+ " union "+query2);
		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0] + " esperienza id "
					+ result[1] + " esperienza nome " + result[2]);
		}
		return list;
	}

	@Override
	public List<Object[]> giteInnerJoinExperienceWheregitaNome(String nome) {
		// TODO Auto-generated method stub
		String nativeQuery = "SELECT  gite.nome AS 'nome_gita',   esperienze.id AS 'esperienza_id'   FROM  gite inner join  esperienze on  esperienze.id= gite.id_experience  where gite.nome=?1 ";
		Query q1 = em.createNativeQuery(nativeQuery);
		q1.setParameter(1, nome);
		// Query q=em.createQuery(query1+ " union "+query2);
		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(
					" dimensione result " + result.length + " gita nome " + result[0] + " esperienza id " + result[1]);
		}
		return list;
	}

	@Override
	@Transactional
	public String removeVideo(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String name = "";
		Video v = this.em.find(Video.class, id);
		name = v.getThumbnail();
		this.em.remove(v);
		return name;
	}

	@Override
	public List<Object[]> viaggioGitaInnerEsperienzeInnerVideoId(int id) {
		// TODO Auto-generated method stub
		String nativeQuery = "SELECT  viaggi.nome AS 'nome_viaggio',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id,video.demo FROM  viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio inner join  video on  video.id_esperienza= esperienze.id where video.id=?1  union SELECT  gite.nome AS 'nome_gita',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id ,video.demo FROM  gite inner join  esperienze on  gite.id_experience= esperienze.id inner join  video on  video.id_esperienza= esperienze.id  where video.id=?1";
		Query q1 = em.createNativeQuery(nativeQuery);
		q1.setParameter(1, id);
		// Query q=em.createQuery(query1+ " union "+query2);
		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " video descrizione" + result[2] + " video nome " + result[3]
					+ " video thumbnail " + result[4] + " video youtube_id " + result[5] + " video demo " + result[7]);
		}
		return list;
	}
	
// Emiliano d completare per nuova vista delle news
	@Override
	public List<Object[]> viaggioGitaInnerEsperienzeInnerVideoLastUpdate() {
		// TODO Auto-generated method stub
		String nativeQuery = "select t.*from ((SELECT  viaggi.nome AS 'nome_diver.viaggio',   esperienze.nome AS 'nome_esperienza'  ,  video.nome AS 'nome_diver.video',video.thumbnail, video.youtube_id, video.id,video.demo,esperienze.id AS 'id_esperienza',  convert(esperienze.last_update,date) AS 'data_esperienza'   FROM esperienze    inner join viaggi  on  viaggi.id= esperienze.id_viaggio inner join  video on  video.id_esperienza= esperienze.id  )  union all (SELECT  gite.nome ,  esperienze.nome   ,  video.nome , video.thumbnail, video.youtube_id, video.id ,video.demo,esperienze.id AS 'id_esperienza', convert(esperienze.last_update,date)  FROM  gite inner join  esperienze on  gite.id_experience= esperienze.id inner join  video on  video.id_esperienza= esperienze.id ) ) as t ORDER BY t.`data_esperienza` desc";
		Query q1 = em.createNativeQuery(nativeQuery);

		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " video descrizione" + result[2] + " video nome " + result[3]
					+ " video thumbnail " + result[4] + " video youtube_id " + result[5] + " video demo " + result[7]+ " esperienza id " + result[7]+" data update " + result[8]);
		}
		return list;
	}

	@Override
	public Video getVideoFromId(int id) {
		// TODO Auto-generated method stub

		return this.em.find(Video.class, id);
	}

	@Override
	public void updateVideo(Video v) {
		// TODO Auto-generated method stub
		em.persist(v);

	}

	@Override
	public int saveViaggio(Viaggio v) {
		// TODO Auto-generated method stub
		em.persist(v);
		return v.getId();
	}
	

	@Override
	public int saveGita(Gita g) {
		// TODO Auto-generated method stub
		em.persist(g);
		return g.getId();
	}

	@Override
	public Viaggio getViaggioFromId(int id) {
		// TODO Auto-generated method stub
		return this.em.find(Viaggio.class, id);
	}

	@Override
	public int saveExperience(Esperienza e) {
		// TODO Auto-generated method stub
		em.persist(e);
		return e.getId();
	}

	@Override
	public Esperienza getEsperienzaFromId(int id) {
		// TODO Auto-generated method stub
		return this.em.find(Esperienza.class, id);
	}

	@Override
	public int saveImmagine(Immagine i) {
		// TODO Auto-generated method stub
		em.persist(i);
		return i.getId();
	}

	@Override
	public Esperienza getEsperienzaByName(String nome) {
		// TODO Auto-generated method stub
		String s = "SELECT e FROM Esperienza e where e.nome=?1";
		Query q = em.createQuery(s);
		q.setParameter(1, nome);
		List<Esperienza> resultList = q.getResultList();
		return resultList.get(0);
	}

	@Override
	public Viaggio getViaggioFromName(String name) {
		// TODO Auto-generated method stub
		String s = "SELECT t FROM Viaggio t where t.nome=?1";
		Query q = em.createQuery(s);
		q.setParameter(1, name);
		List<Viaggio> resultList = q.getResultList();
		return resultList.get(0);
	}

	@Override
	public Immagine getImageFromId(int id) {
		// TODO Auto-generated method stub
		return this.em.find(Immagine.class, id);
	}

	@Override
	public void updateImmagine(Immagine i) {
		// TODO Auto-generated method stub
		this.em.persist(i);

	}

	@Override
	public List<Object[]> allCopertinaImageOfExperience(String name) {
		// TODO Auto-generated method stub
		Query q = em.createQuery(
				"SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.mainImage, i.isVertical,i.experienceImage,t.demo,t.id,e.id  AS id_esperienza FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where e.nome=?1 and i.experienceImage=1");
		q.setParameter(1, name);
		List<Object[]> list = q.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " immagine path_image " + result[2] + " immagine id "
					+ result[3] + " immagine main_image " + result[4] + " immagine vertical " + result[5]);
		}
		return list;
	}

	@Override
	public void updateViaggio(Viaggio v) {
		// TODO Auto-generated method stub
		this.em.persist(v);
	}

	@Override
	public List<Object[]> retrieveIdEntitytoDeleteByIdTravel(int id) {
		// TODO Auto-generated method stub
		Query q = em.createQuery(
				"SELECT distinct e.id AS id_esperienza,  i.id AS id_immagine , i.pathImage  FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where t.id=?1");
		q.setParameter(1, id);
		List<Object[]> list = q.getResultList();
		return list;

	}

	@Override
	public void removeImmagine(int id) {
		// TODO Auto-generated method stub
		Immagine i = this.em.find(Immagine.class, id);

		this.em.remove(i);

	}

	@Override
	public void removeExperience(int id) {
		// TODO Auto-generated method stub
		Esperienza e = this.em.find(Esperienza.class, id);
		this.em.remove(e);

	}

	@Override
	public void removeViaggio(int id) {
		// TODO Auto-generated method stub
		Viaggio v = this.em.find(Viaggio.class, id);
		this.em.remove(v);

	}


	@Override
	public List<Object[]> retrieveAllImageOfExperienceById (int id,String tipo) {
		Query q = em.createQuery(
				"SELECT  e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.mainImage, i.isVertical,i.experienceImage,e.descrizione,e.id AS id_esperienza,t.demo,e.data FROM "+ tipo+ " t JOIN t.esperienze  e JOIN e.immagini i where e.id=?1 order by i.experienceImage desc");
		q.setParameter(1, id);
		List<Object[]> list = q.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " esperienza nome " + result[0]
					+ " immagine path_image " + result[1] + " immagine id " + result[2] + " immagine main_image "
					+ result[3] + " i.isVertical " + result[4] + " immagine experienceImage " + result[5]+ " esperienza descrizione " + result[6]+ " esperienza id " + result[7]);
		}
		return list;
	}

	@Override
	public void updateEsperienza(Esperienza e) {
		// TODO Auto-generated method stub
		this.em.persist(e);
	}

	@Override
	public List<Object[]> immaginiGiteWhereExperienceImageIsTrue() {
		// TODO Auto-generated method stub
		Query q = em.createQuery(
				"SELECT  e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.isVertical,i.experienceImage,e.id  AS id_experience,g.demo,g.nome FROM Gita g JOIN g.esperienze e JOIN e.immagini i where i.experienceImage=1");
		List<Object[]> list = q.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " immagine path_image " + result[2] + " immagine id "
					+ result[3] + " immagine main_image " + result[4] + " immagine vertical " + result[5]);
		}
		return list;
	}

	@Override
	public List<Object[]> getGitaFromIdExperience(int id) {
		// TODO Auto-generated method stub
		Query q = em.createQuery(
				"SELECT  g.id AS id_Gita, i.id AS id_imm,i.pathImage FROM Gita g JOIN g.esperienze e JOIN e.immagini i where e.id=?1");
		q.setParameter(1, id);
		List<Object[]> resultList = q.getResultList();
		 System.out.println("dimensione "+resultList.size());
		 for (Object[] result : resultList) {
				System.out.println(" dimensione result " + resultList.size() + " idGita " + result[0]
						+ " idImm " + result[1] );
			}
			return resultList;
	}

	@Override
	public void removeGita(int idGita) {
		// TODO Auto-generated method stub
		Gita find = this.em.find(Gita.class, idGita);
		this.em.remove(find);
	}

	@Override
	public List<Video> getVideoFromExperienceId(int id) {
		// TODO Auto-generated method stub
		String s = "SELECT v FROM Video v JOIN v.esp e where e.id=?1";
		Query q = em.createQuery(s);
		q.setParameter(1, id);
		List<Video> resultList = q.getResultList();
		return resultList;
		
	}

	@Override
	public void updateGita(Gita gEsperienza) {
		// TODO Auto-generated method stub
		this.em.persist(gEsperienza);
		
	}

	@Override
	public List<Object[]> viaggioInnerEsperienzeInnerImmaginiLastUpdate() {
		// TODO Auto-generated method stub
		String nativeQuery = "select t.*from (SELECT  viaggi.nome AS 'nome_diver.viaggio',   esperienze.nome AS 'nome_esperienza' ,esperienze.id AS 'id_esperienza',  immagini.path_image ,  immagini.is_vertical , immagini.main_image, immagini.experience_image,  convert(esperienze.last_update,date) AS 'data_esperienza', viaggi.demo   FROM esperienze    inner join viaggi  on  viaggi.id= esperienze.id_viaggio inner join  immagini on  immagini.id_esp= esperienze.id)as t where t.experience_image=1 ORDER BY t.`data_esperienza` desc limit 3";
		Query q1 = em.createNativeQuery(nativeQuery);

		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " esperienza id" + result[2] + " immagini path " + result[3]
					+ " immagini is vertical " + result[4] + " immagini mainimage " + result[5] + " immagini experienceimage " + result[6]+ " last update" + result[7]+" demo "+ result[8]);
		}
		return list;
	}


	@Override
	public List<Object[]> gitaInnerEsperienzeInnerImmaginiLastUpdate() {
		// TODO Auto-generated method stub
		String nativeQuery = "select t.*from (SELECT  gite.nome ,   esperienze.nome AS 'nome_esperienza'   ,esperienze.id ,  immagini.path_image ,  immagini.is_vertical , immagini.main_image, immagini.experience_image, convert(esperienze.last_update,date) AS 'data_esperienza',gite.demo FROM  gite inner join  esperienze on  gite.id_experience= esperienze.id inner join  immagini on  immagini.id_esp= esperienze.id)as t where t.experience_image=1 ORDER BY t.`data_esperienza` desc limit 3";
		Query q1 = em.createNativeQuery(nativeQuery);

		List<Object[]> list = q1.getResultList();
		for (Object[] result : list) {
			System.out.println(" dimensione result " + result.length + " viaggio nome " + result[0]
					+ " esperienza nome " + result[1] + " esperienza id" + result[2] + " immagini path " + result[3]
					+ " immagini is vertical " + result[4] + " immagini mainimage " + result[5] + " immagini experienceimage " + result[6]+ " last update" + result[7]+" demo "+ result[8]);
		}
		return list;
	}

}

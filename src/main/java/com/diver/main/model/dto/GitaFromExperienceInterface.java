package com.diver.main.model.dto;

public interface GitaFromExperienceInterface {
// "SELECT  g.id AS id_Gita, i.id AS id_imm,i.pathImage AS pathImage FROM Gita g JOIN g.esperienze e JOIN e.immagini i where e.id=?1",nativeQuery = true)

	Integer getId_gita();
	Integer getId_imm();
	String getPathImage();

}

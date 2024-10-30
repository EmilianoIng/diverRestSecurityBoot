package com.diver.main.model.dto;

import java.util.List;
import java.util.Set;

public class EntityToDelete {
	
	private List<Integer> idImmToDelete;
	private List<String> pathImmToDelete;
	private Set<Integer> idEsperienzeToDelete;
	
	public List<Integer> getIdImmToDelete() {
		return idImmToDelete;
	}
	public void setIdImmToDelete(List<Integer> idImmToDelete) {
		this.idImmToDelete = idImmToDelete;
	}

	public Set<Integer> getIdEsperienzeToDelete() {
		return idEsperienzeToDelete;
	}
	public void setIdEsperienzeToDelete(Set<Integer> idEsperienzeToDelete) {
		this.idEsperienzeToDelete = idEsperienzeToDelete;
	}
	
	public List<String> getPathImmToDelete() {
		return pathImmToDelete;
	}
	public void setPathImmToDelete(List<String> pathImmToDelete) {
		this.pathImmToDelete = pathImmToDelete;
	}
	public EntityToDelete(List<Integer> idImmToDelete, Set<Integer> idEsperienzeToDelete,List<String> pathImmToDelete ) {
		super();
		this.idImmToDelete = idImmToDelete;
		this.idEsperienzeToDelete = idEsperienzeToDelete;
		this.pathImmToDelete= pathImmToDelete;
		
	}
	@Override
	public String toString() {
		return "EntityToDelete [idImmToDelete=" + idImmToDelete + ", pathImmToDelete=" + pathImmToDelete
				+ ", idEsperienzeToDelete=" + idEsperienzeToDelete + "]";
	}


}

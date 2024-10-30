package com.diver.main.user.repository;

import com.diver.main.security.model.DettagliAnagrafici;
import com.diver.main.security.model.DettagliAnagraficiDtoInput;

public interface AnagraficaDaoInterface {

	void insertDettagli(DettagliAnagrafici dett);
	DettagliAnagrafici retrieveDettagli(String username);
	void updateDettagli(DettagliAnagrafici dett);

}

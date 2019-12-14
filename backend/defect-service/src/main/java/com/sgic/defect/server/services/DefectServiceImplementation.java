package com.sgic.defect.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgic.defect.server.entities.Defect;
import com.sgic.defect.server.repositories.DefectRepository;

@Service
public class DefectServiceImplementation implements DefectService{
	
	@Autowired
	DefectRepository defectRepository;
	
	//Add Defect
	public void addDefect(Defect defect) {
		defectRepository.save(defect);
	}
	
	//Get All Defect
	public List<Defect> getAllDefect() {
		return defectRepository.findAll();
	}
	
	//Update Defect
	public void updateDefect(Defect defect) {
		defectRepository.save(defect);
	}
	
	//Check Whether Defect Id Exists
	public boolean isIdExists(Long id) {
		return defectRepository.existsById(id);
	}
	
	//Delete Defect
	public void deleteDefect(Long id) {
		defectRepository.deleteById(id);
	}	
	
	//Get Defect By Id
	public Defect getDefectById(Long id) {
		return defectRepository.findById(id).get();
	}
	
	//Get Total Defects By Severity
	public Long getTotalDefectsBySeverity(Long id) {
		return defectRepository.countBySeverityId(id);
	}
	
	//Get Total Defects By Priority
	public Long getTotalDefectsByPriority(Long id) {
		return defectRepository.countByPriorityId(id);
	}
	
	//Get Total Defects By Status
	public Long getTotalDefectsByStatus(Long id) {
		return defectRepository.countByStatusId(id);
	}	
	
	

}

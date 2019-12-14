package com.sgic.defect.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgic.defect.server.entities.Defect;

public interface DefectRepository extends JpaRepository<Defect, Long>{
	public long countBySeverityId(Long id);
	public long countByPriorityId(Long id);
	public long countByStatusId(Long id);
	
}

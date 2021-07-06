package com.farm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm.model.CropEntity;

@Repository
public interface CropJpaDao extends JpaRepository<CropEntity, Long>{

}

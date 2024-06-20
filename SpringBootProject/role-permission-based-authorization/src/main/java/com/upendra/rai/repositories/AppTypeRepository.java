package com.upendra.rai.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppSidebar;
import com.upendra.rai.entities.AppType;
import com.upendra.rai.entities.AppVersion;

@Repository
public interface AppTypeRepository extends JpaRepository<AppType, Long>{

	
//	@Query("select at from AppType at JOIN FETCH at.appVersions")
//	List<AppType>findAllWithAppType();
	
	@Query("select at from AppType at JOIN FETCH at.appVersions")
	List<AppType>findAllWithAppType();
	
	  Optional<AppType> findByAppTypeCode(String appTypeCode);
	  
	@Query("select at from AppType at where at.id=:appTypeId and at.appVersions=:appVersions")
	Optional<AppType>findbyIdAndAppType(Long appTypeId,AppVersion appVersions);
	
//	@Query("select at from AppType at where at.id=:appTypeId and at.appVersions=:appVersions")
//	  Optional<AppType> findByIdAndAppType(Long appTypeId,AppVersion appVersions);

}

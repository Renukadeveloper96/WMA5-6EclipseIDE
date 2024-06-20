package com.upendra.rai.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppSidebar;

@Repository
public interface AppSidebarRepository extends JpaRepository<AppSidebar, Long>{
	
	  @Query("select at from AppSidebar at where at.id=:appSidebarId and at.appIcon = :appIcon")
	  Optional<AppSidebar> findByIdAndAppIcon(Long appSidebarId,AppIcon appIcon);
	  
	  
	  @Query("select at from AppSidebar at where at.appIcon = :appIcon")
	  List<AppSidebar> findAllAndAppIcon(AppIcon appIcon);
	  
	  @Query("SELECT s FROM AppSidebar s JOIN FETCH s.appIcon")
	    List<AppSidebar> findAllWithAppIcon();
	  
	  @Query("select at from AppSidebar at where at.appIcon = :appIcon")
	  List<AppSidebar> findAllAndAppSidebar(AppIcon appIcon);
}

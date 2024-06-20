package com.upendra.rai.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.upendra.rai.entities.AppIcon;
import com.upendra.rai.entities.AppSidebar;
import com.upendra.rai.entities.AppType;
import com.upendra.rai.entities.BusinessCategory;
import com.upendra.rai.entities.Client;


@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {

  Optional<BusinessCategory> findByUuid(String uuid);
	
//	@Query("select at from BusinessCategory at JOIN FETCH at.client")
//	List<AppType>findAllWithAppType();
//  
  
  //2
  @Query("select bc from BusinessCategory bc JOIN FETCH bc.client")
  List<BusinessCategory>findallwithBusiness();
  
  
  // 3 it is not working properly++++++++
  @Query("select bc from BusinessCategory bc where bc.id=:businessId and bc.client=:client")
  Optional<BusinessCategory>findByIdWithClient(Long businessId,Client client);
  
  
  //1
  @Query("select bc from BusinessCategory bc where bc.client = :client")
  List<BusinessCategory> findAllAndClient(Client client);
  
//  @Query("select bc from BusinessCategory bc where bc.id=:businessId")
//  Optional<BusinessCategory>findByIdWithbusinessId(Long businessId);
//
//  @Query("select bc from BusinessCategory bc where bc.uuid=:businessCategoryId and bc.client = null")
//  Optional<BusinessCategory> findByUuidAndNullClient(String businessCategoryId);
//
//  @Query("select bc from BusinessCategory bc where bc.uuid=:businessCategoryId and bc.client = :client and bc.isActive = true")
//  Optional<BusinessCategory> findActiveByUuidAndClient(String businessCategoryId, Client client);
//
//  @Query("select max(b.sortOrder) from BusinessCategory b where b.client is null")
//  Long getMaxSortOrder();
//
//  @Query("select max(b.sortOrder) from BusinessCategory b where b.client = :client")
//  Long getMaxSortOrder(Client client);
//
//  @Query("SELECT bc from BusinessCategory bc "
//      + "where bc.sortOrder between :fromSortOrder and :toSortOrder " + "order by bc.sortOrder asc")
//  List<BusinessCategory> findAllBySortOrderAsc(Integer fromSortOrder, Integer toSortOrder);
//
//  @Query("SELECT bc from BusinessCategory bc "
//      + "where bc.sortOrder between :fromSortOrder and :toSortOrder "
//      + "order by bc.sortOrder desc")
//  List<BusinessCategory> findAllBySortOrderDesc(Integer fromSortOrder, Integer toSortOrder);
//
//  Optional<BusinessCategory> findByUuidAndClientUuid(String businessCategoryId, String clientId);
//
//  List<BusinessCategory> findByClientIsNull();

}

package com.studentcrud.repo;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.studentcrud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
//	  @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name LIKE %?1%")
//	    public Page<User> findAll(String keyword, Pageable pageable);
	
//	public Long countById(Integer id);
////	
//	@Query("Select u FROM User u WHERE CONCAT (u.id,' ',u.email,' ',u.firstName,' ',u.lastName) LIKE %?1%")
//	public Page<User> findAll(String keyword, Pageable pageable);
////	
////	
////	
////	
//	@Query("UPDATE User u Set u.enabled = ?2 WHERE u.id = ?1")
//	@Modifying
//	public void updateEnableStatus(Integer id, boolean enabled);
	
}
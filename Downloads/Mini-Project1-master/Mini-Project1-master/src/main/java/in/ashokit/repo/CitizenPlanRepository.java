package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import in.ashokit.entity.CitizenPlan;


@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan,Integer>{

	@Query("select DISTINCT cp.planStatus from CitizenPlan cp ")
	  List<String> findAllDistinctPlanStatus();
	
	@Query("select DISTINCT cp.planName from CitizenPlan cp ")
	  List<String> findAllDistinctPlanName();
	}


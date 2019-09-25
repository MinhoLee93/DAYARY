package us.flower.dayary.repository.moim;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;

public interface MoimPeopleRepository extends JpaRepository<MoimPeople, Long>{

	@Query("select count(id) as countId from MoimPeople where moim=(:no)")
	long countAllMoimPeople(@Param("no") long no);

  
	 
}

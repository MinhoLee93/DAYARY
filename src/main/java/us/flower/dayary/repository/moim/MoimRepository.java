package us.flower.dayary.repository.moim;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.Moim;

public interface MoimRepository extends JpaRepository<Moim, Long>, JpaSpecificationExecutor<Moim>{

    boolean existsByImageName(String imageName);

    
    boolean existsByIdAndPeople_id(long id,long peopleId);


	List<Moim> findByTitleLike(String title);



	Page<Moim> findAllByTitleLike(Pageable pageable, String title);
 

	Page<Moim> findAllByTitleLikeAndCategory(Pageable pageable, String string, Common common);


	Page<Moim> findAllByTitleLikeOrCategory(Pageable pageable, String string, Common common);


	List<Moim> findById(long no, Sort sort);


	Page<Moim> findAllByTitleLikeAndCategoryAndSidocode(Pageable pageable, String string, Common common,
			String sido_code);


	Page<Moim> findAllByTitleLikeAndCategoryAndSidocodeLike(Pageable pageable, String string, Common common,
			String string2);


	Page<Moim> findAllByTitleLikeAndCategoryAndSidocodeLikeAndSigooncodeLike(Pageable pageable, String string,
			Common common, String string2, String string3);

	@Modifying
	@Transactional
	@Query("UPDATE Moim SET Title = (:title),Intro = (:intro),peopleLimit = (:peopleLimit),joinCondition = (:joincondition),imageName = (:imageName),imageExtension = (:imageExtension)   WHERE Id = (:moimId)")
	void updateMoim(@Param("title") String title,@Param("intro") String intro,@Param("peopleLimit") long peopleLimit,@Param("joincondition") char joincondition,@Param("imageName") String imageName,@Param("imageExtension") String imageExtension, @Param("moimId") long moimId);


	List<Moim> findByidAndPeople_id(long moimId, long peopleId);














	
  



    //@Query("select a.email as email from People a inner join MoimPeople b on a.id=b.people where b.joinrole='study' and b.people=4")
//    @Query("select a.no as no from Moim a where a.no=1")
//	Optional<Moim> findPeopleOne(Long people_no);

	
     
   

}

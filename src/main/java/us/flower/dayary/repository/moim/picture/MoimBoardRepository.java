package us.flower.dayary.repository.moim.picture;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.DTO.MoimBoardListDTO;

public interface MoimBoardRepository extends JpaRepository<MoimBoard, Long>, MoimBoardRepositoryCustom {

	List<MoimBoard> findByToDoWriteList_id(long id);

	void deleteByToDoWriteList_id(long id);
 
	@Query(value = "select m from MoimBoard m inner join  m.moimBoardfile f WHERE m.moim=(:moim) and  m.boardGroup=(:boardGroup) and f.representImage = (:representImage)",countQuery = "select count(m) from MoimBoard m")
	List<MoimBoard> searchRepresent(@Param("boardGroup") BoardGroup boardGroup,@Param("moim") Moim moim,@Param("representImage") long representImage);

	//public Page<MoimBoardImage> search(final Pageable pageable);

	List<MoimBoard> findByBoardGroup(BoardGroup board_group_id);

	//Page<MoimBoard> findByboardGroup_idAndMoim_id(long l, Moim moim, Pageable page);

	List<MoimBoard> findByboardGroup_idAndMoim_id(long l, long no);

	Page<MoimBoard> findByMoim_id(Moim moim, Pageable page);

	//Page<MoimBoardListDTO> findByBoardGroup(Long boardGroupId, Pageable pageable);

	List<MoimBoard> findAllByMoimAndBoardGroupOrderById(Moim moim, BoardGroup boardGroup);

	

	
}

package us.flower.dayary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.Academy;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.ComunityBoard;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.BoardGroupRepository;
import us.flower.dayary.repository.ComunityBoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardGroupTest {

	@Autowired
	BoardGroupRepository brdgrouprepo;
	
	@Autowired
	ComunityBoardRepository communityboardrepo;

	
	/*group board test*/
    @Test
    public void boardGroup() {
    	
    BoardGroup brdgrp=new BoardGroup();
    
    ComunityBoard comnibrd=new ComunityBoard();
    
//    brdgrp.setNo(6L);
//    brdgrp.setName("스터디룸");
//    brdgrouprepo.save(brdgrp);
//    brdgrp.setNo(7L);
//    brdgrp.setName("독서실");
//    brdgrouprepo.save(brdgrp);
    

  // List<ComunityBoard> list=communityboardrepo.findAll();
    
   
   // List<BoardGroup> cmub=brdgrouprepo.findAll();
    //brdgrouprepo.findById(1L);
//    
    
    Optional<BoardGroup> brdgroup=brdgrouprepo.findById(1L);
    brdgroup.get().getCommunityBoard();
    
  //communityboardrepo.save(comnibrd);
    
    }
}

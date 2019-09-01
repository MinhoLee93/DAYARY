package us.flower.dayary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.repository.MoimRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoimTest {

	
	@Autowired 
	MoimRepository moimRepository;
	
	@Test
	public void moimMake() {
		Moim moim=new Moim();
		moim.builder().no(1L).title("Spring  study").intro("우리 모임은 234#$@$@#$");
		for(long i=2;i<50;i++) {
			moim.setNo(i);
			moim.setTitle("Spring  study");
			moim.setIntro("우리 모임은 234#$@$@#$");
			moimRepository.save(moim);	
		}
	
	}
}
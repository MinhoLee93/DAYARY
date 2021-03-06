package us.flower.dayary.controller.moim.meetup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import us.flower.dayary.domain.Meetup;
import us.flower.dayary.domain.MeetupPeople;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.DTO.TempData;
import us.flower.dayary.repository.moim.MoimPeopleRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.meetup.MeetupPeopleRepository;
import us.flower.dayary.repository.moim.meetup.MoimMeetUpRepository;
import us.flower.dayary.service.moim.meetup.MoimMeetUpPeopleService;
import us.flower.dayary.service.moim.meetup.MoimMeetUpService;

@Controller
public class MoimMeetUpController {
	
	@Autowired
	MoimMeetUpService moimMeetupService;
	@Autowired
	MoimMeetUpPeopleService moimmeetuppeopleService;
	
	@Autowired
	MeetupPeopleRepository meetuppeopleRepository;
	@Autowired
	MoimMeetUpRepository moimmeetupRepository;
	@Autowired
	MoimRepository moimRepository;
	@Autowired
	MoimPeopleRepository moimpeopleRepository;
	
	/**
	 * 모임 오프라인모임 탈퇴하기
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 * @throws IOException 
	 */
	@ResponseBody
	@DeleteMapping("/moimlistView/moimdetailView/moimmeetupDetailView/offmoimWithdraw/{no}/{meetupList.id}")
	public Map<String,Object> moimoffmoimWithdraw(@PathVariable("no") long no,@PathVariable("meetupList.id") long meetupListId
									  ,Model model,Meetup meetUp,HttpSession session) throws IOException {
		Map<String,Object> returnData = new HashMap<String,Object>();
	
		



		
		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
	
			
		try {
					List<MeetupPeople> meetupinfo=moimmeetuppeopleService.selectmeetupIdAndpeopleId(meetupListId,peopleId);
					long meetuppeopleNo=meetupinfo.get(0).getId();
					
					meetuppeopleRepository.deleteById(meetuppeopleNo);
				 	returnData.put("code","1"); 
				 	returnData.put("message","참여 취소완료:)");
			 
			
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		model.addAttribute("no",no);
		return returnData;
	}
//	/**
//	 * 오프라인모임 참여자 조회
//	 *
//	 * @param locale
//	 * @param Moim
//	 * @return returnData
//	 * @throws Exception
//	 * @author choiseongjun
//	 * @story TempDataDTO로 임시데이터를 받음 
//	 * @date 2019-10-11
//	 */
//    
//	@ResponseBody
//	@PostMapping("/meetuppeopleList")
//	public Map<String, Object> meetuppeopleList(@Valid @RequestBody TempData tempdata,HttpSession session) {
//	
//		System.out.println(tempdata.getNo1());
//		System.out.println(tempdata.getNo2());
//		
//		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
//		Map<String,Object> returnData = new HashMap<String,Object>();
//		People people=new People();
//		people.setId(peopleId);
//	
//		Moim moim=new Moim();
//		moim.setId(tempdata.getNo2());
//  
//		Meetup meetup=new Meetup();
//		meetup.setId(tempdata.getNo1());//오프모임키
//			 
//		 try {
//			 		List<MeetupPeople> meetuppeoplelist=meetuppeopleRepository.findByMeetup_id(tempdata.getNo1());//오프라인모임키로 내부 사람들을 조회한다.
//
//				 	returnData.put("code","1"); 
//				 	returnData.put("message","참여자 보기 조회:)");
//			
//		  }catch(Exception e) { returnData.put("code", "E3290");
//		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
//		  }
//		 
//		return returnData;
//	}
//	/**
//	 * 오프라인모임 참여자 조회
//	 *
//	 * @param locale
//	 * @param Moim
//	 * @return returnData
//	 * @throws Exception
//	 * @author choiseongjun
//	 * @story TempDataDTO로 임시데이터를 받음 
//	 * @date 2019-10-11
//	 */
//    
//	@ResponseBody
//	@PostMapping("/moimmeetupPeopleList")
//	public Map<String, Object> moimmeetupPeopleList(@Valid @RequestBody TempData tempdata,HttpSession session) {
//	
//		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
//		Map<String,Object> returnData = new HashMap<String,Object>();
//		People people=new People();
//		people.setId(peopleId);
//	
//		Moim moim=new Moim();
//		moim.setId(tempdata.getNo2());
//  
//		Meetup meetup=new Meetup();
//		meetup.setId(tempdata.getNo1());//오프모임키
//			 
//		 try {
//			 		//List<MeetupPeople> meetuppeoplelist=meetuppeopleRepository.findByMeetup_idAndPeople_id(tempdata.getNo1(),peopleId);//오프라인모임참가취소하기위해서 현재접속중인아이디와 오프라인모임넘버를 찾는다.
//
//			 		
//				 	returnData.put("code","1"); 
//				 	returnData.put("message","참여자 보기 조회:)");
//			
//		  }catch(Exception e) { returnData.put("code", "E3290");
//		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
//		  }
//		 
//		return returnData;
//	}
	/**
	 * 일반회원 오프라인모임 참여
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @story TempDataDTO로 임시데이터를 받음 
	 * @date 2019-10-11
	 */
   
	@Transactional
	@ResponseBody
	@PostMapping("/moimmeetupJoin")
	public Map<String, Object> moimgrantjoinPeople(@Valid @RequestBody TempData tempdata,HttpSession session) {
	
		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		long meetupId = tempdata.getNo1();
		long moimId = tempdata.getNo2();
		Map<String,Object> returnData = new HashMap<String,Object>();
		People people=new People();
		people.setId(peopleId);
	
		Moim moim=new Moim();
		moim.setId(moimId);
  
		Meetup meetup=new Meetup();
		meetup.setId(meetupId);//오프모임키
		
		List<MoimPeople> moimPeopleInfo=moimpeopleRepository.findByMoim_idAndPeople_id(moimId, peopleId);
		List<Moim> moimKing=moimRepository.findByidAndPeople_id(moimId, peopleId);
		long moimPeopleCount=0;
		long moimKingCount=0;
		for(int i=0;i<moimPeopleInfo.size();i++) {
			moimPeopleCount=moimPeopleInfo.get(i).getId();
		}
		for(int i=0;i<moimKing.size();i++) {
			moimKingCount=moimKing.get(i).getId();
		}
		MeetupPeople meetupPeople=new MeetupPeople();
		meetupPeople.setMoim(moim);
		meetupPeople.setPeople(people);
		meetupPeople.setMeetup(meetup);
		meetupPeople.setJoinCancel('N');
		   
			
			 
		 try {
			 if(moimPeopleCount==0 && moimKingCount==0) {
				 returnData.put("code","2"); 
				 returnData.put("message","모임 가입한 사람만 참여할수 있습니다.");
			 }else {
				 meetuppeopleRepository.save(meetupPeople);

				 returnData.put("code","1"); 
				 returnData.put("message","참여 완료:)");
			 }
			
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		 
		return returnData;
	}
	/**
	 * 오프라인 모임 생성
	 *
	 * @param locale
	 * @param Moim
	 * @return returnData
	 * @throws Exception
	 * @author choiseongjun
	 * @story TempDataDTO로 임시데이터를 받음 
	 * @date 2019-10-07
	 */
	@ResponseBody
	@PostMapping("/moimoffMake/{no}")
	public Map<String, Object> moimoffMake(@PathVariable("no") long no,@Valid @RequestBody Meetup meetUp,HttpSession session) {
		  
			long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		
			
			
			
		  Map<String,Object> returnData = new HashMap<String,Object>();
		  
		   
		 try { 
			 	moimMeetupService.moimoffMake(meetUp,peopleId,no);
			 	returnData.put("code","1"); 
			 	returnData.put("message","정모 만들기 완료:)");
		  }catch(Exception e) { returnData.put("code", "E3290");
		  		returnData.put("message", "데이터 확인 후 다시 시도해주세요."); 
		  }
		 
		return returnData;
	}
	/**
	 * 모임 오프라인모임 만들기 화면으로
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimoffMakeView/{no}")
	public String moimoffMakeView(@PathVariable("no") long no, Model model,Meetup meetUp) {

		model.addAttribute("no", no);
		return "moim/popup/moimoffMake";
	}
	/**
	 * 모임 오프라인모임 디테일 화면으로
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimlistView/moimdetailView/moimmeetupDetailView/{no}/{meetupList.id}")
	public String moimmeetupDetailView(@PathVariable("meetupList.id") long meetupListId,@PathVariable("no") long no
										,Model model,Meetup meetUp,Sort sort,HttpSession session
										, HttpServletRequest request
							            , HttpServletResponse response) {
		   
		

		

		
		 long peoplemeetupNo=0;
		long peopleId = (long) session.getAttribute("peopleId");//현재 접속중인 일반회원 번호를 던져준다
		
		List<MeetupPeople> meetupPeopleList=meetuppeopleRepository.findByMeetup_id(meetupListId);
	
		List<MeetupPeople> meetupPeopleCheck=meetuppeopleRepository.findByMeetup_idAndPeople_id(meetupListId, peopleId);
		for(int i=0;i<meetupPeopleCheck.size();i++) {
			peoplemeetupNo=meetupPeopleCheck.get(i).getId();
			
		}
		
		moimMeetupService.findmeetupMoimone(meetupListId)
						 .ifPresent(meetupList -> model.addAttribute("meetupList", meetupList));
		
		model.addAttribute("peoplemeetupNo",peoplemeetupNo);//밋업피플 넘버를 넘겨줘서 참가버튼 참여/참여 취소 여부를 분기처리한다  
		model.addAttribute("meetupPeopleList",meetupPeopleList);
		model.addAttribute("meetupListId",meetupListId);
		model.addAttribute("meetupPeopleCheck",meetupPeopleCheck); 

		return "moim/meetup/moimmeetupDetail";
	}
	/**
	 * 모임 오프라인모임리스트 화면으로
	 *
	 * @param
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimlistView/moimdetailView/moimmeetupDetail/{no}")
	public String moimmeetupDetail(@PathVariable("no") long no,Model model,Meetup meetUp,Sort sort) {
		   
		sort = sort.and(new Sort(Sort.Direction.DESC, "id"));
		List<Meetup> meetupList=moimmeetupRepository.findByMoim_id(no,sort);//오프라인 모임 내림차순정렬로 가져옴
			
		
		model.addAttribute("meetupList",meetupList);
		model.addAttribute("no",no);
		return "moim/meetup/moimmeetupList";
	}
}

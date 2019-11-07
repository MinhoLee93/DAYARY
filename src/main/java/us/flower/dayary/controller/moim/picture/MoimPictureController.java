package us.flower.dayary.controller.moim.picture;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimBoard;
import us.flower.dayary.domain.MoimBoardFile;
import us.flower.dayary.domain.DTO.MoimBoardImage;
import us.flower.dayary.domain.DTO.PageMaker;
import us.flower.dayary.domain.DTO.PageVO;
import us.flower.dayary.repository.moim.picture.MoimBoardFileRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardRepository;
import us.flower.dayary.service.moim.image.MoimImageImpl;

@Controller
public class MoimPictureController {
	
	@Autowired 
	MoimImageImpl moimiamge;
	
	@Autowired
	MoimBoardFileRepository mbfRepository;
	@Autowired
	MoimBoardRepository mbRepository;
	 /**
     * 모임 사진첩 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun 
     */
    @GetMapping("/moimDetail/{no}/moimPicture")
    public String moimPicture(@PathVariable("no") long no,Model model,PageVO vo) {
    	Pageable page = vo.makePageable(0, "id");
//    	Page<MoimBoard> result = mbRepository.findAll(mbRepository.makePredicate(null, null), page);
    	Page<MoimBoard> result;
//    	BoardGroup boardGroup = new BoardGroup();
//    	Moim moim = new Moim();
//    	
//    	moim.setId(no);
//    	boardGroup.setId(8);
//    	
//    	result = mbRepository.searchRepresent(boardGroup,moim,0,page);
//    	
    	result = moimiamge.search(page,no);
    	model.addAttribute("no",no);
    	model.addAttribute("resultList",result.getContent());
		model.addAttribute("resultListCount", result.getTotalElements());
		model.addAttribute("pageNumber", result.getTotalPages());
    	//model.addAttribute("",);
//    	
    	//System.out.println(result.getContent());
    	return "moim/moimpictureList"; 
    }
    /**
     * 모임 사진첩 글쓰기
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun 
     */
    /*
    @GetMapping("/moimDetail/{no}/moimPicture/moimPictureWrite")
    public String moimPictureWrite(@RequestParam("image") MultipartFile mtfRequest,Model model) {
    	
    	System.out.println("성공");
    	
    	return "moim/moimpictureWrite"; 
    }*/
    @GetMapping("/moimDetail/{no}/moimPicture/moimPictureWrite")
    public String uploadMoimImg(@PathVariable("no") long no,Model model) {
    	
    	model.addAttribute("no",no);
    	
    	return "moim/moimpictureWrite"; 
    }  
    @ResponseBody
    @PostMapping("/moimDetail/test")
    public Map<String,String> testMethod(HttpSession session,@RequestPart("moimId") String moidId,@RequestPart("title") String title,@RequestPart("file") MultipartFile[] file) {
    	Map<String,String> result = new HashMap<>();
    	long peopleId =  (long) session.getAttribute("peopleId");	
    	String resultStr="";   	
    	boolean check = true;
    	
    	check = moimiamge.writePost(peopleId, 8L, Long.parseLong(moidId), title,file);
    	
    	resultStr = check ? "성공" :"실패";
    	result.put("result", resultStr);
    	
    	System.out.println(title);
    	return result;
    } 
}

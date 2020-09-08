package bit.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model) {
		model.addAttribute("doc_title", "글 목록");
		
		List<BbsDto> list = service.allBbsList();
		model.addAttribute("bbslist", list);
		return "bbslist.tiles";
	}
	
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String bbswrite(Model model) {
		System.out.println("BbsController bbswrite()");
		model.addAttribute("doc_title", "글 쓰기");
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.GET)
	public String bbswriteAf(BbsDto dto) {
		System.out.println("BbsController bbswriteAf()");
		System.out.println(dto.getTitle());
		boolean isS = service.addbbs(dto);
		if(isS) {
			System.out.println("글 추가 성공");
			return "redirect:/bbslist.do";
		}
		else {
			System.out.println("글 추가 실패");
			return "redirect:/bbslist.do";
		}
	}
	
	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(String seq, Model model) {
		
		BbsDto bbs = service.getbbs(seq);
		model.addAttribute("doc_title", "글 상세보기");
		model.addAttribute("bbs", bbs);
		return "bbsdetail.tiles";
	}
}

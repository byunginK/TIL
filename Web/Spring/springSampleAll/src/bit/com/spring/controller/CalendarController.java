package bit.com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.spring.dto.CalendarDto;
import bit.com.spring.dto.CalendarParam;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.CalendarService;
import bit.com.spring.util.CalendarUtil;

@Controller
public class CalendarController {

	@Autowired
	CalendarService service;
	
	@RequestMapping(value = "calendarlist.do", method = RequestMethod.GET)
	public String calendarlist(Model model, HttpSession session, CalendarParam cal ) {
		model.addAttribute("doc_title", "일정");
		
		cal.calculate();//오늘 날짜 취득하기 위해
		//로그인한 사람의 정보가 필요함
		String id = ((MemberDto)session.getAttribute("login")).getId();
		
		String yyyymm = CalendarUtil.yyyymm(cal.getYear(), cal.getMonth());
		
		//dto set
		CalendarDto fcal = new CalendarDto();
		fcal.setId(id);
		fcal.setRdate(yyyymm);
		
		List<CalendarDto> list = service.getCalendarlist(fcal);
		model.addAttribute("flist", list);
		model.addAttribute("cal", cal);
		
		return "calendar.tiles";
	}
}

package bit.com.spring.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.CalendarDao;
import bit.com.spring.dto.CalendarDto;

@Repository
public class CalendarDaoimpl implements CalendarDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String ns = "Calendar.";

	@Override
	public List<CalendarDto> getCalendarlist(CalendarDto cal) {
		return sqlSession.selectList(ns+"getCalendar", cal);
	}
	
	
}

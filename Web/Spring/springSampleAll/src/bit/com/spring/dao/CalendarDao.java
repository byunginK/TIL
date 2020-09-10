package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.CalendarDto;

public interface CalendarDao {

	List<CalendarDto> getCalendarlist(CalendarDto cal);
}

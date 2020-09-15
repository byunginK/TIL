package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.PollBean;
import bit.com.spring.dto.PollDto;

public interface PollService {
	
	List<PollDto> getPollAllList(String id);
	
	void makePoll(PollBean pbean); //Pollbean은 polldto 와 pollsub를 합친것이다.
}

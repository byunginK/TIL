package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.PollDto;
import bit.com.spring.dto.PollSubDto;
import bit.com.spring.dto.Voter;

public interface PollDao {

	List<PollDto> getPollAllList();
	
	int isVote(Voter voter);
	
	void makePoll(PollDto poll);
	void makePollSub(PollSubDto pollsub);
}

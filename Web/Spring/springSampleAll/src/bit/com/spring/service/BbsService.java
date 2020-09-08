package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.BbsParam;

public interface BbsService {

	List<BbsDto> allBbsList();
	boolean addbbs(BbsDto bbsdto);
	BbsDto getbbs(String seq);
	boolean addreply(BbsDto bbsdto);
	boolean updatestep(BbsDto bbsdto);
	List<BbsDto> getsearchlist(BbsParam bbs);
	boolean removebbs(String seq);
	boolean updatebbs(BbsDto bbs);
}

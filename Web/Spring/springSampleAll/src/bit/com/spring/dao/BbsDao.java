package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.BbsParam;

public interface BbsDao {
	List<BbsDto> allBbsList();
	boolean addbbs(BbsDto bbsdto);
	BbsDto getbbs(String seq);
	boolean addreply(BbsDto bbsdto);
	boolean updatestep(BbsDto bbsdto);
	List<BbsDto> getsearchlist(BbsParam bbs);
	boolean removebbs(String seq);
	boolean updatebbs(BbsDto bbs);
}

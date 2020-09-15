package bit.com.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.PollDao;
import bit.com.spring.dto.PollDto;
import bit.com.spring.dto.PollSubDto;
import bit.com.spring.dto.Voter;

@Repository
public class PollDaoimpl implements PollDao {

	@Autowired
	SqlSession sqlSession;
	
	String ns = "Poll.";
	
	@Override	//모든 리스트를 불러옴
	public List<PollDto> getPollAllList() {
		return sqlSession.selectList(ns+"getPollAllList");
	}

	@Override	//투표를 했는지 안했는지 확인
	public int isVote(Voter voter) {
		return sqlSession.selectOne(ns +"isVote", voter);
	}

	@Override
	public void makePoll(PollDto poll) {
		sqlSession.insert(ns+"makePoll", poll);
	}

	@Override
	public void makePollSub(PollSubDto pollsub) {
		sqlSession.insert(ns+"makePollSub", pollsub);
	}

	
}

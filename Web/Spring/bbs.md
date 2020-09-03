# 게시판 (스프링)
### 1. 로그인 이후 BbsController으로 이동
```java
package bit.com.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.BbsService;

@Controller
public class BbsController {

	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService bbsService;
	@RequestMapping (value = "bbslist.do", method = RequestMethod.GET)
	public String gobbslist(Model model, HttpServletRequest req) {
		logger.info("gobbslist"+new Date());
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		list = bbsService.allBbsList();
		model.addAttribute("allBbsList",list);
		return "bbslist";
	}
}  
```  
### 2. Service 인터페이스와 클래스를 만들어준다.
```java
package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.BbsDto;

public interface BbsService {

	List<BbsDto> allBbsList();
	boolean addbbs(BbsDto bbsdto);
	BbsDto getbbs(String seq);
	boolean addreply(BbsDto bbsdto);
	boolean updatestep(BbsDto bbsdto);
}
```
- 실제 클래스
```java
package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.BbsDao;
import bit.com.spring.dto.BbsDto;
import bit.com.spring.service.BbsService;

@Service
public class BbsServiceimpl implements BbsService {
	
	@Autowired
	BbsDao bbsDao;
	@Override
	public List<BbsDto> allBbsList() {
		List<BbsDto> list = bbsDao.allBbsList();
		return list;
	}
	@Override
	public boolean addbbs(BbsDto bbsdto) {
		
		boolean isS = bbsDao.addbbs(bbsdto);
		return isS;
	}
	@Override
	public BbsDto getbbs(String seq) {
		BbsDto dto = bbsDao.getbbs(seq);
		return dto;
	}
	@Override
	public boolean addreply(BbsDto bbsdto) {
		boolean isS = bbsDao.addreply(bbsdto);
		return isS;
	}
	@Override
	public boolean updatestep(BbsDto bbsdto) {
		boolean isS = bbsDao.updatestep(bbsdto);
		return isS;
	}
}
```
### 3. 실제 쿼리문을 실행시켜줄 Dao 인터페이스와 클래스를 생성한다
```java
package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.BbsDto;

public interface BbsDao {
	List<BbsDto> allBbsList();
	boolean addbbs(BbsDto bbsdto);
	BbsDto getbbs(String seq);
	boolean addreply(BbsDto bbsdto);
	boolean updatestep(BbsDto bbsdto);
}
```
- 실제 dao 클래스
```java
package bit.com.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.BbsDao;
import bit.com.spring.dto.BbsDto;

@Repository
public class BbsDaoimpl implements BbsDao {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "bbs.";
	
	@Override
	public List<BbsDto> allBbsList() {
		List<BbsDto>list = sqlSession.selectList(namespace+"allbbsList");
		
		return list;
	}

	@Override
	public boolean addbbs(BbsDto bbsdto) {
		
		int result = sqlSession.insert(namespace+"addbbs", bbsdto);	
		return result>0?true:false;
	}

	@Override
	public BbsDto getbbs(String seq) {
		int seqnum = Integer.parseInt(seq);
		BbsDto dto = sqlSession.selectOne(namespace+"getbbs", seqnum);
		return dto;
	}

	@Override
	public boolean addreply(BbsDto bbsdto) {
		
		int result = sqlSession.insert(namespace+"addreply", bbsdto);
		return result>0?true:false;
	}

	@Override
	public boolean updatestep(BbsDto bbsdto) {
		int result = sqlSession.update(namespace+"updatestep", bbsdto);
		return result>0?true:false;
	}
}
```
### 4. 다오에서 실행하는 bbs 의 쿼리문 을 만들어준다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="bbs">
	<select id="allbbsList" resultType="bit.com.spring.dto.BbsDto">
		SELECT*FROM BBS ORDER BY REF DESC , STEP ASC
	</select>  
	
	<insert id="addbbs" parameterType="bit.com.spring.dto.BbsDto">
		INSERT INTO BBS(SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT)
		VALUES(SEQ_BBS.NEXTVAL,#{id},(SELECT NVL(MAX(REF),0)+1 FROM BBS),0,0,#{title},#{content},SYSDATE,0,0)
	</insert>
	
	<select id="getbbs" parameterType="java.lang.Integer" resultType="bit.com.spring.dto.BbsDto">
		SELECT* FROM BBS WHERE SEQ = #{seqnum}
	</select>
	
	<update id="updatestep" parameterType="bit.com.spring.dto.BbsDto">
		UPDATE BBS
		SET STEP = STEP +1
		WHERE REF = (SELECT REF FROM BBS WHERE SEQ = #{seq}) AND STEP > (SELECT STEP FROM BBS WHERE SEQ = #{seq})
	</update>
	
	<insert id="addreply" parameterType="bit.com.spring.dto.BbsDto">
		INSERT INTO BBS(SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT)
		VALUES(SEQ_BBS.NEXTVAL,#{id},(SELECT REF FROM BBS WHERE SEQ = #{seq}),(SELECT STEP FROM BBS WHERE SEQ = #{seq})+1,(SELECT DEPTH FROM BBS WHERE SEQ = #{seq})+1,
		#{title},#{content},SYSDATE,0,0)
	</insert>
</mapper>
```
### 5. 처음 리스트를 뿌려준다
```html
<%@page import="bit.com.spring.dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<BbsDto> list = (List<BbsDto>)request.getAttribute("allBbsList");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bbs list</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>게시판</h1>
<a href="BbsWrite.do">글쓰기</a><br><br>
<div align="center">
<table border="1" >
<col width="70"><col width="600"><col width="150">
<tr>
	<th>번호</th><th>제목</th><th >작성자</th>
</tr>
<%
if(list.size()==0){
	%>
	<tr><th colspan="3">작성된 글이 없습니다</th></tr>
	<%
}else{
	for(int i = 0; i < list.size(); i++){
		String ts ="";
		for(int j = 0; j < list.get(i).getDepth(); j++){
			
			String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
			ts = ts+nbsp;
		}
		%>
		<tr>
			<th><%=i+1%></th><td onclick="location.href='./gobbsdetail.do?seq=<%=list.get(i).getSeq()%>'"><%=ts %><%=list.get(i).getTitle()%></td><td><%=list.get(i).getId()%></td>		
		</tr>
		<%
	}
}
%>

</table>

</div><br>

<!-- 검색 부분 -->
<div align="center"> 
<select id="choice">
	<option value="sel">선택</option>
	<option value="id">작성자</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" id="search">
<button id="searchBbs">검색</button> 
</div>

<script type="text/javascript">
let choice = "";
$("#choice").change(function() {
	choice = $("#choice").val();
	
});
$("#searchBbs").click(function() {
	let search = {
			pick:choice,
			search:$("#search").val()
			};

	$.ajax({
		url:"./searchbbs.do",
		type:"get",
		datatype:"json",
		data:search,
		success:function(data){
			alert("success");
		},
		error:function(){
			alert("error");
		}
		
	});
});
</script>
</body>
</html>
```
### 6. 글쓰기 부분 누르면 html과 글 추가 컨트롤을 생성해준다
```java
<%@page import="bit.com.spring.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDto member = (MemberDto)request.getSession().getAttribute("login");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bbs write</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div>
	<form id="frm">
		<div>
		작성자: <input type="text" name="id" readonly="readonly" value="<%=member.getId()%>">
		</div>
		<div>제목: <input type="text" name="title"></div>
		<div>
			<h4>내용:</h4>
			<textarea rows="13" cols="80" name="content"></textarea>
		</div>
	</form>
	<button type="button" id="list_btn">목록</button>
	<button type="button" id="add_btn">글 추가</button>
</div>
<script type="text/javascript">
$("#list_btn").click(function(){
	location.href = "./bbslist.do";
});
$("#add_btn").click(function() {

	if($('input[name=title]').val()==""){
		alert("제목을 입력해주세요");
	}else if($('textarea[name=content]').val()==""){
		alert("내용을 입력해주세요");
	}else{
		
		let formdata = $("#frm").serialize();
		
		$.ajax({
			url:"addbbs.do",
			type:"get",
			datatype:"json",
			data: formdata,
			success:function(data){
				if(data==true){
					alert("글이 추가 되었습니다");
					location.href="./bbslist.do";
				}else{
					alert("글 추가 실패")
				}
			},
			error:function(){
				alert("error");
			}
		});
	}
});

</script>
</body>
</html>
```
### - 글쓰기 컨트롤 생성
```java
@RequestMapping(value = "BbsWrite.do", method = RequestMethod.GET)
public String gobbswrite() {
  logger.info("gobbswrite"+ new Date());

  return "bbswrite";
}

@ResponseBody
@RequestMapping(value = "addbbs.do", method = RequestMethod.GET)
public boolean addbbs(BbsDto bbsdto) {
  logger.info("addbbs "+ new Date());
  logger.info(bbsdto.toString());

  boolean isS = bbsService.addbbs(bbsdto);

  return isS;
}
```
### - 글 클릭시 자세하게 볼 수 있는 페이지와 컨트롤 생성
```java
@RequestMapping(value = "gobbsdetail.do", method = RequestMethod.GET)
public String gobbsdetail(String seq, Model model) {
  logger.info("gobbsdetail" + new Date());
  logger.info("seq: "+seq);

  BbsDto dto = bbsService.getbbs(seq);
  model.addAttribute("getbbs", dto);

  return "bbsDetail";
}
```
### - 댓글 추가시 사용할 컨트롤 생성 ( 댓글들의 step 업데이트와 insert한 댓글 두개의 메소드생성)
```java
@ResponseBody
@RequestMapping(value = "addreply.do", method = RequestMethod.GET)
public boolean addreply(BbsDto bbs) {
  logger.info("addreply"+ new Date());
  logger.info(bbs.toString());

  bbsService.updatestep(bbs);
  boolean isS = bbsService.addreply(bbs);

  return isS;
}
```

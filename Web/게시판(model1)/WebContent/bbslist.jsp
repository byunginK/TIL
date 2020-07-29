<%@page import="dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
//댓글의 depth와 이미지를 추가하는 함수   		depth =1   이면 한칸 띄어쓰기하고 이미지 
public String arrow(int depth){
	String rs = "<img src='./image/arrow.png' width='20px' height='20px'/>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	
	String ts = "";
	for(int i = 0; i<depth; i++){
		ts +=nbsp;
	}
	return depth==0?"":ts + rs;
}

%>    
    
    
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){	//세션이 날라갔을때 (시간이 많이 지나서)
	%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";	
	</script>
<%	
}
mem = (MemberDto)ologin;

BbsDao dao = BbsDao.getInstance();

List<BbsDto> list = dao.getBbsList();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4 align="right" style="background-color: #f0f0f0">환영합니다 <%=mem.getId() %>님</h4>

<h1>게시판</h1>

<div align="center">
<table border="1">
<col width="70"><col width="600"><col width="150">
<tr>
	<th>번호</th><th>제목</th><th>작성자</th>
</tr>

<%
if(list == null || list.size() == 0){
	%>
	<tr>
		<td colspan="3">작성된 글이 없습니다.</td>
	</tr>
<%
}else{
	for(int i = 0; i < list.size(); i++){
		BbsDto bbs = list.get(i);
		if(bbs.getDel()==0){
%>
			<tr>
				<th><%=i+1 %></th>
				<td>
					<%=arrow(bbs.getDepth()) %>	<!-- 여백 + 이미지 -->
					<a href="bbsdetail.jsp?seq=<%=bbs.getSeq() %>">
						<%=bbs.getTitle() %>
					</a>
				</td>
				<td align="center"><%=bbs.getId() %></td>
			</tr>
<%
		}else{
			%>
			<tr>
				<th><%=i+1 %></th>
				<td style="color: red">
					<%=arrow(bbs.getDepth()) %>	<!-- 여백 + 이미지 -->
					이 글은 삭제 되었습니다.
				</td>
				<td align="center"><%=bbs.getId() %></td>
			</tr>
			<%
		}
	}
}
%>
</table>
<a href="bbswrite.jsp">글쓰기</a>
</div>

<form action="bbssearch.jsp">
<select name="search_op">
	<option value="id">작성자</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" name="sc">
<input type="submit" value="검색">
</form>

</body>
</html>









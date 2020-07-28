<%@page import="dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
%>
	<tr>
		<th><%=i+1 %></th>
		<td>
			<a href="bbsdetail.jsp?seq=<%=bbs.getSeq() %>">
				<%=bbs.getTitle() %>
			</a>
		</td>
		<td align="center"><%=bbs.getId() %></td>
	</tr>
<%
	}
}
%>
</table>
</div>

<a href="bbswrite.jsp">글쓰기</a>

</body>
</html>









<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int seq = Integer.parseInt(request.getParameter("seq"));
BbsDao dao = BbsDao.getInstance();
BbsDto bbs = dao.getBbsdto(seq);

//System.out.println(bbs.toString());
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>작성한 글</h1>
<table border="1px solid" >
<col width="100px"><col width="300px">
<tr>
	<th colspan="2"><%=bbs.getId() %>님이 작성하신 글</th>
</tr>
<tr>
	<th>제목</th><td align="center"><%=bbs.getTitle() %></td>
</tr>
<tr>
	<th colspan="2">내용</th>
</tr>
<tr>
	<td colspan="2"><%=bbs.getContent() %></td>
</tr>
</table>
</body>
</html>
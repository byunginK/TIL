<%@page import="dto.CustUserDto"%>
<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	//custuseradd에서 submit으로 넘겨준 데이터 받기
String id = request.getParameter("id");
String name = request.getParameter("name");
String address = request.getParameter("address");

System.out.println(id+" "+name+" "+address);	//확인
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
CustUserDao dao = CustUserDao.getInstance();

boolean isS = dao.addCustUser(new CustUserDto(id,name,address));	//DB에 넣기(넘겨 받은 데이터(id,name,address)로 dto생성)

if(isS){
%>
	<script type="text/javascript">
	alert('성공적으로 추가 되었습니다.');
	location.href = "custuserlist.jsp";
	</script>
	
<%
}else{
%>
	<script type="text/javascript">
	alert('추가되지 않았습니다.');
	location.href = "custuseradd.jsp";
	</script>
<%
}
%>
</body>
</html>

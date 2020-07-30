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
if(ologin == null){	
	%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";	
	</script>
<%	
}
mem = (MemberDto)ologin;
%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h4 align="right" style="background-color: #f0f0f0">환영합니다 <%=mem.getId() %>님</h4>

<h1>게시판</h1>

<div align="center">
<table border="1" >
<col width="70"><col width="600"><col width="150">
<tr>
	<th>번호</th><th>제목</th><th >작성자</th>
</tr>


</table>
<a href="conBbs?work=write">글쓰기</a>
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

<script type="text/javascript">
$(document).ready(function() {
	let count = 0;
	$.ajax({
		url:"conBbs",
		type:"get",
		datatype : "json",
		data:"work=bbslist",
		success:function(datas) {
			//alert(datas);
			if(datas==null){
				let addlist = "<tr><th colspan='3'>"+"작성된 글이 없습니다."+"</th></tr>";
			$("#tb1").after(addlist);
			}else{
				
				$.each(datas,function(i, val){
					let addlist = "";
					let nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
					let ts = "";
					for(let j = 0; j<val.depth; j++){
						ts +=nbsp;
					}
				 	if(val.del==1){
						addlist = "<tr>"
							+	"<th>"+count+"</th>"
							+	"<td style='color: red'>"
							+		ts+"***이 글은 삭제 되었습니다.***"
							+	"</td>"
							+	"<td align='center'>"+val.id+"</td>"
							+ "</tr>"
						count++;
					}else{ 
						addlist = "<tr>"
									+	"<th>"+count+"</th>"
									+	"<td>"
									+		ts+ "<a href='conBbs?work=detail&seq="+val.seq+"'>"
									+		val.title
									+		"</a>"
									+	"</td>"
									+	"<td align='center'>"+val.id+"</td>"
									+ "</tr>"
						
						count++;
					 } 	
				 	$("tr").eq(-1).after(addlist);
				});
			}
			
		},
		error:function() {
			alert('error');
		}
		
	});
});
</script>

</body>
</html>









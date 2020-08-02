<%@page import="java.util.ArrayList"%>
<%@page import="dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

String choice = request.getParameter("choice");
String search = request.getParameter("search");
String pageNumber = request.getParameter("pageNumber");

if(choice == null || choice.equals("")){
	choice="";
}
if(search == null) {
	search = "";
	choice = "sel";
}
if(pageNumber == null){
	pageNumber = "0";
}

%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	let search = "<%=search%>";
	if(search=="") return;
	else{
		let seh = document.getElementById("search");
		seh.value= "<%=search%>";
	}
	let obj = document.getElementById("choice");
	obj.value = "<%=choice%>";
	obj.setAttribute("selected", "selected");
	
});
</script>
<%
System.out.println("setA"+" "+choice+" "+search);
%>

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

<div align="center">
<select id="choice">
	<option value="sel">선택</option>
	<option value="id">작성자</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" id="search">
<input type="button" id="search_btn" value="검색">
</div>

<script type="text/javascript">
$(document).ready(function() {
	let choice = $("#choice").val();
	let search = $("#search").val();
	let pageNumber = <%=pageNumber%>;
	$.ajax({
		url:"conBbs",
		type:"get",
		datatype : "json",
		data:"work=blist&choice="+choice+"&search="+search+"&pageNumber="+pageNumber,
		success:function(datas) {
			
			//alert("success");
			if(datas==""){
				let addlist = "<tr><th colspan='3'>"+"작성된 글이 없습니다."+"</th></tr>";
				$("tr").eq(-1).after(addlist);
			}else{
				let count2 = 1;
				$.each(datas,function(i, val){
					
					let addlist = "";
					let nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
					let ts = "";
					for(let j = 0; j<val.depth; j++){
						ts +=nbsp;
					}
				  
						addlist = "<tr>"
									+	"<th>"+count2+"</th>"
									+	"<td>"
									+		ts+ "<a href='conBbs?work=detail&seq="+val.seq+"'>"
									+		val.title
									+		"</a>"
									+	"</td>"
									+	"<td align='center'>"+val.id+"</td>"
									+ "</tr>"
						
						count2++;
					 
				 	$("tr").eq(-1).after(addlist);
				});
			}
		},
		error:function() {
			alert('error');
		}
	}); 
	
	$.ajax({
		url:'conBbs',
		type:"get",
		datatype : "json",
		data:"work=page&choice="+choice+"&search="+search,
		success:function(data) {
			let str = JSON.stringify(data);
			let bp = Number(str);
		//	alert('페이지:'+str);
			let addlist2="";
			for(let i = 0; i < bp; i++){
				if(pageNumber == i){	// 1 [2] [3] 이렇게 페이지  , 현재 페이지는 링크
					 addlist2 = "<span style='font-size: 15pt; color: blue; font-weight: bold; text-decoration: none' >"
								+(i+1)
								+"</span>&nbsp;"
					
				}else{	// 그외 페이지
					
					addlist2 = "<a href = '#none'  onclick='goPage("+i+")' style='font-size: 15pt; color: black; font-weight: bold; text-decoration: none'>"
								+[i +1]
								+"</a>&nbsp;"
					
				}
				$('a').eq(-1).before(addlist2);
			}
		},
		error: function() {
			alert('error');
		}
	});
});
$(function() {
	$("#search_btn").click(function() {
		let choice = $("#choice").val();
		let search = $("#search").val();
		location.href="conBbs?work=bsearch&choice="+choice+"&search="+search;
	});
		
});
function goPage(pageNum) {
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "bbslist.jsp?pageNumber="+pageNum+"&choice="+choice+"&search="+word; 
	
	
}

</script>
</body>
</html>









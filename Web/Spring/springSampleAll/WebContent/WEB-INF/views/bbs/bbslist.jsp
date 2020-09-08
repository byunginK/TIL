<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    


<!-- 검색 -->
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">

<form name= "frmForm1" id="_frmFormSearch" method="post">
<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
<tr>
	<td>검색</td>
	<td style="padding-left: 5px">
		<select id="_s_category" name="s_category">
			<option value="" selected="selected">선택</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="id">작성자</option>
		</select>
	</td>
	<td style="padding-left: 5px">
		<input type="text" id="_s_keyword" name="s_keyword">
	</td>
	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="btnSearch">검색</button>
		</span>
	</td>
</tr>
</table>

</form>

</div>


<!-- arrow class 생성 -->
<jsp:useBean id="ubbs" class="bit.com.spring.util.BbsArrow"/>

<table class="list_table" style="width: 85%">

<colgroup>
	<col style="width:70px">	<!-- 번호 -->
	<col style="width:auto">	
	<col style="width:100px">	<!-- 작성자 -->
</colgroup>

<thead>
	<tr>
		<th>번호</th><th>제목</th><th>작성자</th>
	</tr>
</thead>
<tbody>
	<c:if test="${empty bbslist }">
	<tr>
		<td colspan="3">작성된 글이 없습니다</td>
	</tr>
	</c:if>
	
	<c:forEach items="${bbslist }" var="bbs" varStatus="vs">
		<!-- arrow를 setting -->
		<jsp:setProperty property="depth" name="ubbs" value="${bbs.depth }"/> <!-- property는 변수명(java에 있는), name은 위에 생성한 util -->
	
		<tr class="_hover_tr">
			<td>${vs.count }</td>
			<td style="text-align: left;">
				<!-- arrow를 출력 -->
				<jsp:getProperty property="arrow" name="ubbs"/>
				
				<c:if test="${bbs.del eq 1 }">
					***이 글은 관리자에 의해서 삭제 되었습니다***
				</c:if>
				<c:if test="${bbs.del eq 0 }">
					<a href="bbsdetail.do?seq=${bbs.seq }">${bbs.title }</a>
				</c:if>
			</td>
			<td>
				${bbs.id }
			</td>
		</tr>
	</c:forEach>
</tbody>

</table>
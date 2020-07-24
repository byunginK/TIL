# model 1
### ajax를 사용하여 jsp파일을 통해 db에서 json을 불러와 출력
- JSP
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// MVC model1

// DB 접근
String userName = "홍길동";
int userNumber = 1001;

String json = "{\"num\":"+userNumber + ", \"name\":\""+userName+"\"}"; //따옴표를 쓰려고 \ 을 사용
System.out.println(json);

out.println(json);
%>
```
- HTML
```html
<p id='demo'></p>
<br>

<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$('button').click(function() {
		$.ajax({
			/////////////////////////send 
			url:"data.jsp",
			type:"get",
			datatype:"json",	// 데이터를 json 형태로 보낼기 위해 데이터 타입을 잡아줌 
			////////////////////////	data를 통해 보내주지만 현재는 안보내줌
			success:function(obj){ 
			//	alert('success');
			//	alert(obj);	문자열로 넘어온다 , data.jsp 에서 문자열
				let json = JSON.parse(obj); // 문자열을 json으로 변경
			//	alert(json);
				alert(json.name); // 이름 접근
			},
			error:function(){
				alert('error');
			}
		});
	});
});
</script>
```

# model 2
### ajax를 사용하여 servlet을 통해 db에서 json을 불러와 출력
- HTML
```html
<p id='demo'></p>
<br>
<input type="text" id="id">
<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$("button").click(function() {
		$.ajax({
			url:"custuser",
			type:"get",
			datatype:"json",
			//data:"id=ABC&pwd=123",
			data:{id:$("#id").val(),pwd:"123"},
			success:function(json){
			//	alert("success");
			//	alert(json.str);
			//	alert(json.map.title);	// 제이손 -> 맵 -> 키값
			
				let custlist = json.map.custlist; // 리스트를 받아준다
				alert(custlist[0].id);
				alert(custlist[0].name);
			},
			error:function(){
				alert("error");
			}
		});
	});
});
</script>
```
- Servlet
- *Servlet에서 Ajax으로 데이터를 보내기 위해서는 jar파일을 lib에 넣어주어야한다*

![image](https://user-images.githubusercontent.com/65350890/88362639-4136ac00-cdb8-11ea-8c5a-eb9b0e2f6ea5.png)


```java
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CustUserDto;
import net.sf.json.JSONObject;

@WebServlet("/custuser")
public class CustUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("CustUserServlet doGet");
		String id = req.getParameter("id");   // Ajax에서 보내준 데이터를 받을 수 있다
		String pwd = req.getParameter("pwd");
	//	System.out.println(id+" "+pwd);
		
		// 전송 data
		JSONObject jobj = new JSONObject();	//json으로 변환
		//jobj.put("str", "Hello");	//String 전송
		
		// hashmap을 통한 데이터 전달
		/*HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "사과");
		
		jobj.put("map", map);*/ // HashMap 전송
		
		
		// list 를 통한 데이터 전달
		List<CustUserDto> list = new ArrayList<CustUserDto>();
		list.add(new CustUserDto("aaa", "홍길동", "서울시"));
		list.add(new CustUserDto("bbb", "일지매", "수원시"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("custlist", list);	//데이터를 묶어놓은 list를 map에 넣어주고
		
		jobj.put("map", map);	// 맵을 다시 json에 넣어준다
		
		
		resp.setContentType("application/x-json; charset=UTF-8");//한글이 깨진다 그래서 인코딩
		resp.getWriter().print(jobj);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CustUserServlet doPost");
	
	}

}
```

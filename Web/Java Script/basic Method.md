# 기본 함수

## 1. Date()
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p id="demo"> </p>
<br>

<input type="text" id="text" size="50">
<br><br>

<button type="button" onclick="displayDate()">지금 시간은?</button>

<script type="text/javascript">

function displayDate() {
	document.getElementById('demo').innerHTML = Date();
	document.getElementById('text').value = Date();
}

setInterval("displayDate()", 1000);

</script>

</body>
</html>
```
Date()는 현재 시간을 불러오는 함수이다. setInterval("displayDate()", 1000); 는 displayDate()를 1000밀리세컨(1초)마다 한번식 업데이트 시켜주는 함수이다.

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id="demo"></p>

<script type="text/javascript">

let d = new Date(); ★ 현재시각을 get 생성가능

/* d = new Date(2020,10 -1,26,6,5,20,0); // setter
// 			 연도    월    일  시  분 초  밀리초		월: 0 ~ 11

d = new Date("October 12,2020 12:33:17");

document.getElementById('demo').innerHTML = d; */

document.getElementById('demo').innerHTML = d.getDay(); // 요일
document.getElementById('demo').innerHTML = d.getFullYear(); // 연도
document.getElementById('demo').innerHTML = d.getMonth() +1; //월
document.getElementById('demo').innerHTML = d.getDate();    //일
document.getElementById('demo').innerHTML = d.getHours(); // 시
document.getElementById('demo').innerHTML = d.getMinutes(); // 분
document.getElementById('demo').innerHTML = d. getSeconds(); //초
</script>

</body>
</html>
```
d = new Date(2020,10 -1,26,6,5,20,0); 처럼 사용자 설정에 맞게 시간 표시가 가능하다. 또한 new Date()로 생성후 d.getDay()등 년,월,일,시,분,초 각각 의 값을 단위별로 불러올 수 있다.


### 자바스크립트의 문자열 함수
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<p id="demo"></p>


<h3 id="h3">난 2월 마스크와 손소독제 대란이 벌어졌다. 정부가 공적 마스크를 개시하기 전이었다. </h3>

<script type="text/javascript">

let str = document.getElementById("h3").innerHTML;

let pos = str.indexOf("마스크");
	pos = str.search("정부");
	pos = str.slice(4, 9); // 4번지부터 9번지 전까지 잘라서 갖고 온다
	pos = str.substring(4, 9);
	
document.getElementById('demo').innerHTML = pos;

let str1, str2;

	str1 = 'hello';
	str2 = "hell";
	str2 = str2+ 'o';
	
	alert(str2);

	if(str1 == str2){
		alert("같은 문자열 입니다.");
	}
	
	str = 'aa,bb,cc,dd';
	let arrStr = str.split(',');
	
	alert(arrStr[2]);
	
</script>
</body>
</html>
```
자바와 대체로 비슷하다.

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

10 진수 : 128

<br>

<button type="button" onclick="func()">변환</button>

<script type="text/javascript">
function func() {
	let num = 128;
	
//	let result = num.toString(2);   // 이진수로 변환
//	let result = num.toString(8); // 8진수로 변환
	let result = num.toString(16); // 16진수로 변환
	alert(result);
	
}

</script>
```
버튼을 누르면 원하는 진법으로 변환하는 코드이다. .toString(원하는 진수)를 통해 변환 가능하다.

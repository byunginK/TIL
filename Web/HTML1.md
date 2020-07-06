# HTML (Hyper Text MakeUp Language)

## resource
- 이때까지 AWT에서 사용하였던 자원들이 Tag로 구성되어 있다.(button, textfield, link, textArea, form)
-  <태그명> 문자열 </태그명>

## HTML 과 XML의 차이
- Html -> Standard Tag(표준테그) + 사용자 지정 태그<br>
	**목적: 화면 표시, 표현, 입력, 출력	:: client언어**
- XML  -> User Tag (사용자 지정 태그)<br>
	**목적: Data제공 , Setting**
	
## 기본 틀
```html
<!DOCTYPE html>
<html>   <!--root tag (시작과 끝을 알리는 태그)-->
	<head>
		<meta charset="UTF-8">
		<title>제목</title>
		<!--
			선언, 정의
			Java Script Code
			Css Setting 
		  -->
	</head>
	<body>
  <!-- Heading  -->
	
	<h1>Hello HTML</h1>
	<h2>Hello HTML</h2>
	<h3>Hello HTML</h3>
	<h4>Hello HTML</h4>
	<h5>Hello HTML</h5>
	<h6>Hello HTML</h6>
  
  <!-- Paragraph 단락 -->

	<p>캐딜락의 신차 CT4와 CT5가 7월 2일 프리뷰 행사를</p>
	<p> 진행한 후 사전계약을 시작했다.</p>
  
  <!-- Pre Formatted 보고 있는대로 출력  -->
	<pre>
	캐딜락의 신차 CT4와 CT5
	가 7월 2일 프리뷰 행사를 진행한 후 사전계약을 시작했다. 
	캐딜락은 이름까지 모두 변경된 새로운 CT4와 CT5를
	통해 메르세데스-벤츠, BMW, 아우디 등 독일 브랜드가 주도하는 수입차 시장을 
	집중 공략할 방침이다.</pre>
	
	</body>
</html>
```
Heading 의 경우 제목으로 사용을 하고 paragraph는 한줄 한 문단을 정의한다. pre formatted은 내가 HTML에서 쓴 형식 그대로 옮겨 진다.

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: yellow">
<!-- attribute(속성) == style, property(특성) == background-color -->

<h1 style="color: blue"> H1 Tag 입니다.</h1>
<h2 style="background-color: #00ff00">H2 Tag 입니다</h2>
<h3 style="font-family: verdana">H3 Tag 입니다</h3>
<h4 style="font-size: 300%">H4 Tag 입니다.</h4>

<p style="text-align: right;">여기가 p Tag </p> 

<hr> <!-- 수평선  -->

<pre style="font-size: 200%; background-color: #ff0000"> <!-- 여러개 사용시 세미콜론으로 사용  -->
캐딜락의 신차 CT4와 CT5
가 7월 2일 프리뷰 행사를 진행한 후 사전계약을 시작했다. 
캐딜락은 이름까지 모두 변경된 새로운 CT4와 CT5를
통해 메르세데스-벤츠, BMW, 아우디 등 독일 브랜드가 주도하는 수입차 시장을 
집중 공략할 방침이다.
</pre>

<p style="font-family: courier; font-size: 200%; color: blue;">
The language for building web pages
</p>


</body>
</html>
```
Style은 속성 그 뒤 특성을 붙여 꾸밀 수 있다. boby에 배경으로 색을 주거나 Heading에 색, 배경색,글꼴,글자크기 등을 <br>
설정할 수 있다. align은 배치 정렬을 의미한다. 여러개의 속성을 사용할 경우 ; 를 붙여 준다.

### Tag 안에 Tag
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Tag 안에서 사용할 수 있는 Tag

<p> 보통의 텍스트</p>

<p><b>두꺼운 텍스트 </b></p>

<p><strong>두꺼운 텍스트</strong></p>

<p style="font-size: 200%"><b>p+attribute +bold</b></p>

<p><i>이탤릭 텍스트	</i></p>

<p>나는 성공할 것입니다. 그 점을 <em>강조</em>하고 싶습니다</p>

<h2>Html <mark>Makeup</mark> language</h2>

<p>보시는 글은 <del>삭제</del>된 글 입니다</p>

<p>이것은 <sub>아래첨자 </sub> 입니다</p>

<p>이것은<sup> 윗첨자 </sup>입니다</p>

</body>
</html>
```
결과는 아래 그림과 같다

![결과](https://user-images.githubusercontent.com/65350890/86575431-32778900-bfb2-11ea-9be1-081672444dd6.png)

### Font 및 DIV
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- font,div -->

<font size="1" color="blue">font size: 1, font color: blue</font>
<br>

<font size="2" color="#ff0000">font size: 2, font color: red</font>
<br>

<font size="3">font size: 3</font>


<pre>
기존 ATS 후속 모델인 CT4는 에스칼라 콘셉트의 디자인 아이덴티티를 계승 받아 더욱 강렬한 존재감을 나타낸다. 
전면 그릴은 캐딜락 엠블럼을 닮은 방패 형상으로 디자인되어 있으며, 
양쪽에 <font size="5" color="#ff0000"><b>헤드 램프</b></font>는 'T'형태로 되어 있으며, 
주간주행등이 에어커튼까지 쭉 내려오는 모습이 인상적이다.
</pre>

<!-- 

	div : 자체만으로 의미가 없다.
		 범위를 묶는데 많이 사용

 -->
 
<div align="center"> 
<font size = "6">6 size font</font>
<p>p tag 입니다</p>
</div>


<div style="border-style: solid;border-color: blue; margin-left: 20px; padding-top: 30px; background-color: #ffff00" >
<h1>h1 tag 입니다</h1>
<p>p tag 입니다</p>
</div>

</body>
</html>
```
div는 텍스트를 묶어서 속성이나 특성을 정해 줄 수 있다.

### 링크 연결
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- link a(anchor) -->
<p>
	<a href="http://www.google.com">구글로 이동</a>
</p>

<a href="index2.html">index2.html로 이동</a>
<br>

<a href = "./index3.html" target = "_self">index3.html로 이동</a>
<br>

<a href = "./index4.html" target = "_blank">index4.html로 이동</a>


</body>
</html>
```

### 이미지 삽입
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: blue">

<!-- <img alt="이미지 없음" src="pic.jpg">

<img alt="" src="http://img.cgv.co.kr/Movie/Thumbnail/Poster/000038/38373/38373_320.jpg">

<img alt="이미지 없음" src="d://tmp/pic.jpg"> -->

<img alt="" src="huk.gif">

<a href = "http://www.naver.com">
	<img alt="" src="huk.gif">
</a>

<img alt="" src="bird.png">
<br>

<img alt="이미지 없음" src="pic.jpg" width="200" height="100">
<br>
<br>
<hr>
<img alt="" src="http://img.cgv.co.kr/Movie/Thumbnail/Poster/000038/38373/38373_320.jpg" width = "150" hetgh = "200"align = "left"hspace="20">

<pre>
다크나이트 현재상영중
The Dark Knight

예매율 6.1%  99%
감독 : 크리스토퍼 놀란 / 배우 : 
크리스찬 베일
장르 : 액션 / 기본 : 
15세 이상, 152분, 미국
개봉 : 
2020.07.01(재개봉)
공식사이트 : www.darkknightmovie.co.kr/
</pre>
<hr>
</body>
</html>
```
이미지에 align을 한후 텍스트를 개행없이 진행하게 되면 이미지 바로 옆에 텍스트를 붙일 수 있다.

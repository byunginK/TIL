# Spring Security

### 인증의 구분을 두어 관리자와 일반 회원을 나누어 접근할 수 있는 페이지를 나눈다

### 1. spring security 라이브러리 다운
```xml
<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-core</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
```
### 2. web.xml을 설정해준다
- 필터와 시큐리티 설정xml을 연결해준다
```xml
<servlet>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<servlet-class>
  		org.springframework.web.servlet.DispatcherServlet
  	</servlet-class>
  	
  	<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>
  			/WEB-INF/spring/servlet-context.xml
  			/WEB-INF/spring/intercepter-context.xml 
  		</param-value>
  	</init-param>
  	
  	<load-on-startup>1</load-on-startup>
  	
  </servlet>
  
   <servlet-mapping>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<url-pattern>/</url-pattern>
  </servlet-mapping>
  
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>
  		/WEB-INF/spring/context-security.xml  <!--시큐리티 설정 xml연결 -->
  		/WEB-INF/spring/context-root.xml
  	</param-value>
  </context-param>
  
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  
  <!-- spring security filter -->
  <filter>
  	<filter-name>springSecurityFilterChain</filter-name>
  	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  
  <filter-mapping>
  	<filter-name>springSecurityFilterChain</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
```


### 3. context-security.xml 을 생성해 준다
- Namespaces에서 beans 와 security를 체크해서 설정해준다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-5.2.xsd">

<!-- 접근시에 로그인이 안 되어 있을 경우 , login 페이지로 강제 이동 -->
	<!-- <security:http auto-config="true" use-expressions="false">	
		<security:intercept-url pattern="/**" access="ROLE_USER"/> 어떠한 경로든 인터셉터(낚아채라) 
	</security:http> -->
	<security:http auto-config="true" use-expressions="true">	<!-- 아래 순서가 매우 중요하다 --> <!-- use-expressions = true 는 SpEL문법을 사용한다는 의미 -->
		 
		<security:intercept-url pattern="/user/**" access="hasAnyRole('ROLE_USER','ROLE_MEMBER','ROLE_ADMIN')"/>	<!-- hasAnyRole(SpEL문법) 은 안에 것 중에 하나 선택 -->
		<security:intercept-url pattern="/member/**" access="hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')"/>
		<security:intercept-url pattern="/admin/**" access="hasRole('ROLE_ADMIN')"/> <!-- 하나만 일때는 hasRole -->
		<security:intercept-url pattern="/**" access="permitAll"/> <!-- 게스트 모드는 모두 접근 가능 -->
		
		<!-- 사용자 지정 로그인 창을 아래의 주소와 내용으로 한다는 의미 -->
    <!-- default-target-url : 로그인 성공시 url -->
		<security:form-login username-parameter="loginId" password-parameter="loginPwd" login-page="/secu/loginPage" default-target-url="/"/>
		
		<!-- 접근 권한에 따른 메세지를 내보내는 링크 -->
		<security:access-denied-handler error-page="/access_deined_page"/>
	</security:http>
	
	
<!-- 우리가 필요한 부분을 셋팅 , 로그인 정보의 차등을 셋팅하는 부분 -->
	<security:authentication-manager>
		<security:authentication-provider>
			<security:user-service>
			<!-- 아래  코드는 현재 db를 접근하지 않기 때문에 임시 적으로 만들어 준것이다 -->
			<!-- 	<security:user name="guest" password="{noop}guest" authorities="ROLE_GUEST"/>
				<security:user name="user" password="{noop}userPw" authorities="ROLE_USER"/>
				 -->
				 <security:user name="user" password="{noop}userPw" authorities="ROLE_USER"/> <!-- {noop}은 스프링 4버전 이후로 암호화를 해주어야 하기때문에 암호화를 해주는 의미 -->
				 <security:user name="member" password="{noop}memPw" authorities="ROLE_MEMBER"/>
				 <security:user name="admin" password="{noop}admin" authorities="ROLE_ADMIN"/>
				 
				<!-- DB로 접근 -->
				<!-- 여기 자리 -->
				
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>	

</beans>
```
#### *참고 SpEL문법
```
#SpEL문법

hasRole("admin") - admin권한을 가지고 있어야 접근가능
hasAnyRole("admin", "user") - admin, user권한중 한가지만 가지고 있으면 접근가능

permitAll - 모두 접근가능

denyAll - 모두 접근불가능

isAnonymous() - 인증을 하지 않은 사용자일 경우(로그인하지 않은 사용자)

isRememberMe() - Remember-me 기능으로 로그인한 사용자일 경우(자동로그인 사용자)

isAuthenticated() - 인증을 한 사용자일 경우(로그인한 사용자)

isFullyAuthenticated() - 인증을 한 사용자이면서 Remember-me 기능으로 로그인하지 않은 사용자
```
### 4. 아까 만들어둔 로그인 페이지를 접근하는 컨트롤러와 접근 금지 페이지 (뷰)생성
```java
package bit.com.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "/secu/loginPage")
	public String page() throws Exception{
		return "/secu/loginPage";
	}
	
	@RequestMapping(value = "access_deined_page")
	public String accessDeinedPage() throws Exception{
		return"/access_deined_page";
	}
}
```
### 5. view 
 - 로그인 접근 할 수있는 페이지 , 접근 권한을 확인해볼 페이지
 ```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!--시큐리티를 사용하기 위해 추가 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/header.jspf" %>
</head>
<body>

<br><br>
	<div class="container text-center">
		<h1>Security Project</h1>
		<sec:authorize access="isAnonymous()">	<!-- 내장함수 접근 (누구든지 접근 가능)-->
		<h5>
			<a href='<c:url value="/secu/loginPage"/>' class="badge badge-pill badge-info">LOGIN</a>로그인해 주세요
		</h5>
		</sec:authorize>
	</div>
	<br><br>
  <!-- 아래는 접근 권한을 확인 하기위 한 페이지 -->
	<div class="container text-center col-2">
		<a href='<c:url value="/page"/>' role="button" class="btn btn-outline-secondary btn-block">GUEST</a>
		<a href='<c:url value="/user/page"/>' role="button" class="btn btn-outline-secondary btn-block">USER</a>
		<a href='<c:url value="/member/page"/>' role="button" class="btn btn-outline-secondary btn-block">MEMBER</a>
		<a href='<c:url value="/admin/page"/>' role="button" class="btn btn-outline-secondary btn-block">ADMIN</a>
	</div>

</body>
</html>
```  
- 로그인 페이지 (스프링 제공)
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/include/header.jspf"  %> <!-- css를 위한 포함이다 -->
<title>Login</title>
</head>
<body>
      <br><br>
      <div class="container text-center">
          <h1>Security Prj</h1><br>
      </div>
      <div class="container col-md-4">
	      <form class="px-4 py-3" action="/springSecurity/login" method="post"><!-- action은 프로젝트 명이다 -->
	          <div class="form-group">
	              <label for="exampleDropdownFormEmail1">ID</label>
	              <input type="text" class="form-control" name="loginId" placeholder="example">
	          </div>
	          <div class="form-group">
	              <label for="exampleDropdownFormPassword1">Password</label>
	              <input type="password" class="form-control" name="loginPwd" placeholder="Password">
	          </div>
	          <div class="form-check">
	              <label class="form-check-label">
	              <input type="checkbox" class="form-check-input">
	              Remember me
	              </label>
	          </div>
	          <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
	          <button type="submit" class="btn btn-primary">Sign in</button>
	      </form>
	      <div class="dropdown-divider"></div>
	      <a class="dropdown-item" href="#">New around here? Sign up</a>
	      <a class="dropdown-item" href="#">Forgot password?</a>
	  </div>

</body>
</html>
```
- 접근 권한 페이지(로그인시 부여되는 권한으로 접근이 되는 페이지가 있고 거부되는 페이지가 있다 )
```html
<%@include file="/WEB-INF/include/header.jspf" %>
</head>
<body>

<br><br>
<div class="container text-center">
	<h1>Guest Page</h1> <!-- 여기와 아래 권한을 알려주는곳만 각각 다르다 -->

</div>
<br><br>
<div class="container text-center">
	<h6 class="font-italic text-danger">Guest 페이지 입니다.<br>감사합니다</h6>

</div>
<br><br>

<div class="container text-center">
	<a href='<c:url value="/"/>' class="text-dark"><i class="fas fa-undo"></i></a>
</div>

</body>
</html>
```
- 접근 거부 페이지
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/header.jspf" %>
</head>
<body>
<br><br>
<div class="container text-center">
	<h1>Access Deined</h1>

</div>
<br><br>
<div class="container text-center">
	<h6 class="font-italic text-danger">접근 권한이 없습니다<br>관리자에게 문의하세요</h6>

</div>
<br><br>

<div class="container text-center">
	<a href='<c:url value="/"/>' class="text-dark"><i class="fas fa-undo"></i></a>
</div>
</body>
</html>
```
### 6. 각 접근권한이 걸린 페이지의 컨트롤러
```java
package bit.com.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {

	@RequestMapping(value = "/page")
	public String page()throws Exception{
		return "/page";
	}
	
	@RequestMapping(value = "/user/page")
	public String userPage()throws Exception{
		return "/user/page";
	}
	
	@RequestMapping(value = "/member/page")
	public String memberPage()throws Exception{
		return "/member/page";
	}
	
	@RequestMapping(value = "/admin/page")
	public String adminPage()throws Exception{
		return "/admin/page";
	}
}
```

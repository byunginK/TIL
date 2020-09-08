package bit.com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect	//어노테이션으로 aop를 사용하기위해 클래스에 설정
public class LogAop {

	
	
	@Around("within(bit.com.spring.controller.*) or within(bit.com.spring.dao.impl.*)") //아까 xml의 around이며 실행할 callback함수에 붙여준다
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		//System.out.println(signatureStr + " - 시작");	//실행 전
		
		try {
			System.out.println("loggerAop:"+signatureStr+" 메소드가 실행");
			Object obj = joinpoint.proceed(); //지정 클래스의 어떠한 메소드가 실행 시
			
			return obj;
		}finally {
			//System.out.println("실행후:"+System.currentTimeMillis());
			
			//System.out.println(signatureStr + "- 종료"); // 실행 후
		}
	}
}

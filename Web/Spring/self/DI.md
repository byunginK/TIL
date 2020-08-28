# java로 DI 구현해보기
- spring을 사용하기 이전에 java로 어떤 형식인지 감을 잡아본다

### 1. 우선 인터페이스를 사용하여 사용할 메소드를 설정한다.
#### - Main

```java
package spring.di;

import spring.di.emtity.NewlecExam;
import spring.di.entity.Exam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamConsole;

public class program {

	public static void main(String[] args) {
		
		Exam exam = new NewlecExam(); // 인터페이스를 사용하여 사용할 메소드 객체를 생성
		ExamConsole console = new InlineExamConsole(exam); //  exam 을 DI 하였다  그리고 아래 조립할수도 있고 위에 조립하여 모양을 바꿀 수 있다.
	//	ExamConsole console = new GridExamConsole(exam);
		console.print();
	}

}
```
### 2. exam의 인터페이스 구현
```java
package spring.di.entity;

public interface Exam {
	int total();
	float avg();
}
```
### 3. 인터페이스에 오버라이드한 사용한 메소드 구현
```java
package spring.di.emtity;

import spring.di.entity.Exam;

public class NewlecExam implements Exam {

	private int kor;
	private int eng;
	private int math;
	private int com;
	
	@Override
	public int total() {
		// TODO Auto-generated method stub
		return kor +eng + math +com;
	}

	@Override
	public float avg() {
		// TODO Auto-generated method stub
		return total()/4.0f;
	}

}
```

- 이렇게 구현하여 exam의 newlecExam을 생성하여 exam의 객체는 유지하면서 구현할 메소드를 사용할 수 있다.(뒤의 객체만 바꿔줄 수 있다)


- 이렇게 구한 total과 avg를 각각 다른 방식으로 찍어보려고 한다. 
- 그래서 ExamConsole 인터페이스를 활용하여 생성한 객체는 유지한채 뒤의 메소드부분만 다르게 해서 조립해준다.

### 4. ExamConsole 인터페이스 생성
```java
package spring.di.ui;

public interface ExamConsole {
	void print();
}
```
### 5. inline방식으로 출력할 메소드 구현
```java
package spring.di.ui;

import spring.di.entity.Exam;

public class InlineExamConsole implements ExamConsole {

	private Exam exam;
	
	public InlineExamConsole(Exam exam) {
		this.exam = exam;
	}

	@Override
	public void print() {
		System.out.printf("total is %d, avg is %f\n", exam.total(),exam.avg()); //출력할 메소드 구현
	}

}
```
### 6. grid방식으로 출력할 메소드 구현
```java
package spring.di.ui;

import spring.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

private Exam exam;
	
	public GridExamConsole(Exam exam) {
		this.exam = exam;
	}
	
	@Override
	public void print() {
		System.out.println("----------------");
		System.out.println("total      avg ");
		System.out.println("----------------");
		System.out.printf(" %3d      %3.2f \n", exam.total(), exam.avg());
		System.out.println("-----------------");
	}

}
```

- 아까 맨위 메인에서 보면 알 수 있듯이 생성된 console에 사용하고 싶은 메소드가 있는 객체를 조립하여 출력을 할 수 있다.
```java
	ExamConsole console = new InlineExamConsole(exam); //  exam 을 DI 하였다  그리고 아래 조립할수도 있고 위에 조립하여 모양을 바꿀 수 있다.
	ExamConsole console = new GridExamConsole(exam);
		console.print();
```

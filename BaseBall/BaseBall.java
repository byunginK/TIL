package main;

import java.util.Scanner;

public class BaseBall {

	//멤버변수
	int r_num[];
	int u_num[];
	boolean clear;
	
	// init 
	public void init() {
		r_num = new int[3];
		u_num = new int[3];
		clear = false;
		
		setRandom();
	}
	//랜덤함수
	public void setRandom() {
		boolean swit[] = new boolean[10];			// 랜덤 숫자 중복 방지		
		for (int i = 0; i < swit.length; i++) {
			swit[i] = false;    
		}
		int r, w; 			
		w =0;
		while(w<3) {
			r = (int)(Math.random()*10);		
			if(swit[r] ==false) {				
				swit[r] = true;
				r_num[w] = r + 1;			// 중복되지 않은 랜덤숫자 r_num배열에 대입
				w++;
			}	
		}
		for (int i = 0; i < r_num.length; i++) {
			System.out.println(i+" : "+r_num[i]);
		}
	}
		
	//입력
	public void userInput() {
		Scanner sc = new Scanner(System.in);
		boolean check;
		int w1;
	
		while(true) {
			check = false;	
			w1 = 0;
			while(w1 < 3) {
				System.out.println((w1 + 1)+"번째 수 = ");
				u_num[w1] = sc.nextInt();
				w1++;
			}
				
			//중복확인	
			out:for (int i = 0; i < u_num.length; i++) {
					for (int j = 0; j < u_num.length; j++) {
						if(u_num[i] == u_num[j] && i!= j) {
							check = true;   //입력한 같은 숫자가 있음
							break out;
						}
					}
				}
			if(check == false) {
				break;
			}
			System.out.println("입력한 숫자중에 중복되는 숫자가 있습니다. 다시 입력해 주십시오.");
		}
		sc.close();
	}
	
	//조건확인
	public boolean finding() {
		int strike, ball;
		strike = ball = 0;
			// ball
		for (int i = 0; i < u_num.length; i++) {
			for (int j = 0; j < r_num.length; j++) {
				if(u_num[i] == r_num[j] && i !=j) {
					ball++;
				}
			}
		}
			// strike
		for (int i = 0; i < u_num.length; i++) {
			if(u_num[i]==r_num[i]) {
				strike++;
			}
		}
			// 탈출
		if(strike > 2) {
			return  true;		             // loop문을 빠져 나오거나 Game Clear를 해주기위한 boolean으로 return값을 받는다.
		}
		System.out.println(strike+"스트라이크"+ball + "볼 입니다.");
		return  false;
	}
	
	public void loop() {                        //loop 함수를 만들고 그안에 userInput과 finding함수를 넣어준다.
		int w = 0;                                // 게임 규칙에 맞게 10번 반복 loop를 생성해준다.
		while(w<10) {
			System.out.println((w+1) + "회>>");
			
			userInput();
			
			clear = finding();
			if(clear) {
				break;
			}
			w++;
		}
	}
	
	//출력
	public void resultPrint() {
		if(clear) {
			System.out.println("Game Clear!!");
		}else {
			System.out.println("Game Over~ ");
		}	
	}
}

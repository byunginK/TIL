package main;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
//BaseBall
// 범위 1~ 10사이 숫자 
// 숫자 3개 선정(랜덤)
// 예 뽑힌 랜덤 숫자 3 7 5
// 유저가 뽑은 숫자 2 7 3 
// 자리와 숫자가 같으면 strike 숫자만 같다 ball
// 조건 ) 랜덤숫자 3가지는 무조건 달라야 한다. 
// 유저에게 숫자 3개를 입력 받아야한다. 
// start
// random 3개
// user input 3개
// processing 두 수 비교
// messege 출력  못 마추면 uset 인풋으로 다시 돌아가고 기회는 10번
// end  replay 여부 확인
		
		Scanner sc = new Scanner(System.in);
		
		int arr1[] = new int[3];
		int arr2[] = new int[3];
		boolean clear = false;
		
		boolean swit[] = new boolean[10];			//랜덤 숫자의 개수의 맞게 설정
		

		for (int i = 0; i < swit.length; i++) {
			swit[i] = false;    				// 00000 00000  전부 false로 우선 설정( 중복방지 코드 참고)
		}
		
		int r, w; 			// 아래 중복 방지 코드
		w =0;
		while(w<3) {
			r = (int)(Math.random()*10);			// r에 랜덤 숫자를 넣어준다.  나올 숫자도 맞게 설정
			if(swit[r] ==false) {				
				swit[r] = true;
				r_num[w] = r + 1;			// 0 부터 랜덤 숫자가 들어가기 때문에  +1을 해준다.
				w++;
			}	
		}
		w = 0;
		int strike, ball;
		while(w<10) {
			// user input  u1 != u2 != u3
			int w1;
			while(true) {
				boolean check = false;
				w1 = 0;
				while(w1 < 3) {
					System.out.println((w1 + 1)+"번째 수 = ");
					u_num[w1] = sc.nextInt();
					w1++;
				}
				
				//같은 숫자가 있는지 체크
			out: for (int i = 0; i < u_num.length; i++) {
					for (int j = 0; j < u_num.length; j++) {
						if(u_num[i] == u_num[j] && i!= j) {
							check = true;   //입력한 같은 숫가가 있음
							break out;
						}
					}
				}
				if(check == false) {
					break;
				}
				System.out.println("입력한 숫자중에 중복되는 숫자가 있습니다. 다시 입력해 주십시오.");
			}
			
			// process(비교)
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
				clear = true;
				break;
			}
			//메세지 출력
			System.out.println(strike+"스트라이크"+ball + "볼 입니다.");
			w++;
		}
		if(clear) {
			System.out.println("Game Clear");
		}else {
			System.out.println("Game Over");
		}
	}

}

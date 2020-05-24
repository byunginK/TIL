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
		
		int ranNum;
		int userNum;
		int arr1[] = new int[3];
		int arr2[] = new int[3];
		boolean b = true;
		
		
		for(int i = 0; i < 3; i++) {							// 랜덤 숫자 3개 중복되지 않게 배열
			ranNum = (int)(Math.random()*9)+1;
			arr1[i] = ranNum;
			for(int j = 0; j < 3; j++) {
				if(i!=j) {
					if(arr1[i] == arr1[j]) {
						ranNum = (int)(Math.random()*9)+1;
						arr1[j] = ranNum;
					}
				}
			}
		}
	while(true) {
		for(int k = 0; k < 10; k++) {							//총 기회 10번
			for( int i =0; i < 3; i++) {						//유저 숫자 3개 입력
				System.out.print("숫자를 입력하세요: ");
				userNum = sc.nextInt();
				arr2[i] = userNum;
			}
				
			for(int i =0; i < 3; i++) {					// 스트라이크인지 볼인지 확인
				if(arr1[i] == arr2[i]) {
					System.out.println("스트라이크");
				}
				else if(arr1[i] != arr2[i]) {
					System.out.println("볼");
				}
			}
			if(arr1[0] == arr2[0] && arr1[1] == arr2[1] && arr1[2] == arr2[2] ) {		//다 맞췄을경우 기회 loop에서 벗어남
				System.out.println("아웃");
				break;
			}
			else{
				System.out.println("숫자를 맞추지 못했습니다.");
			}
		}	
			
		System.out.println("다시 플레이 하시겠습니까? 1. Y / 2. N");		//재플레이 여부 확인
			System.out.print(">");
			String r = sc.next();
			if(b == r.equals("N") || b == r.equals("n")) {
				System.out.println("이용해 주셔서 감사합니다.");
				break;
			}
		}
		sc.close();
	}
}

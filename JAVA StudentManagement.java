package main;

import java.util.Scanner;

public class Mission2 {

	public static void main(String[] args) {
/*
 성적 통계 프로그램
 통계를 산출할 학생의 수 입력
 동적할당 -> student[학생수][4]
 예) 홍길동 90 100 85
  	일지매 100 95 90
 이름, 과목점수 입력
 >> 숫자 입력
 >> 숫자로 입력이 안되었다면 다시 입력
 
 1 ~ 100사이의 점수
 범위를 초과하면 "범위를 초과했습니다. 다시입력하세요" 출력
 
 국어, 수학, 영어 최고 점수
 
 국어, 수학, 영어 최저 점수
 
 모든 점수의 총합
 학생 수에 따른 평균
 */
		
		//학생수 입력
		Scanner sc = new Scanner(System.in);
		
		int studentNum = 0;
		String name;
		int korea;
		int math;
		int english;
		int w = 0;
		int koreamax=0;
		int koreamin =100;
		int mathmax=0;
		int mathmin =100;
		int englishmax=0;
		int englishmin =100;
		
		System.out.print("학생 수를 입력하세요: ");
		studentNum = sc.nextInt();
		String list[][] = new String[studentNum][4];
    
		//이름,  //과목 점수 입력 (숫자로 입력)
    
		for (int i = 0; i < list.length; i++) {
			System.out.print("이름을 입력하세요: ");
			name = sc.next();
			list[i][0] = name;
		}
		while (w<list.length) {
			while(true) {
				System.out.print(list[w][0]+"의 국어 점수를 입력하세요: ");
				korea = sc.nextInt();
				if(korea>0 && korea<100) {		// 숫자의 범위 확인
					if(korea> koreamax) {
						koreamax = korea;
					}
					if(korea <koreamin) {
						koreamin = korea;
					}
					list[w][1] = korea+"";
					break;
				}
				else {
					System.out.println("잘못된 범위 입니다. 다시입력하세요");
					continue;
				}
			}
			
			
			while(true) {
				System.out.print(list[w][0]+"의 수학 점수를 입력하세요: ");
				math = sc.nextInt();
				if(math>0 && math<100) {
					if(math> mathmax) {
						mathmax = math;
					}
					if(math <mathmin) {
						mathmin = math;
					}
					list[w][2] = math+"";
					break;
				}
				else {
					System.out.println("잘못된 범위 입니다. 다시입력하세요");
					continue;
				}
			}
		
			
			while(true) {
			System.out.print(list[w][0]+"의 영어 점수를 입력하세요: ");
			english = sc.nextInt();
				if(english>0 && english<100) {
					if(english> englishmax) {
						englishmax = english;
					}
					if(english <englishmin) {
						englishmin = english;
					}
					list[w][3] = english+"";
					w++;
					break;
				}
				else {
					System.out.println("잘못된 범위 입니다. 다시입력하세요");
					continue;
				}
			}
			
		}
    
		//과목의 최고 점수
    
		System.out.print("국어 최고점수: "+koreamax);
		System.out.println();
		System.out.print("수학 최고점수: "+mathmax);
		System.out.println();
		System.out.print("영어 최고점수: "+englishmax);
		System.out.println();
    
		//과목의 최저 점수
    
		System.out.print("국어 최저점수: "+koreamin);
		System.out.println();
		System.out.print("수학 최저점수: "+mathmin);
		System.out.println();
		System.out.print("영어 최저점수: "+englishmin);
		System.out.println();
    
		//모든 점수의 총합
    
		int score;
		int sum = 0;
		double avg =0;
		for (int i = 0; i < list.length; i++) {
			for (int j = 1; j < list[0].length; j++) {
				score = Integer.parseInt(list[i][j]);
				sum = sum + score;
				avg = sum/studentNum/list[0].length;
			}
		}	
		System.out.println("모든 점수의 총합은 : "+ sum);
	
		//학생수에 따른 평균
    
		System.out.println("모든 점수의 평균은 : "+avg);
		
		
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				System.out.print(list[i][j]+" ");
			}
		}
		sc.close();
	}
}

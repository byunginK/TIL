package main;

import java.util.Scanner;

public class MainClass {
	
	public static void main(String[] args) {
/*

String student[][];

메뉴----
1. 학생 정보 입력	insert	
2. 학생 정보 삭제	delete
3. 학생 정보 검색	search
4. 학생 정보 수정	update
5. 모든 학생 정보 출력

6. 과목 총점	
7. 과목 평균

8. 정렬
9. 데이터의 저장
 */
		
		Scanner sc = new Scanner(System.in);
		String student[][] = new String [20][4];
		int choice;
		// 데이터의 Load
		int sum  = 0;
		for (int i = 0; i < student.length; i++) {
			for (int j = 0; j < student[i].length; j++) {
				student[i][j] = "";
			}
		}
		
		while(true) {
			System.out.println("--------------메뉴");
			System.out.println("1. 학생 정보 입력");
			System.out.println("2. 학생 정보 삭제");	
			System.out.println("3. 학생 정보 검색");	
			System.out.println("4. 학생 정보 수정");	
			System.out.println("5. 모든 학생 정보 출력");	
			System.out.println("6. 과목 총점");
			System.out.println("7. 과목 평균");
			System.out.println("8. 정렬");
			
			System.out.println("메뉴를 선택해 주세요");
			System.out.print(">>");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:			
					insert(student);
					break;
				case 2:
					delete(student);
					break;
				case 3:
					search(student);
					break;
				case 4:
					update(student);
					break;
				case 5:
					printAll(student);
					break;
				case 6:
					sum =totalEng(student);
					System.out.println("영어 과목 총점: "+ sum);
					break;
				case 7:
					double avg = avgEng(student);
					System.out.println("영어 과목 평균: "+ avg);
					break;
//				case 8:
//					//sorting(student);
//					break;
			}
		}
	}
	
	//create 
	static void insert(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		int findIndex = -1;		//만약 0 이면 데이터가 다 차있을 경우 빈 곳이 없으므로 제일 첫번째에 데이터를 덮어 씌우게 된다.
		for (int i = 0; i < student.length; i++) {
			if(student[i][0].equals("")) {
				findIndex = i;
				break;
				
			}
		}
		System.out.println("findIndex:" + findIndex);
		
		System.out.print("이름:");
		String name = sc.next();
		
		System.out.print("나이:");
		String age = sc.next();
		
		System.out.print("영어:");
		String eng = sc.next();
		
		System.out.print("수학:");
		String math = sc.next();
		
		student[findIndex][0] = name;
		student[findIndex][1] = age;
		student[findIndex][2] = eng;
		student[findIndex][3] = math;
		
		System.out.println("입력 완료: "+ student[findIndex][0]);
	}

	static void delete(String student[][]) {
		Scanner sc = new Scanner(System.in);
		boolean b = false;
		
		while(b == false) {
			System.out.println("삭제할 학생 이름을 입력해주세요");
			System.out.print(">>");
			String name = sc.next();
			for (int i = 0; i < student.length; i++) {
				if(!student[i][0].equals("") && student[i][0].equals(name)) {
						student[i][0] = "";
						student[i][1] = "";
						student[i][2] = "";
						student[i][3] = "";
						b = true;
				}
				else {
					System.out.println("입력하신 학생이 없습니다.");
					break;
				}	
			}
		}
		System.out.println("삭제가 완료 되었습니다");
	}
	
	static void search(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		boolean b = false;
		
		while(b == false) {
			System.out.println("검색할 학생의 이름을 입력해 주세요");
			System.out.print(">>");
			String name = sc.next();
			
			for (int i = 0; i < student.length; i++) {
				if(!student[i][0].equals("")) {
					if(student[i][0].equals(name)) {
						System.out.println("이름: " + student[i][0]);
						System.out.println("나이: " + student[i][1]);
						System.out.println("영어: " + student[i][2]);
						System.out.println("수학: " + student[i][3]);
						b = true;
						break;
					}
				}
				else if(student[i][0].equals("") && !student[i][0].equals(name)){
					System.out.println("입력한 학생은 없습니다.");
					break;
				}
			}
		}
	}
	
	static void update(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		boolean b = false;
		while(b == false) {
			System.out.println("수정할 학생의 이름을 입력해 주세요");
			System.out.print(">>");
			String name = sc.next();
			
			for (int i = 0; i < student.length; i++) {
				if(student[i][0].equals(name)) {
					System.out.println("수정할 항목을 고르세요. 1.이름/ 2.나이/ 3.영어/ 4.수학");
					int choice = sc.nextInt();
					
					switch(choice) {
						case 1:
							System.out.println("수정할 이름을 입력해주세요");
							name = sc.next();
							student[i][0] = name;
							break;
						case 2:
							System.out.println("수정할 나이를 입력해주세요");
							String age = sc.next();
							student[i][1] = age;
							break;
						case 3:
							System.out.println("수정할 영어점수를 입력해주세요");
							String eng = sc.next();
							student[i][2] = eng;
							break;
						case 4:
							System.out.println("수정할 수학점수를 입력해주세요");
							String math = sc.next();
							student[i][3] = math;
							break;
					}
					b = true;
					
				}
				else {
					System.out.println("입력하신 학생은 없습니다.");
					break;
				}
			}
			System.out.println("수정이 완료 되었습니다.");
		}
	}
	
	static void printAll(String student[][]) {
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) {
				for (int j = 0; j < student[i].length; j++) {
					System.out.print(student[i][j]+" ");
				}	
			}
			else {
				continue;
			}
			System.out.println();
		}
	}
	
	static int totalEng(String student[][]) {
		
		int sum =0;
		
		for (int i = 0; i < student.length; i++) {
			if(!student[i][2].equals("")) {
				int num = Integer.parseInt(student[i][2]);
				sum = sum + num;
			}
			else if(student[i][2].equals("")){
				continue;
			}	
			else {
				break;
			}
		}
		int result = sum;
		
		return result;
	}
	
	
	static double avgEng(String student[][]) {
		double avg = 0;
		int sum =0;
		int count = 0;
		
		for (int i = 0; i < student.length; i++) {
			if(!student[i][2].equals("")) {
				count++;
				int num = Integer.parseInt(student[i][2]);
				sum = sum + num;
			}
			else if(student[i][2].equals("")) {
				continue;
			}
			else {
				break;
			}
		}
		
		avg = sum/count;
		return avg;
	}
	
//	static void sorting(String student[][]) {
//		int temp;
//		for (int i = 0; i < student.length; i++) {
//			if(student[i][1]=="") {
//				continue;
//			}
//			else {
//				for (int k = 0; k < student.length-1; k++) {
//					for (int j = k +1; j < student.length; j++) {
//						int n1 = Integer.parseInt(student[k][1]);
//						int n2 = Integer.parseInt(student[j][1]);
//						if(n1>n2) {
//							temp = n1;
//							n1 = n2;
//							n2 = temp;
//						}
//					}
//				}
//			}
//		}
//	}
}


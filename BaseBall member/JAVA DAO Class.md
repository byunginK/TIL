# DAO Class
```java
package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import data.FileProcess;
import dto.Batter;
import dto.Human;
import dto.Pitcher;

// Data Access Object (DAO)(model)(back end)
public class MemberDao {

	private int count;
	private Human human[];
	//file f1 = new file("d:\\temp\\memberlist.txt");
	//private String fileAddres;
	
	FileProcess fp = new FileProcess();

	// 담아둘 배열 (휴먼으로 받아야함)

	public void init() {
		
		human = new Human[20];
		count = 0;
	}

	public MemberDao() {
	}

	public void insert() { // 선수 추가
		Scanner sc = new Scanner(System.in);

		System.out.println("추가할 선수의 타입을 선택하세요 ");
		System.out.print("(1. 투수 / 2. 타자  )  : ");
		int choice = sc.nextInt();

		if (choice == 1) {
			human[count] = new Pitcher();
			pitcherInsert();
		} else if (choice == 2) {
			human[count] = new Batter();
			batterInsert();
		}
		count++;
	}

	public void delete() {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 선수 이름을 입력하세요: ");
		String name = sc.next();

		for (int i = 0; i < human.length; i++) {
			if (human[i] != null) {
				String pn = human[i].getName();
				if (pn.equals(name)) {
					human[i] = null;
					System.out.println("삭제 되었습니다.");
					break;
				} else {
					System.out.println("해당 선수는 없습니다.");
					break;
				}
			}
		}
	}

	public void select() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 선수 이름을 입력하세요: ");
		String name = sc.next();

		for (int i = 0; i < human.length; i++) {
			if (human[i] != null) {
				String pn = human[i].getName();
				if (pn.equals(name)) {
					System.out.println("이름: " + human[i].getName());
					System.out.println("나이: " + human[i].getAge());
					System.out.println("선수 번호: " + human[i].getNumber());
					System.out.println("신장: " + human[i].getHeight());
					if (human[i] instanceof Pitcher) {
						pitcherCatego(i);
					} else if (human[i] instanceof Batter) {
						batterCatego(i);
					}
				}
			} else {
				System.out.println("해당 선수는 없습니다.");
				break;
			}
		}
	}

	public void update() {
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 선수 이름을 입력하세요: ");
		String name = sc.next();

		for (int i = 0; i < human.length; i++) {
			if (human != null) {
				String pn = human[i].getName();
				if (pn.equals(name)) {
					if (human[i] instanceof Pitcher) {
						System.out.println("수정할 항목을 고르세요");
						System.out.print("1.승리/ 2.패배/ 3.방어율   = ");
						int choice = sc.nextInt();
						switch (choice) {
						case 1:
							System.out.print("수정할 승리 횟수를 입력하세요: ");
							((Pitcher) human[i]).setWin(sc.nextInt());
							break;
						case 2:
							System.out.print("수정할 패배 횟수를 입력하세요: ");
							((Pitcher) human[i]).setLose(sc.nextInt());
							break;
						case 3:
							System.out.print("수정할 방어율을 입력하세요: ");
							((Pitcher) human[i]).setDefence(sc.nextDouble());
							break;
						}
						System.out.println("수정이 완료 되었습니다.");
						break;
					} else if (human[i] instanceof Batter) {
						System.out.println("수정할 항목을 고르세요");
						System.out.print("1.타수/ 2.안타/ 3.타율   = ");
						int choice = sc.nextInt();
						switch (choice) {
						case 1:
							System.out.print("수정할 타수를 입력하세요: ");
							((Batter) human[i]).setBatcount(sc.nextInt());
							break;
						case 2:
							System.out.print("수정할 안타 횟수를 입력하세요: ");
							((Batter) human[i]).setHit(sc.nextInt());
							break;
						case 3:
							System.out.print("수정할 타율을 입력하세요: ");
							((Batter) human[i]).setHitAvg(sc.nextDouble());
							break;
						}
						System.out.println("수정이 완료 되었습니다.");
						break;
					}
				}
			}
		}
	}

	public void allPrint() {

		for (int i = 0; i < human.length; i++) {
			if (human[i] != null) {
				System.out.print(human[i].getName() + "\t");
				System.out.print("나이: " + human[i].getAge() + "\t");
				System.out.print("등번호: " + human[i].getNumber() + "\t");
				System.out.println("신장: " + human[i].getHeight() + "\t");
				if (human[i] instanceof Pitcher) {
					pitcherCatego(i);
				} else if (human[i] instanceof Batter) {
					batterCatego(i);
				}
			} else {
				break;
			}
		}
	}
	
	public void dataSave() {
		
		
		// name-24-
		for (int i = 0; i < human.length; i++) {
			String str[i] = 
			fp.saveFile(str);
		}
		
	}
	public void pitcherInsert() {
		Scanner sc = new Scanner(System.in);

		System.out.print("선수의 등번호를 입력해주세요: ");
		human[count].setNumber(sc.nextInt());
		System.out.print("선수의 이름을 입력해주세요: ");
		human[count].setName(sc.next());
		System.out.print("선수의 나이를 입력해주세요: ");
		human[count].setAge(sc.nextInt());
		System.out.print("선수의 신장을 입력해주세요: ");
		human[count].setHeight(sc.nextDouble());
		System.out.print("투수 승리 횟수를 입력하세요: ");
		((Pitcher) human[count]).setWin(sc.nextInt());
		System.out.print("투수 패배 횟수를 입력하세요: ");
		((Pitcher) human[count]).setLose(sc.nextInt());
		System.out.print("투수 방어율을 입력하세요: ");
		((Pitcher) human[count]).setDefence(sc.nextDouble());
	}

	public void batterInsert() {
		Scanner sc = new Scanner(System.in);

		System.out.print("선수의 등번호를 입력해주세요: ");
		human[count].setNumber(sc.nextInt());
		System.out.print("선수의 이름을 입력해주세요: ");
		human[count].setName(sc.next());
		System.out.print("선수의 나이를 입력해주세요: ");
		human[count].setAge(sc.nextInt());
		System.out.print("선수의 신장을 입력해주세요: ");
		human[count].setHeight(sc.nextDouble());
		System.out.print("타자의 타수를 입력하세요: ");
		((Batter) human[count]).setBatcount(sc.nextInt());
		System.out.print("타자의 안타 수를 입력하세요: ");
		((Batter) human[count]).setHit(sc.nextInt());
		System.out.print("타자의 타율을 입력하세요: ");
		((Batter) human[count]).setHitAvg(sc.nextDouble());
	}

	public void pitcherCatego(int i) {
		System.out.println("승리횟수: " + ((Pitcher) human[i]).getWin());
		System.out.println("패배횟수: " + ((Pitcher) human[i]).getLose());
		System.out.println("방어율: " + ((Pitcher) human[i]).getDefence());
	}

	public void batterCatego(int i) {
		System.out.println("타수: " + ((Batter) human[i]).getBatcount());
		System.out.println("안타: " + ((Batter) human[i]).getHit());
		System.out.println("타율: " + ((Batter) human[i]).getHitAvg());
	}
}
```

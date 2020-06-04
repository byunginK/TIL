# MainClass

```java

package main;

import java.util.Scanner;

import dao.MemberDao;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		MemberDao md = new MemberDao();
		md.init();
		while(true) {
			System.out.println("BaseBall Member 관리");
			System.out.println("---------Manu---------");
			System.out.println("1. 선수 추가");
			System.out.println("2. 선수 삭제");
			System.out.println("3. 선수 검색");
			System.out.println("4. 선수 수정");
			System.out.println("5. 선수 모두보기");
			System.out.println("6. 데이터 저장");
			System.out.println("----------------------");
			System.out.print("메뉴를 선택해 주세요 >>");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					md.insert();
					break;
				case 2:
					md.delete();
					break;
				case 3:
					md.select();
					break;
				case 4:
					md.update();
					break;
				case 5:
					md.allPrint();
					break;
				case 6:
					md.dataSave();
					break;
			}
		}
	}

}
```

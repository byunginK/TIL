# Human Class
```java
package dto;
// DTO (Data Transfer Object)(VO : value Object)
public class Human {

	private int number; // 선수등록번호(sequence number)(중복안됨)
	private String name; // 선수 이름
	private int age; // 나이
	private double height; // 신장

	public Human() {
	}

	public Human(int number, String name, int age, double height) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.height = height;
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Human [number=" + number + ", name=" + name + ", age=" + age + ", height=" + height + "]";
	}

	
}
```
# Pitcher Class
```java
package dto;

public class Pitcher extends Human {

	private int win;
	private int lose;
	private double defence; // 0.0 ~10.0
	
	public Pitcher() {
		
	}
	
	
	public Pitcher(int number, String name, int age, double height,int win, int lose, double defence) {
		super(number,name,age,height);
		this.win = win;
		this.lose = lose;
		this.defence = defence;
	}


	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public double getDefence() {
		return defence;
	}
	public void setDefence(double defence) {
		this.defence = defence;
	}

}
```
# Batter Class
```java
package dto;

public class Batter extends Human {

	private int batcount; //타수
	private int hit;
	private double hitAvg; // 타율  0.0 ~ 1.0
	
	public Batter() {
		super();
		
	}
	
	
	
	public int getBatcount() {
		return batcount;
	}
	public void setBatcount(int batcount) {
		this.batcount = batcount;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public double getHitAvg() {
		return hitAvg;
	}
	public void setHitAvg(double hitAvg) {
		this.hitAvg = hitAvg;
	}
	@Override
	public String toString() {
		return "Batter [batcount=" + batcount + ", hit=" + hit + ", hitAvg=" + hitAvg + "]";
	}
	
	
	
}
```

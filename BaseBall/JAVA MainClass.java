package main;

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
		
		BaseBall ball = new BaseBall();
		
		ball.init();
		ball.loop();
		ball.resultPrint();
		
	}
}


package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.CardDeck;

public class Game {

	List<Integer> cardList = new ArrayList<Integer>();
	ArrayList<Integer> uCardSet = null;		// 유저 카드 set
	ArrayList<Integer> dCardSet = null; 	// 딜러 카드 set
	int userScore;
	int dealerScore;
	int replay = 1;
	
	public void play() {
		Scanner sc = new Scanner(System.in);
		System.out.println("==========Black Jack===========");
		while(replay == 1) {
			boolean b = true;
			uCardSet = new ArrayList<Integer>();
			dCardSet = new ArrayList<Integer>();
			// 카드 생성 및 카드 배분
			init();
			// 유저 카드 확인
			for (int i = 0; i < uCardSet.size(); i++) {
				System.out.println("유저카드:"+uCardSet.get(i));
			}
			// 딜러 카드 확인
			for (int i = 0; i < dCardSet.size(); i++) {
				System.out.println("딜러카드:"+ dCardSet.get(i));
			}
			// 점수 확인
			b = checkCard();
			
			if(b) {
			loop: while(b) {
					System.out.println("Hit (1) / Stay (2)");
					System.out.print(">>>");
					int choice = sc.nextInt();
					switch(choice) {
						case 1: // Hit
							// 카드 배분
							uCardSet.add(uCardDeck(cardList));
							if(dealerScore <= 16) {
								dCardSet.add(dCardDeck(cardList));
							}
							checkCard();
							
							for (int i = 0; i < uCardSet.size(); i++) {
								System.out.println("유저카드 :"+ uCardSet.get(i));
							}
							System.out.println("합"+ userScore);
							for (int i = 0; i < dCardSet.size(); i++) {
								System.out.println("유저카드 :"+ dCardSet.get(i));
							}
							System.out.println("합"+ dealerScore);
							break;
						case 2: // Stay
							// 카드 공개 및 점수 합산
							for (int i = 0; i < uCardSet.size(); i++) {
								System.out.println("유저 카드 :"+ uCardSet.get(i));
							}
							System.out.println("점수 합산: "+ userScore);
							for (int i = 0; i < dCardSet.size(); i++) {
								System.out.println("딜러카드:"+ dCardSet.get(i));
							}
							System.out.println("점수 합산: "+ dealerScore);
							if(userScore == dealerScore) {
								System.out.println("User Lose...");
							}
							else if(userScore > dealerScore) {
								System.out.println("User Win !!~");
							}
							else if(userScore < dealerScore) {
								System.out.println("User Lose....");
							}
							break loop;
					}
					if(userScore > 21) {
						System.out.println("User Brust!");
						System.out.println("User Lose...");
						break;
					}
					else if (dealerScore > 21) {
						System.out.println("dealer Brust!");
						System.out.println("User Win !!~~");
						break;
					}
					else {
						continue;
					}
				}
			}
			System.out.println("RePlay? Yes (1) / No (2)");
			System.out.print(">>> ");
			replay = sc.nextInt();
		}
		System.out.println("감사합니다.");
	}
	// 카드 생성
	
	public void init() {
		CardDeck cd = new CardDeck();
		cardList = cd.cardList();
		
		uCardSet.add(uCardDeck(cardList));
		uCardSet.add(uCardDeck(cardList));
		
		dCardSet.add(dCardDeck(cardList));
		dCardSet.add(dCardDeck(cardList));
		
	}
	// 카드 배분 (중복 숫자 제공 안됨)
	public int uCardDeck(List<Integer> cardList) {
		int index = (int)(Math.random()*20);
		int num = cardList.get(index)%13+1;
		int cardNum = changeScore(num,uCardSet);
		return cardNum;
	}
	public int dCardDeck(List<Integer> cardList) {
		int index = (int)(Math.random()*20 +20);
		int num = cardList.get(index)%13+1;
		int cardNum = changeScore(num,dCardSet);
		return cardNum;
	}
	
	// 처음 점수 확인
	public boolean checkCard() {
		userSum(uCardSet);
		dealerSum(dCardSet);
		
		if(userScore == 21 && dealerScore == 21) {
			System.out.println("무승부");
			return false;
		}
		else if(userScore == 21 && dealerScore != 21) {
			System.out.println("Black Jack!!");
			System.out.println("User Win~~!!");
			return false;
		}
		else if(dealerScore == 21 && userScore != 21) {
			System.out.println("Dealer : Black Jack");
			System.out.println("User Lose...");
			return false;
		}
		return true;
	}
	// A, J, Q, K 점수 변환
	public int changeScore(int cardNum, List<Integer> cardList) {
		
		if(cardNum == 1) {
			int sum = 0;
			for (int i = 0; i < cardList.size(); i++) {
				sum = sum + cardList.get(i);
			}
			if(sum <= 10) {
				return 11;
			}
			else {
				return 1;
			}
		}
		else if(cardNum == 11) {
			return 10;
		}
		else if(cardNum == 12) {
			return 10;
		}
		else if(cardNum == 13) {
			return 10;
		}
		return cardNum;
	}
	
	public void userSum(ArrayList<Integer> uCardSet) {
		int sum = 0;
		for (int i = 0; i < uCardSet.size(); i++) {
			sum = sum + uCardSet.get(i);
			userScore = sum;
		}	
	}
	
	public void dealerSum(ArrayList<Integer> dCardSet) {
		int sum = 0;
		for (int i = 0; i < dCardSet.size(); i++) {
			sum = sum + dCardSet.get(i);
			dealerScore = sum;
		}	
	}
}

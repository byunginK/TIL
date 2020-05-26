## 피보나치 수열
- #### 피보나치 수열이란?
0번째 항은 0, 1번째 항은 1, 그 외 항은 전번, 전전번 항의 합으로 표현

a b c 변수 준비
  a b c  -> 으로 반복 할때 마다 a = b , b = c 가 된다.
  
```
long a, b, c;
long arrNum[] = new long[30];
		
		
//초기값
a = 0;
b = 1;
		
arrNum[0] = a;
arrNum[1] = b;
```
우선 첫번째 값과 2번째 항의 값을 배열에 넣어준다.
```
int w = 0;
while(w <28) {
		
c= a+b;
arrNum[w + 2] = c;
// 값을 갱신
a = b;
b = c;
			
w++;
}
```
loop를 반복하면서 
a b c 
  a b c
  
의 형태로 값이 대입되면서 쭉 진행된다.

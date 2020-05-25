## Array (배열)


#### 정의 :   같은 자료형의 변수의 묶음
#### 관리 :   index number   0 부터 (n-1)개 까지

#### 형식:    자료형  배열명(=변수명)[] = new 자료형[배열 갯수];

 - int Array[] = new int[10];  10개의 변수 선언 (0 ~9)
 - int arr[] = {1, 2, 3};                             // 바로 변수를 초기화 하여 사용 할 수 있음
 
 ---
 
 ## 2차원 배열

```
int Array2[3][4] = {					         //첫 대괄호는 {}의 개수 , 두번째 대괄호는 {}안의 요소의 개수
    {1,2,3,4},
    {5,6,7,8},
    {9,10,11,12}
};		
```
 - 위의 배열은  int Array2[][] = new int[3][4]; 옆의 코드와 같이 구현이 가능하다.
 
 ```
for (int i = 0; i < Array2.length; i++) {			          //length는 3이다.
    for (int j = 0; j < Array2[0].length; j++) {		    //Array[0].length => 4이다.
      System.out.println("Array2["+ i + "]["+ j +"] = "+Array2[i][j]);
    }
}
```

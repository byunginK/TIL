# Heap 정렬
- 최악의 경우 o(nlogn)
- 추가배열 불필요
- (binary heap) 알고리즘 이용

## Heap 이란?
- complete binary tree 이면서 heap property 만족
```
complete binary tree (계층적인 관계) : 마지막 레벨을 제외하고 완전히 꽉 차있는 상태 (마지막 리프 노드가 몇개 없을 수 있음)
(Full binary tree : 모든 레벨의 노드들이 꽉 차있는 형태)
```
![image](https://user-images.githubusercontent.com/65350890/87673269-3b850900-c7af-11ea-9e18-2d83cd147b3d.png)

### heap 종류
1. max heap property : 부모는 자식보다 크거나 같다. (max heap)
2. min heap property : 부모는 자식보다 작거나 같다. (min heap) 
- 본질적인 동일한 자료구조이다(부호만 바꾸면 된다)

### 예시
![image](https://user-images.githubusercontent.com/65350890/87673475-97e82880-c7af-11ea-9725-0bf2ca013e77.png)
- a를 제외한 b와 c의 경우 표시된 트리와 다른 트리가 동일하지 않아 힙이 아니다.

### heap 의 표현
```
힙은 일차원 배열로 표현 가능 : A[1...n]
 	루트 노드 A[1]
 	A[i]의 부모 = A[i/2]
 	A[i]의 왼쪽 자신 = A[2i]
 	A[i]의 오른쪽 자식 = A[2i+1]
```  
![image](https://user-images.githubusercontent.com/65350890/87674409-ecd86e80-c7b0-11ea-9606-7be669b366f4.png)

### Max Heapify 연산
트리모양 : complete binary tree 에서 왼쪽, 오른쪽 (subtree)의 값과 루트노드의 값 확인<br>
만약 자식보다 값이 작으면 -> 자식노드 중 큰 값과 자리교체
![image](https://user-images.githubusercontent.com/65350890/87674673-45a80700-c7b1-11ea-9291-82e40ba61595.png)
```java
public static void heapify(int[]A,int len) { // Max heap을 만드는 함수
	for (int i = 1; i < len; i++) {
		int child = i;		//배열로 표기하기 위한 인덱스 child
		while(child>0) {
			int parent = (child-1)/2;	//배열로 표기하기 위한 부모노드
			if(A[child] > A[parent]) {
				int tmp = A[parent];
				A[parent] = A[child];
				A[child] = tmp;
			}
			child = parent;
		}
	}
}	
``` 

### heap 정렬
- 정렬을 하는 순서는 아래의 그림과 같다
1. heapify를 이용하여 힙 구축
2. 루트노드(최대값)과 맨 아래 리프노드(heap과 배열에서 맨 마지막)의 자리교체 (정렬)
4. 힙의 사이즈도 줄어든다( 내가 표현한 메소드는 for문의 조건에서 i의 길이를 -1 해주고 i-- 해주었다)
3. 다시 heapify
4. 반복

![image](https://user-images.githubusercontent.com/65350890/87874290-99119380-ca03-11ea-8ef1-bc737ccddb2e.png)

```java
	public static void main(String[] args) {
		int[]a = {2,8,6,1,10,15,3,12,11};
		
		heapify(a, a.length); // 우선 정렬할 데이터를 한번 Max heap으로 만들어준다.
		
		for (int i = a.length-1; i > 0; i--) { // 루트노드와 맨 아래 리프 노드와 자리르 바꿔준다(사실상 정렬) 그리고 맨아래로 간 최대값은 무시
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;			//최대값(루트노드)를 맨 아래 자식노드에 값 주고 for문 -- 하면서 이미 정렬된 맨 마지막은 다시 heap을 만들때 포함 x
			
			heapify(a, i);
		}
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]+" ");
		}
	}
```
- 결과 {1,2,3,6,8,10,11,12,15}

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

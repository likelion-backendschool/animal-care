## 팀 구성원, 개인 별 역할

---
+ 김우용 : API 구현(커뮤니티)
+ 김치훈 : API 구현(회원가입, 로그인)
+ 서창민 : API 구현(닥터 마이페이지)
+ 신상원 : API 구현(닥터 Q&A)
+ 이주원3 : API 구현(고객 마이페이지)


## 팀 내부 회의 진행 회차 및 일자

---
+ 6회차(2022.08.17) 디스코드 진행(모두 참여)
+ 7회차(2022.08.19) 디스코드 진행(모두 참여)


## 현재까지 개발 과정 요약 (최소 500자 이상)

---
0. Pull Requests
    + Private 레포지토리에서는 branch 규칙을 적용할려면 업그레이드를 해야함
    + 문서 작성의 경우 전부 확인 후 main ← doc 로 PR 날린 거 merge시키기( 사람)
    + commit 규칙 
    + 소문자로 하기
    + PR 보내는 법에 대해서 이야기
    + feature에서 개발 후 잘 되는 코드 작성
    

1. 이주원3님
    + 예약을 중심으로 구현
    + 내 정보 로그인과 연동해야함
    + 예약 날짜 달력 나왔을때 나오게 하는 것 미구현
    + 도메인 별로 나눠서 작성함
    + JPQL VS Spring Data JPA(Repository) 비교 해보기 → Spring Data JPA(Repository)로 바꿈
    + 프론트 작성함
    + 진료내역(진단서) → 변경 진단서 개념 제거
    + 각자 코드 합친 것 리팩토링


2. 신상원님
    + 가져다 쓸려고 만들어 놨음 → CRUD를 만듦
    + TEST 코드를 작성함
    + 레스트 컨트롤러 X → 타임 리프 방식으로 수정하기
    + TDD 이용해서 코드 작성함


3. 서창민님
    + 도메인 주도로 짬
    + mypage 주도로 퍼지는게 싫어서 DB 접근 쪽과 WEB 부분을 나눴음
    + medical record -> diagnosis 


4. 김우용님
    + restAPI → 수정을 해야함
    + 페이징 처리 못함, 타임리프 들어가는 부분은 전체적인 틀은 구현함
    + 위의 부분에 대한 리팩토링 필요


5. 김치훈님
    + 로그인 기능 개발
    + 팀원들 PR하는 것에 도움
    + PR 어떻게 할 것인지 방법구안   
    + 각자 코드 합친 것 리팩토링


## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---
### 논의 사항
1. 패키지명
    + 웹사이트 주소를 반대로 기재한 모양으로 패키지 이름을 부여함
    + 명칭 소문자 사용
    + 너무 길어지거나 불가피하게 나눠야 될 경우 . 구분


2. 패키지 구조에 대한 것 테이블 마다 나눈 것 

![image-20220817-135445](https://user-images.githubusercontent.com/39127771/185784137-d31b100f-1ab8-45f7-b2fd-250488ecfd4e.png)


3. 브랜치명 앞에 #1 이런식으로 붙이는 게 좋을 거 같음
    + 안 붙이는 걸로 하기로 함


4. DTO를 어디서 바꿀 것인가? → 질문 하기로 함
    + DTO → Entity
      + 서비스단에서 하면 로직이 안 맞음
      + 종속성이 큼
    + Entity → DTO
 

5. 유효성 검사(request 값이 잘못된 경우)
    + DTO쪽에서 컨트롤러에서 검사


6. 에러 검사(중복 처리 ETC….)
    + 서비스단에서 처리 해보기

7. mySQL root, password 명으로
    + DB명 : animal_care


8. 테스트 코드에 대해서
    + 테스트 코드 작성하면 코드를 합칠때 좋음


9. DB 수정사항
    + hospital 엔터티: phone_num 추가
    + medical_appointment 엔터티: medical_appointment_date 추가
    + animal 엔터티: 건강상태를 넣게 하는게 괜찮은건가 -> 빼기로 함
    + 물품 엔터티, 약 배달 엔터티 없음 -> 나중에 만들기
    + 날짜는 LocalDateTime으로 통일

10. 회원 마이페이지, 닥터 마이페이지 수정사항
    + 명칭 바꿈: 회원 마이페이지에서 진료내역(진단서) → 병원방문내역
    + 닥터마이페이지에 진단서 넣어야함

<img width="439" alt="스크린샷 2022-08-21 오후 6 36 05" src="https://user-images.githubusercontent.com/39127771/185784917-35f7510b-791b-47c4-bc10-4f08729c7a24.png">
   
    


### 질문 사항 정리
1. DTO를 어디서 바꿀 것인가? → 질문 하기로 함
    + DTO → Entity
      + 서비스단에서 하면 로직이 안 맞음
      + 종속성이 큼
    + Entity → DTO
   

## WBS 변동사항

---
   + 리팩토링 진행했음
   + 백엔드 + 타임리프 써서 자기가 구현한 부분 만들어오기



## 개발 결과물 공유

---
   + Github Repository URL : https://github.com/likelion-backendschool/animal-care
   + 8/19 프로젝트 회의(수정사항): https://www.notion.so/8-19-3f3c394be9964283b3ec3b4ef52a241e
   + wbs: https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0

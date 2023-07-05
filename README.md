<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
  </a>

  <h1 align="center">:fork_and_knife: 젓가락질 Project</h1>

  <p align="center">
    KAIST 2023 여름 몰입캠프 1주차 과제 (2분반 김창완, 엄창용)  
  <br />
  <br />
  <br />
  </p>
</div>

## :pushpin: 몰입캠프 1주차 과제 (공통)

1. 공통과제 I (6/29 ~ 7/5) - 탭 구조를 활용한 안드로이드 앱 제작
목적:
서로 함께 공통의 과제를 함으로써, 개발에 빠르게 익숙해지기
* 결과물: 세 개의 탭이 존재하는 안드로이드 앱
  * 탭 1: 나의 연락처 구축. 휴대폰의 연락처 데이터를 활용하거나, JSON 형식을 이용해서 임의의 연락처 데이터를 구축. (추천: ListView나 RecyclerView 등을 이용해서 데이터 보여 주기)
  * 탭 2: 나만의 이미지 갤러리 구축. 이미지는 대략 20개 정도.
  * 탭 3: 자유 주제   
* 첫날 해야 하는 일: Android Studio 설치하기, Hello World 띄워 보기   
- 과제 중 추가로 해야 할 일 - 협업을 위한 도구 사용
  - Git에 대한 이해도 높이기
  - github.com에서 퍼블릭 프로젝트로 운영하는 것을 권고 (실제로 현업에서 많이 사용되기에)   

## :pushpin: 개발 환경
IDE: Android Studio  
Language: java    
minSdk: 24  
targetSdk: 33  
Hardware: Galaxy S7 (Android 8.0.0)

## :pushpin: 역할 분담

- 공통: 탭 레이아웃 작성  
- 김창완: 탭1, 탭3  
- 엄창용: 탭2, git 사용법 공부  


## :pushpin: 앱 설명

### :zero: 권한 허용 팝업

<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/645f6306-5376-4efe-83e2-0e3dd0b3b654" width="30%" height="30%">
  
앱을 실행하기 전, 연락처 권한을 허용 받기 위한 팝업 제시.
사용자가 거부할 경우 앱 실행을 종료한다.

### :one: 탭1 : 연락처
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/d4a79ed6-235b-4489-9c98-d89427326bc6" width="30%" height="30%">
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/627a9ebd-4466-46b7-92cc-f774224304ed" width="30%" height="30%">
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/3dd97a7f-64b8-4402-b343-9f9b019ed173" width="30%" height="30%">   

1. 휴대폰에 저장되어 있는 연락처를 List 형태로 저장해서 화면에 보여줌. (ListView 사용)
2. 짧게 누를 시, 해당 연락처로 전화를 바로 걸 수 있는 다이얼 화면으로 전환.  
3. 길게 누를 시, 해당 연락처를 삭제. (위 사진에서는 Jamie 연락처를 삭제함.)

앱을 통한 연락처 수정이 있을 때마다 ListView를 새로고침하여 실시간으로 변경된 정보 확인 가능.  

<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/792c9c0c-5707-48c0-bc87-4fad1e1821f7" width="30%" height="30%">   

또한, 연락처 수정으로 인해 연락처 목록 순서가 바뀌더라도 알맞게 전화가 걸리는 모습을 확인 가능.

<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/32c0abd6-4011-4135-ac8f-cf40c10e2529" width="30%" height="30%">  
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/5ac768f4-c44f-4f06-b545-47adc5a2cc13" width="30%" height="30%">  
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/7dbda3ff-571d-4509-8e14-86ff89ba94b7" width="30%" height="30%">  

상단의 입력 칸에 이름과 전화번호를 쓰고 "연락처 추가" 버튼을 누르면 해당 정보로 연락처 추가.
전화번호를 적는 EditText는 편의를 위해 숫자만 입력하더라도 010-xxxx-xxxx 형식으로 알아서 맞춰지게끔 구현함.

- 연락처 프로필 사진, 메모, 이메일 등 더 다양한 기능을 구현해야 함.
- EditText와 Button의 width를 pixel 단위가 아닌 weight 비율로 구현해야 함. 

### :two: 탭2 : 갤러리

<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/34a210dd-fdcc-4369-ac27-932703f38278" width="30%" height="30%"> 
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/8621ee9b-3fac-46b0-9942-ace50f8579a7" width="30%" height="30%"> 
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/949e1712-6d28-4271-b317-97f6db09179b" width="30%" height="30%"> 
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/98b2cf47-8bde-4a96-ba77-34b64bb39321" width="30%" height="30%"> 
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/c2b836d9-b46a-4e08-9d35-09ecd3c1c53b" width="30%" height="30%"> 
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/45eb5ec4-40e3-4797-b8e5-7aff0db94471" width="30%" height="30%"> 

1. "사진" 탭을 누르면 빈 화면으로 시작.
2. "불러오기" 버튼을 누르면 휴대폰 내부저장소에서 사진 파일을 고를 수 있음. 
3. "포토" 버튼을 누르면 휴대폰 갤러리에서 사진을 선택할 수 있음.
4. 선택된 사진은 갤러리 형태로 화면에 나타남. (GridView 사용)
5. 메인 화면에서 원하는 사진을 한 번 더 누르면, 해당 사진 탭이 나옴.
6. "삭제" 버튼을 누르고 삭제를 원하는 사진을 누르면 해당 사진은 메인 화면에서 없어짐.

<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/eda492ae-b154-4752-a908-c4bb82a8f976" width="30%" height="30%"> 

7. 사진 추가는 원하는 만큼 가능함.
 
- 앱을 재실행했을 때 갤러리가 초기화되는 문제 해결해야 함. -> SQLite로 구현 시도 중  

### :three: 탭3 : 국어사전
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/3dfbfcd0-49ca-491f-8004-0c70f150f2ee" width="30%" height="30%"> 
<img src = "https://github.com/changwann/madcamp_week1/assets/122224659/21f38882-498d-4a33-b99d-944efc4b103c" width="30%" height="30%">  

네이버 국어사전을 연결해 사용자가 원하는 단어 검색을 할 수 있게끔 구현. (WebView 사용)  
단, 기기가 인터넷에 연결되어 있어야 함.

- 탭1, 탭2에 시간을 뺏겨 탭3를 다소 단순한 주제로 잡음. WebView는 기능 구현이 간단해서 탭3에 시간을 쏟지 못한 것이 아쉬움.

## :pushpin: 느낀 점

김창완: 안드로이드 스튜디오와 java 언어를 거의 처음 다뤄봐서 우여곡절이 많았지만, 결국 과제를 해내긴 해서 뿌듯했다.  
이해보단 우선 구현을 목표로 개발을 했는데, 이제 전체적인 느낌을 알게 됐으니 나중에는 원리 위주로 코드를 이해하고 싶다.
그리고 기능을 구현하기도 벅차 디자인을 소홀히 한 부분이 많이 아쉽다.

엄창용:

## :pushpin: 팀원 연락처

김창완 GIST - changwan@gm.gist.ac.kr  
엄창용 KAIST - um8389@kaist.ac.kr

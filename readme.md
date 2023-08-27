# TREVELO
위치 기반 여행지 추천 및 숙소를 등록, 예약할 수 있는 종합 여행 플랫폼

## :airplane: 프로젝트 소개

- TREVELO는 관광지 검색, 여행 경로 설정, 관광지 추천, 숙소 거래 등 여행 종합 웹 플랫폼 입니다.
- 사용자는 본인 위치 기반의 관광지를 추천받을 수 있고, 여행 경로를 날짜별로 설정하여 관리할 수 있습니다.
- 사용자는 숙소를 자유롭게 등록하고, 또 숙소를 제공하는 거래를 할 수 있습니다.
- 플랫폼 내에서의 거래는 마일리지로 이루어지며, 마일리지는 토스페이먼츠 결제를 통해 충전할 수 있습니다.

## :two_men_holding_hands: 참여 인원

|                            Backend + Frontend                            |                          Backend + Frontend                           |       
|:------------------------------------------------------------------------:|:---------------------------------------------------------------------:| 
| <img src="https://github.com/Shin-sangwon.png" width="120" height="120"> | <img src="https://github.com/Lee-do-ha.png" width="120" height="120"> | 
|                  [신상원](https://github.com/Shin-sangwon)                  |                  [이도하](https://github.com/Lee-do-ha)                  |

### 신상원 (팀장)
- Spring Security + JWT
- 회원 관리 + 회원 관리
- 숙소 등록
- 숙소 예약
- 숙소 리뷰 + 평점
- 마일리지 결제

### 이도하
- 게시판 + 댓글
- 위치 기반 여행지 추천
- 관심 여행지 저장
- 여행 경로 설정

## :sailboat: 기능 소개

### 사용자는 접속 위치 기반의 관광지를 추천 받을 수 있습니다
![여행지_추천](https://github.com/Shin-sangwon/TREVELO/assets/101318750/97c6fdbe-66f6-42ce-b6dc-e21b6637e369)

### 여행 정보를 게시판을 통해 공유할 수 있습니다
![게시판](https://github.com/Shin-sangwon/TREVELO/assets/101318750/b12028f6-83c1-4aca-bd38-357968c57eef)

### 여러 주제로 관광지를 검색할 수 있습니다
![관광지, 관심관광지](https://github.com/Shin-sangwon/TREVELO/assets/101318750/977c0331-f035-46bd-bf35-ace0b04a58a8)

### 사용자는 관심 관광지를 등록하여 볼 수 있습니다
![나의 관광지](https://github.com/Shin-sangwon/TREVELO/assets/101318750/2a310c2b-2b2a-44bb-901d-99c7d813384e)

### 사용자는 여행 계획을 일자별로 설정하고 조회할 수 있습니다
![여행 계획](https://github.com/Shin-sangwon/TREVELO/assets/101318750/b15092c0-5c89-4ea1-a540-0dbfc089b9a3)

### 사용자는 숙소를 등록하여 거래할 수 있습니다
![숙소 등록](https://github.com/Shin-sangwon/TREVELO/assets/101318750/13fca920-d895-4ae2-b741-cccf5e9aaccd)

### 사용자는 마음에 드는 숙소를 예약할 수 있습니다
![숙소 예약](https://github.com/Shin-sangwon/TREVELO/assets/101318750/3f1e6e20-ea46-4423-922e-f3b94379f17d)

### 이용했던 숙소에 대해서 리뷰를 작성할 수 있습니다
![리뷰 작성](https://github.com/Shin-sangwon/TREVELO/assets/101318750/03ee5343-3442-47ac-a251-e2b515e6e0c6)

### 숙소를 예약하기 위해 토스페이를 이용하여 마일리지를 충전할 수 있습니다
![토스 결제](https://github.com/Shin-sangwon/TREVELO/assets/101318750/12de18c6-e016-457e-b61a-4bfde83917dc)


## :sparkler: 기술 스택
- Springboot
- Spring Security
- JWT
- MyBatis
- MySql
- Vue
- Vuex
- AWS S3

## :books: ERD
![tripdb_erd](https://github.com/Shin-sangwon/TREVELO/assets/101318750/40ace70f-9bf6-41ad-bb31-c054d46776dc)

## :white_check_mark: 프로젝트 회고

### 프론트엔드와의 첫 만남

이번 프로젝트에서는 백엔드 뿐만 아니라 프론트엔드도 처음으로 다뤄봤습니다. Vue.js와 VUEX를 사용하여 view를 띄우는 방법, 백엔드와의 RESTful API 통신 방법 등을 배울 수 있었습니다. 이 경험을 바탕으로, 앞으로의 프로젝트에서 프론트엔드 개발자와 더 원활하게 소통할 방법을 배웠습니다.

### Mybatis, Query

자주 사용하던 JPA 대신 Mybatis를 사용하여 프로젝트를 진행했습니다. SQL 쿼리를 직접 작성해야 하는 점이 생각보다 쉽지 않았습니다. 특히 JOIN, GROUP BY 등의 복잡한 쿼리 작성이 어려웠습니다. 이를 통해 ORM에 너무 의존하지 않도록 해야겠다고 느꼈습니다.

### 예약과 결제의 예외 처리

예약과 결제 관련 기능을 구현하면서, 다양한 예외 상황 (예: 중복 예약, 결제 실패, 본인 숙소 예약 등)을 고려하게 되었습니다. 이런 부분들을 보완하면서 테스트 코드의 중요성을 새삼 느꼈습니다. 방어적 코딩을 좋아하기 때문에 이 부분은 특히 재미있었지만, 이런 도메인에서는 지금보다 더욱 엄격한 예외 처리가 필요하다고 느꼈습니다.


## :monorail: Front-end repository
[TREVELO-Front](https://github.com/TREVELO/frontend)
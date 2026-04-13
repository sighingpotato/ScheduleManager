🗓️ 일정 관리 앱 (Schedule Manager)

JPA와 Spring Boot를 활용한 3-Layer Architecture 기반의 일정 관리 앱

1. 📂 프로젝트 개요

- 개발 환경: Java 17, Spring Boot

- 데이터베이스: MySQL

- 주요 기능:
  - 일정의 생성, 조회(전체/단건), 수정, 삭제 (CRUD)

  - 일정별 댓글 작성 (최대 10개 제한) 및 삭제 시 연쇄 삭제(Cascade)

  - 생성/수정일 자동 관리

  - DTO를 통한 검증

2. 🗺️ ERD (Entity Relationship Diagram)

Schedule: 일정 정보를 담는 테이블 (제목, 내용, 작성자, 비밀번호 등)

Comment: 일정에 종속되는 댓글 테이블 (내용, 작성자, 비밀번호, 일정 ID 등)

관계: Schedule(1) : Comment(N) (일정 하나에 여러 댓글이 존재)


3. 📝 API 명세서

    📅 일정 관련 API (/api/schedules)

    [README.md](src/main/java/com/example/schedulemanager/README.md)


4. 🛠️ 핵심 로직

3-Layer Architecture

Controller: 외부 요청을 받고 응답을 반환하는 계층. ResponseEntity를 통해 HTTP 상태 코드를 제어.

Service: 비즈니스 로직(비밀번호 검증, 댓글 개수 제한 등)을 처리.

Repository: JPA를 사용하여 DB에 직접 접근.

비밀번호 검증 및 보안
    
응답 객체인 ResponseDto 생성 시 비밀번호 필드를 제외하여 클라이언트에게 민감 정보가 노출되지 않도록 설계했다.
    
수정/삭제 시에는 서버 내부에서 checkPassword 메서드를 통해 입력값과 DB 저장값을 비교했다.

데이터 무결성 및 검증 (Lv 7)
    
@NotBlank, @Size 어노테이션을 DTO에 적용하여 유효하지 않은 데이터가 서비스 로직까지 도달하지 못하도록 차단했다.
    
제목: 최대 30자
    
일정 내용: 최대 200자
    
댓글 내용: 최대 100자


5. 💡 배운 점 및 회고

PUT vs PATCH의 차이점: 일정 수정 기능을 구현할 때 둘 중 무엇을 써야 할지 고민했다.

- **`PUT`**: 자원의 **전체**를 교체할 때 사용한다. (만약 제목, 내용, 작성자가 있는데 제목만 보내면 나머지는 null로 덮어씌워질 위험이 있다.)
- **`PATCH`**: 자원의 **일부**만 수정할 때 사용한다.
- **적용**: 이번 과제에서는 일정의 '제목'과 '작성자명'만 일부 수정하는 조건이 있었기 때문에 `@PatchMapping`을 사용하는 것이 RESTful한 설계에 더 적합하다는 것을 배웠다.

양방향 매핑: mappedBy를 통해 연관관계의 주인을 설정하고, 단건 조회 시 댓글 리스트를 함께 불러오는 과정을 통해 객체 지향적 설계의 중요성을 알 수 있었다.
 
연관 관계 설정: 일정 삭제 시 해당 일정에 달린 댓글도 함께 삭제되도록 cascade = CascadeType.REMOVE를 설정하여 데이터 고립을 방지할 수 있다는 것을 알 수 있었다.
- **`@OneToMany`**: "나(일정) 하나에 여러 개(Many)의 댓글이 달릴 수 있어"라는 뜻이다.
- **`mappedBy = "schedule"`**: 이게 아주 중요합니다! "댓글(Comment) 엔티티에 있는 `schedule`이라는 필드가 나랑 너를 연결해 주는 주인이야"라고 알려주는 거다. (`Comment`엔티티 안에`private Schedule schedule;`이라고 적어주신 것과 짝꿍이다.)
- `cascade = CascadeType.REMOVE`를 딱 한 문장으로 요약하면 "부모(일정)가 삭제될 때, 자식(댓글)들도 뿔뿔이 흩어지게 두지 말고 다 같이 싹 지워주세요!" 라는 뜻이다.

API 명세서의 중요성: 개발 전 명세서를 먼저 작성함으로써 전체적인 데이터 흐름을 미리 파악할 수 있었고, 컨트롤러 구현 시 경로 헷갈림을 줄이는데 도움이 되었다.

JPA Auditing을 이용하면 스프링이 알아서 시간을 찍어줄 수 있다는 것을 알았다.






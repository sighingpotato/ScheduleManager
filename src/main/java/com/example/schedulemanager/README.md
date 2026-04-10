# 📅 일정 관리 애플리케이션 (Schedule Manager)

## 1. API Specification
RESTful API 명세서입니다. 모든 응답(Response)에서 보안을 위해 password는 제외됩니다.

### 1.1. 일정 생성

- URL: /api/schedules

- Method: POST

- Description: 새로운 일정을 생성합니다.

Request Header
```
Content-Type: application/json
```

| 이름           | 데이터타입            | 설명        |
|--------------|------------------|-----------|
| Content-Type | application/json | 요청 데이터 형식 |

Request Body

```JSON
{
  "title": "스터디",
  "content": "팀 스터디 진행",
  "author": "홍길동",
  "password": "1234"
}
```

| 이름        | 데이터타입  | 설명    |
|-----------|--------|-------|
| title     | String | 일정 제목 |
| content   | String | 일정 내용 |
| author    | String | 작성자명  |
| password  | String | 비밀번호  |

Response Header
```
Content-Type: application/json
```

| 이름           | 데이터타입            | 설명        |
|--------------|------------------|-----------|
| Content-Type | application/json | 응답 데이터 형식 |


Response Body 

✅ 201 Created

```JSON
{
  "id": 1,
  "title": "스터디",
  "content": "팀 스터디 진행",
  "author": "홍길동",
  "createdAt": "2026-04-10T14:00:00",
  "updatedAt": "2026-04-10T14:00:00"
}
```

| 이름        | 데이터타입    | 설명    |
|-----------|----------|-------|
| id        | Long     | 일정 id |
| title     | String   | 일정 제목 |
| content   | String   | 일정 내용 |
| author    | String   | 작성자명  |
| createdAt | DateTime | 생성일   |
| updatedAt | DateTime | 수정일   |

### 1.2. 전체 일정 조회

- URL: /api/schedules

- Method: GET

- Description: 등록된 전체 일정을 조회. (수정일 기준 내림차순 정렬)

Parameter & Querystring

```Plaintext
Query Parameter: author (Optional) - 작성자명으로 필터링
```

| 이름     | 데이터타입  | 설명               |
|--------|--------|------------------|
| author | String | 작성자명 (없으면 전체 조회) |


Response Header

```Plaintext
Content-type: application/json
```

| 이름           | 데이터타입            | 설명        |
|--------------|------------------|-----------|
| Content-Type | application/json | 응답 데이터 형식 |

Response Body

✅ (200 OK)

```JSON
[
  {
    "id": 2,
    "title": "두 번째 일정",
    "content": "내용입니다.",
    "author": "홍길동",
    "createdAt": "2026-04-09T10:00:00",
    "updatedAt": "2026-04-10T15:30:00"
  },
  {
    "id": 1,
    "title": "스터디",
    "content": "팀 스터디 진행",
    "author": "홍길동",
    "createdAt": "2026-04-10T14:00:00",
    "updatedAt": "2026-04-10T14:00:00"
  }
]
```

| 이름        | 데이터타입    | 설명    |
|-----------|----------|-------|
| id        | Long     | 일정 id |
| title     | String   | 일정 제목 |
| content   | String   | 일정 내용 |
| author    | String   | 작성자명  |
| createdAt | DateTime | 생성일   |
| updatedAt | DateTime | 수정일   |


### 1.3. 선택 일정 조회

- URL: /api/schedules/{id}

- Method: GET

- Description: 고유 식별자(ID)를 통해 단건 일정을 조회합니다.

Parameter & Querystring

```Plaintext
Path Variable: id
```

| 이름 | 데이터타입 | 설명   |
|----|-------|------|
| id | Long  | 일정 id |

Response Header
```Plaintext
Content-Type: application/json
```

| 이름           | 데이터타입            | 설명        |
|--------------|------------------|-----------|
| Content-Type | application/json | 응답 데이터 형식 |


Response Body

✅ 200 OK

```JSON
{
  "id": 1,
  "title": "스터디",
  "content": "팀 스터디 진행",
  "author": "홍길동",
  "createdAt": "2026-04-10T14:00:00",
  "updatedAt": "2026-04-10T14:00:00"
}
```

| 이름        | 데이터타입    | 설명    |
|-----------|----------|-------|
| id        | Long     | 일정 id |
| title     | String   | 일정 제목 |
| content   | String   | 일정 내용 |
| author    | String   | 작성자명  |
| createdAt | DateTime | 생성일   |
| updatedAt | DateTime | 수정일   |

### 1.4. 선택 일정 수정

- URL: /api/schedules/{id}

- Method: PATCH

- Description: 일정을 수정합니다. (제목, 작성자명만 수정 가능하며 비밀번호 검증이 필요합니다.)

Parameter & Querystring

```Plaintext
Path Variable: id
```

| 이름 | 데이터타입 | 설명        |
|----|-------|-----------|
| id | Long  | 수정할 일정 id |

Request Header

```Plaintext
Content-Type: application/json
```

| 이름           | 데이터타입            | 설명        |
|--------------|------------------|-----------|
| Content-Type | application/json | 요청 데이터 형식 |

Request Body

```JSON
{
  "title": "스터디 (수정됨)",
  "author": "홍길동 (수정됨)",
  "password": "1234"
}
```

| 이름       | 데이터타입  | 설명        |
|----------|--------|-----------|
| title    | String | 수정할 일정 제목 |
| author   | String | 수정할 작성자명  |
| password | String | 검증용 비밀번호  |

Response Body

✅ 200 OK

```JSON
{
  "id": 1,
  "title": "스터디 (수정됨)",
  "content": "팀 스터디 진행",
  "author": "홍길동 (수정됨)",
  "createdAt": "2026-04-10T14:00:00",
  "updatedAt": "2026-04-10T18:00:00"
}
```

| 이름        | 데이터타입    | 설명    |
|-----------|----------|-------|
| id        | Long     | 일정 id |
| title     | String   | 일정 제목 |
| content   | String   | 일정 내용 |
| author    | String   | 작성자명  |
| createdAt | DateTime | 생성일   |
| updatedAt | DateTime | 수정일   |

### 1.5. 선택 일정 삭제

- URL: /api/schedules/{id}

- Method: DELETE

- Description: 일정을 삭제합니다. (비밀번호 검증이 필요합니다.)

Parameter & Querystring

```
Path Variable: id
```

| 이름 | 데이터타입 | 설명        |
|----|-------|-----------|
| id | Long  | 삭제할 일정 id |

Request Header
```
Content-Type: application/json
```

| 이름           | 데이터타입            | 설명        |
|--------------|------------------|-----------|
| Content-Type | application/json | 요청 데이터 형식 |

Request Body

```JSON
{
  "password": "1234"
}
```

| 이름       | 데이터타입  | 설명       |
|----------|--------|----------|
| password | String | 검증용 비밀번호 |

Response Body

✅ 204 No Content

```Plaintext
(Body 없음)
```

## 2. ERD (Entity Relationship Diagram)

데이터베이스 테이블 구조입니다.

### Table: `schedule`
| Column      | Type         |  Key  | Constraint | Description |
|:------------|:-------------|:-----:|:----------|:------------|
| `id`        | BIGINT       |  PK   |           | 일정 고유 식별자   |
| `title`     | VARCHAR(100) |       |           | 일정 제목       |
| `content`   | VARCHAR(256) |       |           | 일정 내용       |
| `author`    | VARCHAR(50)  |       |           | 작성자명        |
| `password`  | VARCHAR(100) |       |           | 비밀번호 (검증용)  |
| `createdAt` | DATETIME     |       |           | 작성일         |
| `updatedAt` | DATETIME     |       |           | 수정일         |


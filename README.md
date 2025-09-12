# OpenAI 가격 예측 API

이 프로젝트는 Spring Boot를 기반으로, OpenAI의 GPT 모델을 사용하여 상품의 미래 가격을 예측하는 RESTful API입니다.

##  주요 기능

- **미래 가격 예측**: 상품명, 현재 가격, 최근 가격 추이 데이터를 기반으로 향후 4주간의 가격을 예측합니다.
- **AI 기반 예측**: OpenAI의 강력한 언어 모델을 활용하여 예측의 정확도와 상세한 분석을 제공합니다.
- **RESTful API**: 표준 REST API 구조로 설계되어 외부 시스템에서 쉽게 연동할 수 있습니다.
- **입력 검증**: Bean Validation을 통한 강력한 입력 데이터 검증
- **에러 처리**: 전역 예외 처리기를 통한 표준화된 에러 응답
- **HTTP 상태 코드**: 표준 HTTP 상태 코드 적용 (201, 400, 500)
- **가격 추이 차트**: Chart.js를 활용한 시각적 가격 예측 차트
- **API 문서 자동화**: `Springdoc OpenAPI`(Swagger UI)를 통해 API 명세를 자동으로 생성하고, 브라우저에서 직접 API를 테스트할 수 있습니다.

## 사용 기술

- **Backend**: Java 21, Spring Boot 3.5.5
- **Build Tool**: Gradle
- **AI**: OpenAI-Java SDK 3.1.2
- **Validation**: Spring Boot Starter Validation (Jakarta Validation)
- **API Documentation**: Springdoc OpenAPI (Swagger UI)
- **Frontend**: Chart.js, HTML5, JavaScript ES6+
- **Utilities**: Lombok, Jackson

## 설치 및 실행 방법

### 1. 요구사항

- Java 21
- Gradle

### 2. 프로젝트 클론

```bash
git clone <저장소_URL>
cd <프로젝트_디렉토리>
```

### 3. 환경 변수 설정

이 프로젝트는 OpenAI API 키를 필요로 합니다. API 키는 **환경 변수**를 통해 설정해야 합니다.

- 변수명: `OPENAI_API_KEY`
- 값: 발급받은 OpenAI API 키

`application.properties` 파일이 `${OPENAI_API_KEY}` 구문을 통해 이 환경 변수를 참조합니다.

### 4. 애플리케이션 실행

아래 명령어를 사용하여 애플리케이션을 실행합니다.

```bash
./gradlew bootRun
```

애플리케이션이 성공적으로 시작되면 `http://localhost:7777`에서 실행됩니다.

### 5. 웹 인터페이스 접속

- **가격 예측 차트**: [http://localhost:7777/chart_practice.html](http://localhost:7777/chart_practice.html)
- **API 문서**: [http://localhost:7777/swagger-ui.html](http://localhost:7777/swagger-ui.html)

## API 명세

Swagger UI를 통해 더 자세한 API 명세를 확인하고 직접 테스트할 수 있습니다.

- **Swagger UI 주소**: [http://localhost:7777/swagger-ui.html](http://localhost:7777/swagger-ui.html)

---

### 가격 예측 API

- **Endpoint**: `POST /api/predictions`
- **설명**: 상품의 미래 가격 예측을 생성합니다.
- **요청 헤더**: 
  - `Content-Type: application/json`

- **요청 본문 (Request Body)**:
  ```json
  {
    "productName": "string",
    "currentPrice": 0,
    "trendData": "string"
  }
  ```

- **필드 설명**:
  - `productName` (String, 필수): 예측할 상품명 (빈 문자열 불가)
  - `currentPrice` (Number, 필수): 현재 가격 (0보다 큰 숫자)
  - `trendData` (String, 필수): 과거 가격 데이터 (예: "10000, 11000, 12000")

- **요청 예시**:
  ```bash
  curl -X POST http://localhost:7777/api/predictions \
    -H "Content-Type: application/json" \
    -d '{
      "productName": "iPhone 16",
      "currentPrice": 1500000,
      "trendData": "1400000, 1450000, 1480000, 1500000"
    }'
  ```

- **성공 응답 (201 Created)**:
  ```json
  {
    "week1": 1520000,
    "week2": 1550000,
    "week3": 1530000,
    "week4": 1560000,
    "explanation": "최근 가격 상승 추세를 반영하여 향후 4주간 점진적 상승이 예상됩니다."
  }
  ```

- **에러 응답 예시**:
  
  **400 Bad Request (입력 검증 실패)**:
  ```json
  {
    "errorCode": "INVALID_INPUT",
    "message": "상품명은 필수입니다.",
    "timestamp": "2025-09-12T07:30:00"
  }
  ```

  **500 Internal Server Error (서버 오류)**:
  ```json
  {
    "errorCode": "INTERNAL_ERROR", 
    "message": "예측 생성 중 오류가 발생했습니다.",
    "timestamp": "2025-09-12T07:30:00"
  }

# OpenAI 가격 예측 API

이 프로젝트는 Spring Boot를 기반으로, OpenAI의 GPT 모델을 사용하여 상품의 미래 가격을 예측하는 RESTful API입니다.

##  주요 기능

- **미래 가격 예측**: 상품명, 현재 가격, 최근 가격 추이 데이터를 기반으로 향후 4주간의 가격을 예측합니다.
- **AI 기반 예측**: OpenAI의 강력한 언어 모델을 활용하여 예측의 정확도와 상세한 분석을 제공합니다.
- **RESTful API**: 외부 시스템에서 쉽게 연동할 수 있도록 표준 REST API 엔드포인트를 제공합니다.
- **API 문서 자동화**: `Springdoc OpenAPI`(Swagger UI)를 통해 API 명세를 자동으로 생성하고, 브라우저에서 직접 API를 테스트할 수 있습니다.

## 사용 기술

- **Backend**: Java 21, Spring Boot 3.5.5
- **Build Tool**: Gradle
- **AI**: OpenAI-Java SDK
- **API Documentation**: Springdoc OpenAPI (Swagger UI)
- **Utilities**: Lombok

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

## API 명세

Swagger UI를 통해 더 자세한 API 명세를 확인하고 직접 테스트할 수 있습니다.

- **Swagger UI 주소**: [http://localhost:7777/swagger-ui.html](http://localhost:7777/swagger-ui.html)

---

### 가격 예측 API

- **Endpoint**: `GET /api/predict`
- **설명**: 상품의 미래 가격을 예측합니다.
- **쿼리 파라미터**:
  - `productName` (String, 필수): 예측할 상품의 이름
  - `currentPrice` (double, 필수): 상품의 현재 가격
  - `trendData` (String, 필수): 최근 가격 변동 추이 또는 시장 상황에 대한 설명 (예: "최근 출시되어 수요가 높음")

- **요청 예시**:
  ```
  GET /api/predict?productName=iPhone%2016&currentPrice=1500000&trendData=최근%20출시,%20수요%20높음
  ```

- **성공 응답 예시 (200 OK)**:
  ```json
  {
    "week1": 1520000,
    "week2": 1550000,
    "week3": 1530000,
    "week4": 1560000,
    "explanation": "초기 수요가 높고 브랜드 충성도가 강해, 약간의 변동은 있겠지만 앞으로 4주간 가격은 소폭 상승할 것으로 예상됩니다."
  }
  ```

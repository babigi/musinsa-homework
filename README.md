# Musinsa-homework

<br>

## 1. 기능 구현 사항

1. 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가를 조회한다.

2. 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드를 조회한다.

3. 각 카테고리 이름으로 최소, 최대 가격를 조회한다.
  - 잘못된 카테고리 입력 값을 통해 조회할 경우 예외 메세지를 반환한다.

<br>

## 2. 개발 스택

- Java11
- Gradle
- SpringBoot 2.7.1
  - Spring-Data-JPA
- H2-database(RDB)

<br>

## 3. 요청 처리 예시

### 1. 각 카테고리 상품 최저가 조회 API 
**`Request`**

- URL : localhost:8080/api/v1/products/search/lowest-price/categories/all
- Request Method : GET
  
**`Response`**

- HTTP/1.1 200
- Content-Type: application/json
- Payload
    ```
    {
      "data": {
        "products": [
          {
            "category": "sneakers",
            "brand": "A",
            "price": 9000
          },
          {
            "category": "bag",
            "brand": "A",
            "price": 2000
          },
          {
            "category": "top",
            "brand": "C",
            "price": 10000
          },
          {
            "category": "pants",
            "brand": "D",
            "price": 3000
          },
          {
            "category": "hat",
            "brand": "D",
            "price": 1500
          },
          {
            "category": "accessory",
            "brand": "D",
            "price": 2000
          },
          {
            "category": "outer",
            "brand": "E",
            "price": 5000
          },
          {
            "category": "sock",
            "brand": "I",
            "price": 1700
          }
        ],
        "totalPrice": 52200
      },
      "message": null,
      "httpStatus": 200
    }
    ```
  
### 2. 한 브랜드에서 모든 카테고리 조회 시 최저가 조회 API

`Request`

- URL : localhost:8080/api/v1/products/search/categories/all/brand/lowest-price
- Request Method : GET

`Response`

- HTTP/1.1 200
- Content-Type: application/json
- Payload
    ```
    {
      "data": {
        "brandName": "D",
        "price": 36100
      },
      "message": null,
      "httpStatus": 200
    }
    ```

### 3. 각 카테고리 이름으로 최소, 최대 가격 조회 API

`Request`

- URL : localhost:8080/api/v1/products/categories/2/brands/min-max-price
- Request Method : GET

`Response`

- HTTP/1.1 200
- Content-Type : application/json
- Payload
    ```
      {
      "data": {
        "minBrandPrice": {
          "brandName": "E",
          "price": 5000
        },
        "maxBrandPrice": {
          "brandName": "I",
          "price": 6700
        }
      },
      "message": null,
      "httpStatus": 200
    }
    ```

### 4. 잘못된 카테고리 입력시 예외 메세지 반환

`Request`

- URL : localhost:8080/api/v1/products/categories/1000/brands/min-max-price
- Request Method : GET

`Response`
- HTTP/1.1 400
- Content-Type : application/json
- Payload
  ```
  {
  "data": null,
  "message": "입력하신 카테고리가 존재하지 않습니다.(categoryId : 1000)",
  "httpStatus": 400
  }
  ```

<br>

## 4. 테스트 코드 추가
- 지속가능한 유지보수와 안정적인 기능 변경 및 리팩토링을 위해 테스트 코드를 추가하였습니다.
- 각 컴포넌트의 유닛 테스트를 정상 기능을 검증하였습니다.

<img width="400" alt="image" src="https://user-images.githubusercontent.com/48561660/179395613-35956b62-0a4b-410c-8c22-3d381fde11d4.png">

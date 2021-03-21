# url-shortening-service

URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service

URL Shortening Key는 8 Character 이내로 생성되어야 합니다.

## 구현 방법

DB의 auto increment 기능을 이용해서 생성된 id 값을 이용

DB의 id의 data type을 int unsigned 로 설정
 
int data type 숫자 범위(32비트)를 8 Character 이내의 키로 변환(16진수로 변환?)
 
문제점 : 관리할 수 있는 최대 수가 약 42억개로 제한됨

## API 환경 구성 가이드

- java 11
- spring boot 2.4.1
- gradle 6.8.3

## 실행 가이드(리눅스)

### gradle 설치
sdkman 을 이용한 간편 gradle 설치
```
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle 6.8.3
```

### java 11
java 11 버전이 없다면 sdkman으로 설치
```
sdk list java // 설치 가능한 자바 버전

sdk install java 11.0.10-zulu // 설치
```

### jar 빌드 및 실행
```
gradle build

java -jar build/libs/*.jar
```
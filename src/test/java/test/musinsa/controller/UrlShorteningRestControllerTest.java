package test.musinsa.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;
import test.musinsa.domain.UrlMap;
import test.musinsa.dto.RequestUrlShorteningDto;

/**
 * Created by YHH on 2021/03/21
 */
@Transactional
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UrlShorteningRestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@LocalServerPort
	private int port;

	@BeforeEach
	protected void setUp() {
		this.webTestClient = WebTestClient.bindToServer()
			.baseUrl("http://localhost:" + this.port)
			.build();
	}

	@Test
	void requestUrlShortening() {
		final String TEST_URL = "https://www.musinsa.com";

		RequestUrlShorteningDto requestUrlShorteningDto = RequestUrlShorteningDto.builder()
			.originUrl(TEST_URL)
			.build();

		UrlMap urlMap = this.webTestClient
			.post()
			.uri("/url-shortening")
			.body(Mono.just(requestUrlShorteningDto), RequestUrlShorteningDto.class)
			.exchange()
			.expectStatus()
			.isCreated()
			.returnResult(UrlMap.class)
			.getResponseBody()
			.blockFirst();

		this.webTestClient
			.get()
			.uri("/" + urlMap.getShorteningPath())
			.exchange()
			.expectStatus()
			.isFound()
			.expectHeader()
			.valueEquals("Location", urlMap.getOriginUrl());
	}
}
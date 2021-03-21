package test.musinsa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import test.musinsa.domain.UrlMap;
import test.musinsa.dto.ErrorMessageDto;
import test.musinsa.service.UrlShorteningService;

/**
 * Created by YHH on 2021/03/21
 */
@RequiredArgsConstructor
@Controller
public class IndexController {
	private final UrlShorteningService urlShorteningService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/**")
	public String redirectUrl(HttpServletRequest httpRequest) {

		UrlMap urlMap = this.urlShorteningService.findUrlMap(httpRequest.getRequestURL().toString());

		if (urlMap != null) {
			return "redirect:" + urlMap.getOriginUrl();
		}

		throw new IllegalArgumentException("알 수 없는 주소");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessageDto> handleBadException(Exception exception) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorMessageDto(exception.getMessage()));
	}
}

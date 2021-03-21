package test.musinsa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.musinsa.domain.UrlMap;
import test.musinsa.dto.ErrorMessageDto;
import test.musinsa.dto.RequestUrlShorteningDto;
import test.musinsa.service.UrlShorteningService;

/**
 * Created by YHH on 2021/03/19
 */
@RequiredArgsConstructor
@RequestMapping
@RestController
public class UrlShorteningRestController {
	private final UrlShorteningService urlShorteningService;

	@PostMapping(value = "/url-shortening", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UrlMap> requestUrlShortening(
		@RequestBody @Valid RequestUrlShorteningDto requestUrlShorteningDto) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(urlShorteningService.getUrlMap(requestUrlShorteningDto));
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorMessageDto> handleBadException(MethodArgumentNotValidException exception) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorMessageDto(
				exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
			));
	}
}

package test.musinsa.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import test.musinsa.domain.UrlMap;
import test.musinsa.domain.UrlMapRepository;
import test.musinsa.dto.RequestUrlShorteningDto;

/**
 * Created by YHH on 2021/03/19
 */
@RequiredArgsConstructor
@Service
public class UrlShorteningService {
	private final UrlMapRepository urlMapRepository;

	@Transactional
	public UrlMap getUrlMap(RequestUrlShorteningDto originUrl) {
		Optional<UrlMap> maybeUrlMap = this.urlMapRepository.findByOriginUrl(originUrl.getOriginUrl());

		if (maybeUrlMap.isPresent()) {
			UrlMap urlMap = maybeUrlMap.get();
			urlMap.addRequestCount();
			return urlMap;
		}

		UrlMap urlMap = this.urlMapRepository.save(UrlMap.from(originUrl.getOriginUrl()));

		urlMap.setShorteningPath();

		return urlMap;
	}

	public UrlMap findUrlMap(String requestUrl) {
		String[] urlToken = requestUrl.split("/");

		String shorteningPath = urlToken[urlToken.length - 1];

		return this.urlMapRepository.findByShorteningPath(shorteningPath)
			.orElseThrow(() -> new IllegalArgumentException("not found url"));
	}
}

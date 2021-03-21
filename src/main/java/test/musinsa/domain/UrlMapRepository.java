package test.musinsa.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YHH on 2021/03/21
 */
public interface UrlMapRepository extends JpaRepository<UrlMap, Long> {

	Optional<UrlMap> findByOriginUrl(String originUrl);

	Optional<UrlMap> findByShorteningPath(String shorteningPath);

}

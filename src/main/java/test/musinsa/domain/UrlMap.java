package test.musinsa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/03/19
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UrlMap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition = "INT(11) UNSIGNED")
	private Long id;

	@Column(name = "origin_url", nullable = false, unique = true)
	private String originUrl;

	@Column(name = "shortening_path", length = 8, unique = true)
	private String shorteningPath;

	@Column(name = "re_request_count")
	private Integer reRequestCount;

	private UrlMap(String originUrl) {
		this.originUrl = originUrl;
		this.reRequestCount = 1;
	}

	public static UrlMap from(String originUrl) {
		return new UrlMap(originUrl);
	}

	public void addRequestCount() {
		this.reRequestCount = this.reRequestCount + 1;
	}

	public void setShorteningPath() {
		if (this.id == null) {
			throw new IllegalArgumentException("encodingPath 생성 실패 : id 없음");
		}

		this.shorteningPath = Long.toHexString(this.id);
	}
}

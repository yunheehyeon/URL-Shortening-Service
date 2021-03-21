package test.musinsa.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by YHH on 2021/03/21
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestUrlShorteningDto {
	@NotEmpty(message = "url 입력 필수")
	@URL(message = "url 형식이 아닙니다.")
	private String originUrl;

	@Builder
	public RequestUrlShorteningDto(@NotEmpty String originUrl) {
		this.originUrl = originUrl;
	}
}

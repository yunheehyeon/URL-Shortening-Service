package test.musinsa.dto;

import org.springframework.web.bind.annotation.GetMapping;

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
public class ErrorMessageDto {
	private String message;

	public ErrorMessageDto(String message) {
		this.message = message;
	}
}

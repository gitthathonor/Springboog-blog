package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDto {
	private Integer id; // 수많은 id가 있기 때문에 헷갈릴 수 있어서 필드명을 다르게 작성
	private String title;
	private String username;
}

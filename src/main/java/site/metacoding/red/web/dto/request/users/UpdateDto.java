package site.metacoding.red.web.dto.request.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateDto {
	private String password;
	private String email;
}

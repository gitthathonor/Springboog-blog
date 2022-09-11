package site.metacoding.red.web.dto.response.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteDto {
	
	private String username;
	private Integer usersId;
	private Integer id;

}

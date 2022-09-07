package site.metacoding.red.web.dto.request.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Getter
@Setter
@AllArgsConstructor
public class UpdateDto {
	private String title;
	private String content;
	private Integer usersId;
	private Integer id;
	
	
	public Boards toEntity(Integer id, Integer usersId) {
		Boards boards = new Boards(this.title, this.content, id, usersId);
		return boards;
	}
}

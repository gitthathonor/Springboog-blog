package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt;
	
	public Boards() {
		
	}
	
	public Boards(String title, String content, Integer usersId) {
		this.title = title;
		this.content = content;
		this.usersId = usersId;
	}
	
	public Boards(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public Boards(String title, String content, Integer usersId, Integer id) {
		this.title = title;
		this.content = content;
		this.usersId = usersId;
		this.id = id;
	}
	
}

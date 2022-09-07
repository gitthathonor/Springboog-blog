package site.metacoding.red.web.dto.response.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagingDto {
	// DB에서 쿼리로 들고오도록 해본다.
	// 안되면 pageUtil을 통해서 제작
	
//	private Integer currentBlock;
	private Integer blockPage; // 1~5 = 1, 6~10 = 2 ... //변수
	private Integer blockCount; // 한 페이지에 페이지 넘버 갯수 5 / 1~5, 6~10, 11~15... //상수
	private Integer startPageNum; // 1 -> 6 -> 11 //변수
	private Integer lastPageNum; // 5 -> 10 -> 15 //변수
	private Integer totalCount; 
	private Integer totalPage;  // totalCount/한페이지당 갯수
	private Integer currentPage; // 현재 내가 어떤 페이지에 있는지
	private boolean isLast; // getter가 만들어지면 isLast() 이름으로 만들어짐 -> el에서는 last로 출력
	private boolean isFirst; // getter가 만들어지면 isFirst() 이름으로 만들어짐 -> el에서는 first로 출력
}

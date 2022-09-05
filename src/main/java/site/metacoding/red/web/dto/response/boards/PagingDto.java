package site.metacoding.red.web.dto.response.boards;

public class PagingDto {
	private Integer startNum;
	private Integer totalCount; 
	private Integer totalPage;  // totalCount/한페이지당 갯수
	private Integer currentPage; // 현재 내가 어떤 페이지에 있는지
	private boolean isLast;
	private boolean isFirst;
}

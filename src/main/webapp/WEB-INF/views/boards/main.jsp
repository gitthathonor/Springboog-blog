<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br>
	<div class="d-flex justify-content-end">
		<div style="width: 300px">
			<form class="d-flex" method="get" action="/">
				<input class="form-control me-2" type="text" placeholder="Search" name="keyword">
				<button class="btn btn-primary" type="submit">Search</button>
			</form>
		</div>
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boards" items="${boardsList}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%-- <a href="?page=${paging.currentPage+1}">다음페이지</a> --%>

	<%-- <div style="background-color: 'grey';">
		<h3>totalCount : ${paging.totalCount}</h3>
		<h3>totalPage : ${paging.totalPage}</h3>
		<h3>currentPage : ${paging.currentPage}</h3>
		<h3>isLast : ${paging.last}</h3>
		<h3>isFirst : ${paging.first}</h3>
	</div> --%>

	<%-- ${param.page } --%>
	<%-- <ul class="pagination">
		<c:if test="${!paging.first}">
			<li class="page-item"><a class="page-link" href="/?page=${param.page - 1}">Previous</a></li>
		</c:if>
		<c:forEach var='pageNum' begin="${startPage }" end="${endPage }" step="1">
			<li class="page-item"><a class="page-link" href="/?page=${pageNum-1 }">${pageNum }</a></li>
		</c:forEach>
		<c:if test="${!paging.last}">
			<li class="page-item"><a class="page-link" href="/?page=${param.page + 1}">Next</a></li>
		</c:if>
	</ul> --%>
	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class='page-item ${paging.first ? "disabled":""}'><a class="page-link"
				href="?page=${paging.currentPage-1}">Prev</a></li>
			<c:forEach var="num" begin="${paging.startPageNum }" end="${paging.lastPageNum}" step="1">
				<c:choose>
					<c:when test="${empty keyword }"><li class='page-item ${paging.currentPage == num-1 ? "active" : ""}'><a class="page-link"
					href="?page=${num-1}">${num}</a></li></c:when>
					<c:otherwise><li class='page-item ${paging.currentPage == num-1 ? "active" : ""}'><a class="page-link"
					href='?page=${num-1}&keyword=${keyword}'>${num}</a></li></c:otherwise> 
				</c:choose>
				
				
			</c:forEach>
			<li class='page-item ${paging.last ? "disabled":""}'><a class="page-link"
				href="?page=${paging.currentPage+1}">Next</a></li>
		</ul>
	</div>

</div>

<%@ include file="../layout/footer.jsp"%>


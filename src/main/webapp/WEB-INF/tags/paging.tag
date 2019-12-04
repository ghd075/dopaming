<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="paging" type="com.dopaming.www.common.Paging" %>
<%@ attribute name="jsFunc" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pagination">
<c:if test="${empty jsFunc}">
	<c:set var="jsFunc" value="go_page"></c:set>
</c:if>
<ul>
<c:if test="${paging.page>1}">
	<li><a href="javascript:${jsFunc}(${paging.page-1})">이전</a>
</c:if>
<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i">
	<c:if test="${i != paging.page}">
		<li><a href="javascript:${jsFunc}(${i})">${i}</a>
	</c:if>
	<c:if test="${i == paging.page}">
		<li class="active">${i}
	</c:if>
</c:forEach>
<c:if test="${paging.page<paging.totalPageCount}">
	<li><a href="javascript:${jsFunc}(${paging.page+1})">다음</a>
</c:if>
</ul>
</div>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="paging" type="com.dopaming.www.common.Paging" %>
<%@ attribute name="jsfunc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pagination">
<c:set var="pre" value="${paging.page-1}"></c:set>
<c:set var="pre2" value="${paging.page+1}"></c:set>

<ul>
<c:if test="${pre == 0}">
<c:set var="pre" value="${1}"/>
</c:if>
 <li><a href="javascript:${jsfunc}(${pre})">◀</a>
 
 <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i">
	<c:if test="${i != paging.page}">
		<li><a href="javascript:${jsfunc}(${i})">${i}</a>
	</c:if>
	<c:if test="${i == paging.page}">
		<li class="active">${i}
	</c:if>
</c:forEach>

<%-- <c:if test="${paging.endPage<paging.totalPageCount}"> //페이지째로 넘기는법--%>
<c:if test="${pre2 > paging.totalPageCount}">
<c:set var="pre2" value ="${paging.totalPageCount}"/>
</c:if>
<li><a href="javascript:${jsfunc}(${pre2})">▶</a>
</ul>
</div>
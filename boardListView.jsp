<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  #list-area{
       border : 1px solid white;
       text-align : center;
       
   }
  .outer a {
     color : white;
     text-decoration: none; 
   } 
</style>
</head>
<body>

  <jsp:include page="../common/menubar.jsp" />
   
    <div class="outer" align="center">
       <br>
       <h1 align="center">게시판</h1>
       <br>

       <div id="search-area">
         <form action="search.bo" method="get">
            <input type="hidden" name="currentPage" value="1">
            <select name="condition">
               <option value="writer">작성자</option>
               <option value="title">제목</option>
               <option value="content">내용</option>
            </select>
            <input type="text" name="keyword">
            <button type="submit">검색</button>
         </form>
       </div>
         
       <br>

       <table id="list-area">
         <thead>
            <tr>
              <th>글번호</th>
              <th width="400">글제목</th>
              <th>작성자</th>
              <th>조회수</th>
              <th>작성일</th>
            </tr>
         </thead>
         <tbody>
            <c:choose>
               <c:when test="${empty list}">
                 <tr colspan="5">조회된 결과가 없습니다.</tr>
               </c:when>
               <c:otherwise>
                  <c:forEach var="b" items="${list}">
                     <tr>
                       <td>${ b.boardNo }</td>
                       <td><a href="detail.bo?bno=${ b.boardNo }">${ b.boardTitle }</a></td>
                       <td>${ b.boardWriter }</td>
                       <td>${ b.count }</td>
                       <td>${ b.createDate }</td>
                     </tr>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </tbody>
       </table>

       <br>

       <div id="paging-area">

         <c:if test="${pi.currentPage ne 1}">
          <a href="list.bo?currentPage=${ pi.currentPage - 1 }">[이전]</a>
        </c:if>
          <c:forEach var="p" begin= "${pi.startPage}" end="${pi.endPage}" >
            <a href="list.bo?currentPage=${ p }">[${ p }]</a>
          </c:forEach>
     
          <c:if test="${pi.currentPage ne pi.maxPage}">
          <a href="list.bo?currentPage=${pi.currentPage + 1}">[다음]</a>
        </c:if>
       </div>

       <br><br>

    </div>
   



</body>
</html>
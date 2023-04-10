//페이지 번호와 한 페이지에 보여줄 게시글 수를 미리 상수로 지정합니다.
//java
//Copy code
//final int ARTICLE_PER_PAGE = 10; // 한 페이지당 보여줄 게시글 수
//final int PAGE_PER_BLOCK = 5; // 한 블록당 보여줄 페이지 수
//요청받은 페이지 번호를 파라미터에서 받아옵니다.
//java
//Copy code
//int pageNum = Integer.parseInt(request.getParameter("page"));
//데이터베이스에서 해당 페이지에 해당하는 게시글을 가져오기 위해 SQL 쿼리문을 작성합니다.
//java
//Copy code
//String sql = "SELECT * FROM article ORDER BY id DESC LIMIT ?, ?";
//int startArticleIndex = (pageNum - 1) * ARTICLE_PER_PAGE;
//try(Connection conn = DriverManager.getConnection(url, user, password);
//    PreparedStatement pstmt = conn.prepareStatement(sql)) {
//    pstmt.setInt(1, startArticleIndex);
//    pstmt.setInt(2, ARTICLE_PER_PAGE);
//    try(ResultSet rs = pstmt.executeQuery()) {
//        // 게시글 데이터를 가져온 후, JSP에 전달하여 출력
//    }
//}
//게시글 총 개수와 페이지 총 개수를 계산합니다.
//java
//Copy code
//String sql = "SELECT COUNT(*) FROM article";
//try(Connection conn = DriverManager.getConnection(url, user, password);
//    PreparedStatement pstmt = conn.prepareStatement(sql);
//    ResultSet rs = pstmt.executeQuery()) {
//    rs.next();
//    int totalArticleCount = rs.getInt(1);
//    int totalPageCount = (int) Math.ceil((double) totalArticleCount / ARTICLE_PER_PAGE);
//    int currentPageBlock = (int) Math.ceil((double) pageNum / PAGE_PER_BLOCK);
//    int startPageNum = (currentPageBlock - 1) * PAGE_PER_BLOCK + 1;
//    int endPageNum = currentPageBlock * PAGE_PER_BLOCK;
//    if(endPageNum > totalPageCount) {
//        endPageNum = totalPageCount;
//    }
//}
//JSP에서 페이지 번호에 대한 링크를 출력합니다.
//html
//Copy code
//<ul class="pagination">
//<% if(currentPageBlock > 1) { %>
//    <li><a href="list?page=<%= startPageNum - 1 %>">이전</a></li>
//<% } %>
//<% for(int i = startPageNum; i <= endPageNum; i++) { %>
//    <li <% if(pageNum == i) { %>class="active"<% } %>>
//        <a href="list?page=<%= i %>"><%= i %></a>
//    </li>
//<% } %>
//<% if(currentPageBlock < Math.ceil((double) totalPageCount / PAGE_PER_BLOCK)) { %>
//    <li><a href="list?page=<%= endPageNum + 1 %>">다음</a></li>
//<% } %>
//</ul>
//위와 같은 코드를 사용하여 JSP에서 페이지네이션을 구현할 수 있습니다. 페이지 번호에 대한 링크를 클릭하면 해당 페이지에 대한 게시글을 가져와서 출력합니다
public class BoardWriteServlet extends HttpServlet {
	prorected
	void doGet(HttpServletRequest req, HttpServletResponse resp) throws	ServletException, IOException {

		String path = "/WEB-INF/views/board/boardInsertForm.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}

	prorected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ervletException, IOException {

    String btitle = req.getParameter("btitle"); // 제목

    String bwriter = req.getParameter("bwriter"); // 작성자

    String bcontent = req.getParameter("bcontent"); // 내용



    Board bd = new Board();



    bd.setbtitle(btitle);

    bd.setbwriter(bwriter);

    bd.setbcontent(bcontent);



    try { 

      BoardService service = new BoardService();

    

      int result = service.insertBoard(bd);

      HttpSession session = req.getSession();

 

      if (result > 0) {

        session.setAttribute("message", "파일 업로드 성공");

      } else {

        session.setAttribute("message", "파일 업로드 실패");

      }

      resp.senRedirect(req.getContextPath());

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

}​ ​

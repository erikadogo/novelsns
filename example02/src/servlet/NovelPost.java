package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Novel;
import model.NovelPostLogic;

/**
 * Servlet implementation class NovelPost
 */
@WebServlet("/NovelPost")
public class NovelPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/WEB-INF/jsp/novelPost.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		Novel novel = null;
		
		//小説情報
		String novelId = request.getParameter("novelId");//自動入力
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String genre = request.getParameter("genre"); //ラジオボタンから選択
		String summary = request.getParameter("summary");
		
		novel = new Novel(novelId,title,text,genre,summary);
		
		//データベースに登録
		NovelPostLogic novelPostLogic = new NovelPostLogic();
		novelPostLogic.execute(novel);
		
		response.sendRedirect("Main");
		
	}

}

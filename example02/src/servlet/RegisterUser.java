package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.RegisterUserLogic;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード先
		String forwardPath = null;

		//サーブレットクラスの動作を決定する「action」の値を
		//リクエストパラメータから取得
		String action = request.getParameter("action");

		//「登録の開始」をリクエストされたときの処理
		if(action == null) {
			//フォワード先を設定
			forwardPath = "/registerForm.jsp";
		}

		//登録確認画面から「登録実行」をリクエストされたときの処理
		else if(action.equals("done")){
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account registerUser = (Account) session.getAttribute("registerUser");

			//登録処理の呼び出し
			RegisterUserLogic logic = new RegisterUserLogic();
			boolean isRegist = logic.execute(registerUser);

			if(isRegist){
				//不要となったセッションスコープ内のインスタンスを削除
				session.removeAttribute("registerUser");

				//登録後のフォワード先を設定
				forwardPath = "/WEB-INF/jsp/registerDone.jsp";
			}else{
				response.sendRedirect("/WEB-INF/jsp/registerNG.jsp");
				return;
			}
		}
		//設定されたフォワード先にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");


		//登録するユーザーの情報を設定
		Account registerUser = new Account(id,pass,mail,name);

		//セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request,response);

	}

}

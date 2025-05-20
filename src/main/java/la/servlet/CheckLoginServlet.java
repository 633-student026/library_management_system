package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.DAO.DAOException;
import la.DAO.LoginDAO;
import la.bean.AccountBean;

@WebServlet("/ChekLoginServlet")
public class CheckLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータ取得
		// リクエスト内のユーザ入力をUTF-8に変換
		request.setCharacterEncoding("UTF-8");

		// ユーザ入力をリクエストから取り出す
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		// パラメータチェック
		// 各変数が値が格納されているか
		if (id == null || id.length() == 0 || pass == null || pass.length() == 0) {
			//リクエストスコープにエラー文をmessageという名前でセット
			request.setAttribute("message", "IDまたはパスワードが未入力です");
			// エラー表示画面へのフォワードのためにインスタンスを作成
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			// フォワード
			rd.forward(request, response);
			return;
		}
		try {
			LoginDAO dao = new LoginDAO();
			List<String> result = dao.findAll(Integer.parseInt(id));
			if (result.size() == 0 || result == null) {
				//リクエストスコープにエラー文をmessageという名前でセット
				request.setAttribute("message", "IDまたはパスワードが間違っています");
				// エラー表示画面へのフォワードのためにインスタンスを作成
				RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
				// フォワード
				rd.forward(request, response);
				return;
			}
			System.out.println(result.get(0));
			if (id.equals(result.get(0)) && pass.equals(result.get(1))) {
				// セッション領域の取得
				HttpSession session = request.getSession();
				AccountBean sessionBean = (AccountBean) session.getAttribute("account");
				AccountBean account = new AccountBean(Integer.parseInt(id), result.get(2));

				//セッションがなければセット
				if (sessionBean == null) {
					session.setAttribute("account", account);
				}
				// リクエストスコープに入れてJSPへフォーワードする
				// リクエストスコープに作成したインスタンスをセット
				request.setAttribute("account", account);
				// フォワードのためのインスタンスを作成
				RequestDispatcher rd = request.getRequestDispatcher("mainMenu.jsp");
				System.out.println("rb");
				// フォワード
				rd.forward(request, response);
				return;
			} else {
				//リクエストスコープにエラー文をmessageという名前でセット
				request.setAttribute("message", "IDまたはパスワードが間違っています");
				// エラー表示画面へのフォワードのためにインスタンスを作成
				RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
				// フォワード
				rd.forward(request, response);
				return;
			}
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("message", "予期せぬエラーが発生");
			// エラー表示画面へのフォワードのためにインスタンスを作成
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
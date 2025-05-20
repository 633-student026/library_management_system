package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.DAO.DAOException;
import la.DAO.ReturnDAO;
import la.bean.AccountBean;

@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータ取得
		// リクエスト内のユーザ入力をUTF-8に変換
		request.setCharacterEncoding("UTF-8");
		// ユーザ入力をリクエストから取り出す
		String bookId = request.getParameter("bookID");

		// パラメータチェック
		// 各変数が値が格納されているか
		if (bookId == null || bookId.length() == 0) {
			//リクエストスコープにエラー文をmessageという名前でセット
			request.setAttribute("message", "IDが未入力です");
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			// フォワード
			rd.forward(request, response);
			return;
		}

		// セッション領域の取得
		HttpSession session = request.getSession();
		AccountBean sessionBean = (AccountBean) session.getAttribute("account");

		//ログインしていなかったら
		if (sessionBean == null) {
			//リクエストスコープにエラー文をmessageという名前でセット
			request.setAttribute("message", "ログインしてください");
			// エラー表示画面へのフォワードのためにインスタンスを作成
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			// フォワード
			rd.forward(request, response);
			return;
		}

		try {
			ReturnDAO dao = new ReturnDAO();
			int result = dao.ReturnBook(sessionBean.getId(), Integer.parseInt(bookId));
			if (result == -1) {
				//リクエストスコープにエラー文をmessageという名前でセット
				request.setAttribute("message", "貸出登録されていない本です");
				// エラー表示画面へのフォワードのためにインスタンスを作成
				RequestDispatcher rd = request.getRequestDispatcher("/errInput2.jsp");
				// フォワード
				rd.forward(request, response);
				return;
			}
			System.out.println(result + "件追加");
			//リクエストスコープにエラー文をmessageという名前でセット
			request.setAttribute("bookId", bookId);
			// エラー表示画面へのフォワードのためにインスタンスを作成
			RequestDispatcher rd = request.getRequestDispatcher("/afterReturn.jsp");
			// フォワード
			rd.forward(request, response);
			return;

		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("message", "予期せぬエラーが発生");
			// エラー表示画面へのフォワードのためにインスタンスを作成
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			rd.forward(request, response);
			return;
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
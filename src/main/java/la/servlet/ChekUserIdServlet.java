package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.DAO.DAOException;
import la.DAO.NewLoginDAO;
import la.bean.AccountBean;

@WebServlet("/ChekUserIdServlet")
public class ChekUserIdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータ取得
		// リクエスト内のユーザ入力をUTF-8に変換

		request.setCharacterEncoding("UTF-8");
		// ユーザ入力をリクエストから取り出す
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String address = request.getParameter("address");
		String stAddressNum = request.getParameter("addressNum");
		String stDate = request.getParameter("date");
		String stTel = request.getParameter("tel");
		String email = request.getParameter("email");
		int id = 0;

		// パラメータチェック
		// 各変数が値が格納されているか
		System.out.println(name + pass + address + stAddressNum + stDate + stTel + email);
		if (name == null || name.length() == 0 || pass == null
				|| pass.length() == 0 || address == null
				|| address.length() == 0 || stAddressNum == null
				|| stAddressNum.length() == 0 || stDate == null
				|| stDate.length() == 0 || stTel == null
				|| stTel.length() == 0) {
			//リクエストスコープにエラー文をmessageという名前でセット
			request.setAttribute("message", "未入力欄があります");
			// エラー表示画面へのフォワードのためにインスタンスを作成
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			// フォワード
			rd.forward(request, response);
			return;
		}

		int addressNum, tel;
		// 入力がすべて数値であるかチェック
		try {
			addressNum = Integer.parseInt(stAddressNum);
			tel = Integer.parseInt(stTel);
			System.out.println(1);
		} catch (NumberFormatException e) {
			request.setAttribute("message", "郵便番号、電話番号、生年月日は数値で入力してください");
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			rd.forward(request, response);
			return;
		}

		// 表示データをJavaBeansにする
		//計算結果をPersonBean型で保存
		AccountBean account = new AccountBean(name, addressNum, address, tel, email, stDate, pass, id);

		//データベース登録
		try {
			NewLoginDAO dao = new NewLoginDAO();
			String row = dao.addAccount(account);
			account.setId(Integer.parseInt(row));
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("message", "予期せぬエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("/errInput.jsp");
			rd.forward(request, response);
		}

		// JavaBeansをリクエストスコープに入れてJSPへフォーワードする
		// リクエストスコープに作成したPersonBean型のインスタンスをセット
		request.setAttribute("account", account);
		// フォワードのためのインスタンスを作成
		RequestDispatcher rd = request.getRequestDispatcher("/checkAccount.jsp");
		// フォワード
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
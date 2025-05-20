package la.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnDAO extends BorrowingDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public ReturnDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	public int ReturnBook(int userId, int bookId) throws DAOException {
		// SQL文の作成
		int check = checkBook(bookId);
		if (check == -1) {
			return -1;
		}
		String sql = "update borrowing set return_date = current_date where bookid = ? and id = ?;";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, bookId);
			st.setInt(2, userId);
			// SQLの実行
			int rows = st.executeUpdate();

			sql = " update bookItem set  is_reservation_availability = not is_reservation_availability,  is_borrowing_availability = not  is_borrowing_availability where bookid = ?;";

			try (Connection con2 = DriverManager.getConnection(url, user, pass);
					// PreparedStatementオブジェクトの取得
					PreparedStatement st2 = con2.prepareStatement(sql);) {
				st2.setInt(1, bookId);
				// SQLの実行
				st2.executeUpdate();
				return rows;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

}
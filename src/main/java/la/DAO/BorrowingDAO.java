package la.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowingDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public BorrowingDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	public int checkBook(int bookId) throws DAOException {
		// SQL文の作成
		String sql = "SELECT  is_reservation_availability,  is_borrowing_availability FROM bookItem WHERE  bookid = "
				+ bookId + ";";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得

			boolean is_borrowing_availability;
			if (rs.next()) {
				is_borrowing_availability = rs.getBoolean("is_borrowing_availability");
				System.out.println(is_borrowing_availability);

			} else {
				return -2;
			}

			if (!is_borrowing_availability) {
				return -1;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public int BorrowingBook(int userId, int bookId) throws DAOException {
		if (checkBook(bookId) <= -1) {
			return -1;
		}
		// SQL文の作成
		String sql = "insert into borrowing (bookID, id, checkout_date, loan_period) values (?, ?, current_date, now() + cast('1 months' as INTERVAL));";
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
				System.out.println(rows);
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
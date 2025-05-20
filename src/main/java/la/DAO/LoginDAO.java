package la.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public LoginDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	public List<String> findAll(int id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT id, pass, name FROM account WHERE id = " + id + ";";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得

			List<String> list = new ArrayList<String>();

			if (rs.next()) {
				System.out.println("LoginDAO");
				String user_id = String.valueOf(rs.getInt("id"));
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				list.add(user_id);
				list.add(pass);
				list.add(name);

				return list;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
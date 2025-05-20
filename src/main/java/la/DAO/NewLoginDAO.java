package la.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.AccountBean;

public class NewLoginDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public NewLoginDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	public String addAccount(AccountBean bean) throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO account (\n"
				+ "    name,"
				+ "    address,"
				+ "    addressNum,"
				+ "    tel,"
				+ "    email,"
				+ "    birth,"
				+ "    c_date,"
				+ "    pass,"
				+ "    is_parmmiion"
				+ ") VALUES"
				+ "    (?, ?, ?, ?, ?, cast( ? as date ), current_date, ?, false);";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, bean.getName());
			st.setString(2, bean.getAddress());
			st.setInt(3, bean.getAddressNum());
			st.setInt(4, bean.getTel());
			st.setString(5, bean.getEmail());
			st.setString(6, bean.getDate());

			st.setString(7, bean.getPass());

			// SQLの実行
			int rows = st.executeUpdate();

			return findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public String findAll() throws DAOException {
		// SQL文の作成
		String sql = "SELECT id FROM account order by id desc;";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得

			if (rs.next()) {
				return rs.getString("id");
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
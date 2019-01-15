package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class RegisterUserDAO {

	public boolean findId(String id){//IDの重複をチェック
        Connection conn = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "example";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);

			//SELECT文を準備
			String sql = "SELECT USER_ID FROM account WHERE USER_ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//?に入る引数を指定
			pStmt.setString(1,id);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();
			if(rs.equals(id)){
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean findMail(String mail){//メールアドレスの重複をチェック
        Connection conn = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "example";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);

			//SELECT文を準備
			String sql = "SELECT MAIL FROM account WHERE MAIL=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//?に入る引数を指定
			pStmt.setString(1,mail);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();
			if(rs.equals(mail)){
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


	public boolean create(User user){//ユーザー登録処理
        Connection conn = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "example";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
        	//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			//データベースへ接続
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);
			//INSERT文を準備
			String sql = "INSERT INTO USER(ID,MAIL,NAME,PASS) VALUES(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1,user.getId());
			pStmt.setString(2, user.getMail());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getPass());

			//INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result != 1){
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			//データベース切断
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
        return true;
	}
}

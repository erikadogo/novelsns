package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {

	public boolean findId(String id){//IDの重複をチェック
        Connection conn = null;
        Account account = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "novelsns";//データベース名
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
			
			while(rs.next()) {
				String rsId = rs.getString("user_id");
				account = new Account(rsId);
			}
			if(account.getMail().equals(id)) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return true;
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean findMail(String mail){//メールアドレスの重複をチェック
        Connection conn = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "novelsns";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);
			Account account = null;

			//SELECT文を準備
			String sql = "SELECT MAIL FROM account WHERE MAIL=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//?に入る引数を指定
			pStmt.setString(1,mail);
			//SELECT文の実行
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String rsMail = rs.getString("mail");
				account = new Account(rsMail);
			}
			if(account.getMail().equals(mail)){
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return true;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


	public boolean create(Account account){//ユーザー登録処理
        Connection conn = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "novelsns";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
        	//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);
			//データベースへ接続
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);
			//INSERT文を準備
			String sql = "INSERT INTO account(USER_ID,PASS,MAIL,NAME) VALUES(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1,account.getUserId());
			pStmt.setString(2, account.getPass());
			pStmt.setString(3, account.getMail());
			pStmt.setString(4, account.getName());

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
			}
		}
        return true;
	}

	public Account findByLogin(Login login){
        Connection conn = null;
        Account account = null;

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "novelsns";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);
			//SELECT文を準備
			String sql = "SELECT USER_ID, PASS, MAIL, NAME FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());

			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();

			//一致したユーザーが存在した場合
			//そのユーザーを表すAccountインスタンスを生成
			if(rs.next()){
				//結果表からデータを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");

				account = new Account(userId,pass,mail,name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try {
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
        //見つかったユーザーまたはnullを返す
        return account;
	}
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
	public Account findByLogin(Login login){
        Connection conn = null;
        Account account = null;

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
			String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
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
				int age = rs.getInt("AGE");

				account = new Account(userId,pass,mail,name,age);
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

	public Account findForRegist(Login login){
        Connection conn = null;
        Account account = null;

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
			String sql = "SELECT USER_ID FROM ACCOUNT WHERE USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getUserId());

			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//一致したユーザーが存在した場合
			//そのユーザーを表すAccountインスタンスを生成
			if(rs.next()){
				//結果表からデータを取得
				String userId = rs.getString("USER_ID");
				account = new Account(userId);
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

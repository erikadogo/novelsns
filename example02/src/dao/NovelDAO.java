package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Novel;

public class NovelDAO {

	public List<Novel> findAll(){//サイトに投稿された小説の取得
        Connection conn = null;
        List <Novel> novelList = new ArrayList<>();

        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "novelsns";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード

        try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL + DB_NAME+DB_ENCODE , DB_USER, DB_PASS);

			String sql =  "SELECT * from novels";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()){
				String id = rs.getString("NOVEL_ID");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String desc = rs.getString("DESC");
				String time = rs.getString("POST_TIME");
				Novel novel = new Novel(id,title,text,desc,time);
				novelList.add(novel);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return novelList;
	}

	public boolean create(Novel novel) {
		Connection conn = null;
		
        final String DRIVER_NAME = "com.mysql.jdbc.Driver";//MySQLドライバ
        final String DB_URL = "jdbc:mysql://localhost:3306/";//DBサーバー名
        final String DB_NAME = "novelsns";//データベース名
        final String DB_ENCODE = "?useUnicode=true&characterEncoding=utf8";//文字化け防止
        final String DB_USER = "root";//ユーザーID
        final String DB_PASS = "root";//パスワード
		
		try {
			//データベース接続
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL + DB_NAME + DB_ENCODE, DB_USER, DB_PASS);
			//INSERT文の準備
			String sql = "INSERT INTO novels(title, text, genre, summary) VALUES(?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, novel.getTitle());
			pStmt.setString(2, novel.getText());
			pStmt.setString(3, novel.getGenre());
			pStmt.setString(4, novel.getSummary());
			
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
		
	}
}

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

	public Novel findAll(){//サイトに投稿された小説の取得
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
}

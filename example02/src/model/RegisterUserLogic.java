package model;

import dao.RegisterUserDAO;

public class RegisterUserLogic {
	public boolean execute(User user){
		//登録処理
		//ID、メールをデータベースから検索し同じものがないか調べる
		String id = user.getId();
		String mail = user.getMail();
		RegisterUserDAO dao = new RegisterUserDAO();

		if(dao.findId(id) || dao.findMail(mail)){
			return false;
		}else{
			dao.create(user);
			return true;
		}



	}
}

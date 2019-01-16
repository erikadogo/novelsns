package model;

import dao.AccountDAO;

public class RegisterUserLogic {
	public boolean execute(Account account){
		String id = account.getUserId();
		String mail = account.getMail();
		AccountDAO dao = new AccountDAO();

		//ID、メールをデータベースから検索し同じものがないか調べる
		boolean isFindId = dao.findId(id);
		boolean isFindMail = dao.findMail(mail);

		if(isFindId || isFindMail){
			return false;
		}else{
			//登録処理
			dao.create(account);
			return true;
		}



	}
}

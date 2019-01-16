package test;

import model.Account;
import model.RegisterUserLogic;

public class RegisterTest {

	public static void main(String[] args) {
		testExecute1();//会員登録成功テスト
		testExecute2();//会員登録失敗テスト
	}

	public static void testExecute1(){
		Account registUser = new Account("goto12","12345","erika@gmail.com","erika");
		RegisterUserLogic bo = new RegisterUserLogic();
		boolean result = bo.execute(registUser);
		if(result){
			System.out.println("成功しました");
		}else{
			System.out.println("失敗しました");
		}
	}

	public static void testExecute2(){
		Account registUser = new Account("minato1","1234","minato@sukkiri.com","minato");
		RegisterUserLogic bo = new RegisterUserLogic();
		boolean result = bo.execute(registUser);
		if(!result){
			System.out.println("成功しました");
		}else if(result){
			System.out.println("失敗しました");
		}
	}

}

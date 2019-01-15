package test;

import model.RegisterUserLogic;
import model.User;

public class RegisterTest {

	public static void main(String[] args) {
		testExecute1();//会員登録成功テスト
		testExecute2();//会員登録失敗テスト
	}

	public static void testExecute1(){
		User user = new User("goto12","erika@gmail.com","erika","12345");
		RegisterUserLogic bo = new RegisterUserLogic();
		boolean result = bo.execute(user);
		if(result){
			System.out.println("成功しました");
		}else{
			System.out.println("失敗しました");
		}
	}

	public static void testExecute2(){
		User user = new User("minato","minato@sukkiri.com","1234");
		RegisterUserLogic bo = new RegisterUserLogic();
		boolean result = bo.execute(user);
		if(!result){
			System.out.println("成功しました");
		}else{
			System.out.println("失敗しました");
		}
	}

}

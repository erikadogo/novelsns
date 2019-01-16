<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>会員登録して小説を書こう/読もう</p>
<form action="/example02/RegisterUser" method="post">
ユーザーID：<input type="text" name="id" required><br>
パスワード:<input type="password" name="pass" required><br>
メールアドレス:<input type="text" name="mail" required><br>
名前:<input type="text" name="name"><br>
<input type="submit" value="確認">
</form>
<p>会員の人は<a href="/example02/LoginServlet">ログイン</a>してね</p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小説投稿SNS ｜ ログイン</title>
</head>
<body>
<form action="/example/LoginServlet" method="post">
ユーザーID：<input type="text" name="userId"><br>
パスワード:<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
<p>まだ登録していない人は<a href="/example02/RegisterUser">会員登録</a>をお願いします</p>
</body>
</html>
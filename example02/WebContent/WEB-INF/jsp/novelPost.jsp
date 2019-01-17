<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小説投稿</title>
</head>
<body>
<h1>小説投稿画面</h1>
<form action="/example02/NovelPost" method="post">
タイトル:<input type="text" name="novelId">
本文:<textarea rows="300" cols="50"></textarea>
説明:<textarea rows="100" cols="30"></textarea>
<button type="submit">小説投稿</button>
</form>
</body>
</html>
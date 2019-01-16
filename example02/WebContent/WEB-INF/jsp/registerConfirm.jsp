<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%
Account registerUser=(Account) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>下記のユーザーを登録します</p>
<p>
ログインID:<%=registerUser.getUserId() %><br>
メールアドレス:<%=registerUser.getMail() %>><br>
名前:<%=registerUser.getName() %><br>
</p>
<a href="/example02/RegisterUser">戻る</a>
<a href="/example02/RegisterUser?action=done">登録</a>
</body>
</html>
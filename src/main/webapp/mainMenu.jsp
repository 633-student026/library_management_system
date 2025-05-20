<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<h1>ログイン成功</h1>
<h2>${account.name}さん</h2>

<a href="borrowing.jsp">貸出・返却</a><br>
<a href="change_info.jsp">会員情報の変更・削除</a><br>
<a href="reservation_history.jsp">予約履歴</a><br>
<a href="borrowing_history.jsp">貸出履歴</a><br>
<form action="" method = "get">
<p>本の検索はこちらから↓</p>
<input type="text"><button>検索</button>
</form>

</body>
</html>
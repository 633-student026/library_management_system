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

 <center>
 <div class="container">
 <h1>登録内容確認</h1>
 
 <h2>ID：${account.id}</h2>

 <p>名前：${account.name}</p>

 <p>郵便番号：${account.addressNum} </p>

 <p>住所：${account.address}</p>

<p>電話番号：${account.tel}</p>

 <p>メールアドレス：${account.email }</p>

 <p>生年月日：${account.date}</p>

  <br><input type="button" onclick="location.href='/book/top.jsp'" value="確認">
 </div>

 
</center>
    
</body>
</html>
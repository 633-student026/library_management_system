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
 <h1>新規会員登録</h1>
 <form action="/book/ChekUserIdServlet" method="post">
 <p>名前</p>
 <input type="text" name = "name" id="name" class="textbox" placeholder="名前を入力">
 <p>パスワード</p>
 <input type="password" name = "pass" id="pass" class="textbox" placeholder="パスワードを入力">
 <p>郵便番号</p>
 <input type="text" name = "addressNum" id="addressNum" class="textbox" placeholder="郵便番号を入力">
 <p>住所</p>
 <input type="text" name = "address" id="address" class="textbox" placeholder="住所を入力">
 <p>メールアドレス</p>
 <input type="text" name = "email" id="mailAddress" class="textbox" placeholder="メールアドレスを入力">
 <p>電話番号</p>
 <input type="text" name = "tel" id="tel" class="textbox" placeholder="電話番号を入力">
 <p>生年月日</p>
 <input type="date" name = "date" id="userDate">
  <br><button>確認</button>
 </div>
 </form>
 
</center>
    
</body>
</html>
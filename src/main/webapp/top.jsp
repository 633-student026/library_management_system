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
 <h1>ログイン</h1>
 <p>作業を続けるには、ログインしてください。</p>
 <form action = "/book/ChekLoginServlet" method="post">
 <p>ID</p>
 <input type="text" name = "id" id="ID" class="textbox" placeholder="ユーザIDを入力">
 <p>パスワード</p>
 <input type="password" name = "pass" id="pass" class="textbox" placeholder="パスワードを入力">
 <br>
<br><button>ログイン</button>
 </div>
 </form>
         
<a href="newLogin.jsp">新規会員登録</a>   
</center>
    
</body>
</html>
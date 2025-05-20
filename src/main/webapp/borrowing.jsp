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
 <h1>貸出・返却</h1>
 
 <p>借りる本のIDを入力してください</p>
 <form action="BorrowingServlet" method="get">
 <input type="text" name="bookID"><button>貸出</button>
 </form>
 <br>
 
 <p>返す本のIDを入力してください</p>
 <form action="ReturnServlet" method="get">
 <input type="text" name="bookID"><button>返却</button>
 </form>
 <br>
  <button type="button" onclick="history.back()">戻る</button>
 
</center>
    
</body>
</html>
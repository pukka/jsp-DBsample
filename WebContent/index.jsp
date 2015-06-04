<%@ page import="java.sql.*, dbpack.DBAccess"
    contentType="text/html; charset=UTF-8" %>
<%
  DBAccess db = new DBAccess();
  db.open();
  ResultSet rs = db.getResultSet("select * from user");
  String tableHTML = "<table border=1>";
  tableHTML += "<tr bgcolor=\"000080\"><td><font color=\"white\">メンバーID</font></td>"
	  + "<td><font color=\"white\">名前</font></td>"
	  + "<td><font color=\"white\">パスワード</font></td>";
  while(rs.next()) {
    int id = rs.getInt("id");
    String get_name = rs.getString("name");
    String get_password = rs.getString("password");

    tableHTML += "<tr><td align=\"right\">" + id + "</td>"
      + "<td>" + get_name + "</td><td>" + get_password + "</td></tr>";
  }
  tableHTML += "</table>";
  db.close();
%>

<html>
<head>
  <title>データベースサンプル</title>
  <meta charset='utf-8'>
</head>
<body>
  <p>データベース接続サンプル</p>
  <%= tableHTML %>
  <a href="insert.html">新規データの追加へ</a>
</body>
</html>
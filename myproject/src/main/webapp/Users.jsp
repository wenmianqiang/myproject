<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,classes.Users" %>
<%@page import="java.util.List"%>
<%@ page  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>  
<body>
<center>
<h1>用户数据</h1>   
<form action="UsersServlet" method="Post">
<table width="100%"  border="3px" cellpadding="1" cellspacing="1">
<tr>
   <td align="center">UserName</td>
   <td align="center">Name</td>
   <td align="center">Password</td>
   <td align="center">Email</td>      
   <td align="center">PhoneNumber</td>
   <td align="center">IdCardNo</td>
   <td align="center">Time</td>
 </tr>

<c:forEach items="${list}" var="user">
  <tr>
        <td align="center">${user.getUserName()}</td>
        <td align="center">${user.getName()}</td>
        <td align="center">${user.getPassword()}</td>
        <td align="center">${user.getEmail()}</td>
        <td align="center">${user.getPhoneNumber()}</td>
        <td align="center">${user.getIdCardNo()}</td>
        <td align="center">${user.getTime()}</td>

   </tr>
</c:forEach>

</table>
</form>
</center>
</body>
</html>

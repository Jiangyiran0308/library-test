<%--
  Created by IntelliJ IDEA.
  User: Jiang
  Date: 2018/8/2
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理系统</title>
    <style>
        .loginbackground{
            background-color: #B7D4FF;
            height: 70%;
            width: 100%;
            padding-top: 50px;
        }
    </style>
</head>

<body>
<div class="loginbackground">
    退出成功！4秒后跳转
</div>
<script>

    setTimeout(function () {
        window.location.href = "/logout" ;
    },4000)

</script>

</body>
</html>

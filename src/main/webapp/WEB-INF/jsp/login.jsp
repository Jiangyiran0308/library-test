<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\7\31 0031
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>图书管理系统</title>
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .loginbackground{
            background-color: #B7D4FF;
            height: 70%;
            width: 100%;
            padding-top: 50px;
        }
        .logincss{
            background-color: #FFFFFF;
            border-radius: 15px;
            height: 390px;
            width: 370px;
            padding: 30px;
            margin: auto;
            top: 0;
            left: 0;
            right: 100px;
            bottom: 0;
        }

        .loginbtn{
            background-color: #3795FF;
            width:310px;
            height: 50px;
            text-align: center;
            color: #FFFFFF;
            font-size: 26px;
            font-family: 微软雅黑;
            border-radius: 6px;
            padding: 5px;
        }
        .loginbtn:hover{
            background-color:#26BEFF ;
        }

    </style>
</head>
<body>
<div>

    <div style="height: 100px;font-family: 微软雅黑;font-size: 55px;font-weight: bold;color:#3795FF;">图书管理系统</div>
    <div class="loginbackground">
        <div class="logincss">
            <div style="font-weight: bold;font-family:黑体;font-size: 26px;text-align: center;padding-bottom: 30px">
                <span >用户登录</span>
            </div>
            <form method="get" id="log_in">
                <div class="input-group input-group-lg" style="padding-bottom: 40px">
                    <span class="input-group-addon" id="basic-addon1" style="width: 50px">
                        <span class="glyphicon glyphicon-user"></span>
                    </span>
                    <input type="text" name="admin" class="form-control"  aria-describedby="sizing-addon1">
                </div>
                <div class="input-group input-group-lg" style="padding-bottom: 40px">
                    <span class="input-group-addon" id="basic-addon2" style="width: 50px">
                        <span class="glyphicon glyphicon-lock"></span>
                    </span>
                    <input type="text" name="password" class="form-control"  aria-describedby="sizing-addon1">
                </div>
                <div id="loading" class="loginbtn" onclick="fo_sunbimt()">
                    登陆
                </div>
            </form>

        </div>
    </div>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>


    function fo_sunbimt() {
        var f = document.getElementById("log_in") ;
        f.submit() ;

    }
    

</script>

</body>
</html>

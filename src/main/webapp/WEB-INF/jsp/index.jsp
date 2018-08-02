<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Jiang
  Date: 2018/7/18
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession session1 = request.getSession() ;
    String name = null;
    if(session1 != null) {
        Map<String, String> account = (Map) session1.getAttribute("account");
        if(account != null) {
            for (String key : account.keySet())
                name = key;
        }
        else
            name = "无";
    }else{
        response.encodeRedirectURL("/login") ;
    }


%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>图书管理系统</title>
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .menu{
            width: 15%;
            height: 600px;
            float: left;
        }
        .menu2{
            height: 50px;
            width: 100%;
            color: #2A5FAB;
            font-size: 28px;
            font-family: 微软雅黑;
            font-weight: bold;

        }
        .content{
            width: 84%;
            height: 95%;
            float: right;
        }

    </style>

    </head>
<body>
<div>
    <div class="menu" >
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation" class="active" data-toggle="tab"><a onclick="choose0()">首页</a></li>
            <li role="presentation" data-toggle="tab"><a onclick="choose1()">查询用户 </a></li>
            <li role="presentation" data-toggle="tab"><a onclick="choose2()">查询书籍 </a></li>
            <li role="presentation" data-toggle="tab"><a onclick="choose3()">查询借书关系</a></li>
        </ul>
    </div>
    <div class="content">
        <div class="menu2">
        系统
            <div style="float: right">
                <span style="font-size: 18px;text-align: left"><%=name%></span>
                <a href="/logout" style="color: #FFFFFF;background-color: #2A5FAB" onclick="quitAccount()">退出</a>
            </div>
        </div>
        <iframe id="menu" width="95%" height="93%" frameborder="0" src="./Library"></iframe>
    </div>
</div>




<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    function choose0() {
        document.getElementById("menu").src = "./Library" ;
    }
    function choose1() {
        document.getElementById("menu").src = "./AllUser";
    }
    function choose2() {
        document.getElementById("menu").src = "./AllBook";
    }
    function choose3() {
        document.getElementById("menu").src = "./AllRelation"
    }


</script>

</body>
</html>

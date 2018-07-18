<%--
  Created by IntelliJ IDEA.
  User: Jiang
  Date: 2018/7/18
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>

    <style>
        .menu{
            width: 15%;
            height: 600px;
            float: left;
        }
        .menu2{
            height: 7%;
            width: 100%;
            color: #1176FF;
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
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/css/zui.min.css">
<!-- ZUI Javascript 依赖 jQuery -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/lib/jquery/jquery.js"></script>
<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/js/zui.min.js"></script>
<body>
<div>
    <div class="menu" >
        <ul class="nav nav-primary nav-stacked">
            <li class="active"><a onclick="choose0()">首页</a></li>
            <li><a onclick="choose1()">查询用户 </a></li>
            <li><a onclick="choose2()">查询书籍 </a></li>
            <li><a onclick="choose3()">查询借书关系</a></li>
        </ul>
    </div>
    <div class="content">
        <div class="menu2">
            图书管理系统
        </div>
        <iframe id="menu" width="95%" height="93%" frameborder="0" src="./Library"></iframe>
    </div>
</div>
<script>
    function choose0() {
        document.getElementById("menu").src = "./Library" ;
        $("li").removeClass("active");
        $("li:first").addClass("active");
    }
    function choose1() {
        document.getElementById("menu").src = "./AllUser";
        $("li").removeClass("active");
        $("li:first").addClass("active");
    }
    function choose2() {
        document.getElementById("menu").src = "./AllBook";
        $("li").removeClass("active");
        $("li:first").addClass("active");
    }
    function choose3() {
        document.getElementById("menu").src = "./AllRelation"
    }

</script>

</body>
</html>

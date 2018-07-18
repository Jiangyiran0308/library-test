<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\6\29 0029
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>LibrarySystem</title>
    <style>
        .adduserclass,.addbookclass,.addrelationclass{
            width: 33%;
            height: 500px;
            border:1px solid #ccc;
            float: left;
            box-sizing: border-box;

        }
        .inputclass{
            margin-right:20px;
            margin-left: 20px;
            margin-top:20px;
            margin-bottom:20px;
        }
        .systitle{
            text-align:center;
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

<div class="adduserclass">
    <div class="inputclass">
    添加用户
        <form id = "addUserInfo" method="get"><br>
            <input type="text" name="userName" class="form-control" placeholder="用户名">
            <br>
            <input type="text" name="userId" class="form-control" placeholder="ID"><br>
            <input type="submit" value="添加" class="btn">
        </form>

        <a href="./AllUser"><input type="button" value="查询" class="btn"></a>
    </div>
</div>
<div class="addbookclass">
    <div class="inputclass">
    添加书籍
        <form id = "addBookInfo" method="get"><br>
            <input type="text" name="bookName" class="form-control" placeholder="书名"><br>
            <input type="text" name="bookIsbn" class="form-control" placeholder="ISBN"><br>
            <input type="text" name="bookAuthor" class="form-control" placeholder="作者"><br>
            <input type="text" name="bookPages" class="form-control" placeholder="页数"><br>
            <input type="submit" value="添加" class="btn">
        </form>
        <a href="./AllBook"><input type="button" value="查询" class="btn"></a>
    </div>
</div>
<div class="addrelationclass">
    <div class="inputclass">
    添加借书关系
        <form id = "addRelationInfo" method="get"><br>
            <input type="text" name="relationUserId" class="form-control" placeholder="用户ID"><br>
            <input type="text" name="relationIsbn" class="form-control" placeholder="ISBN"><br>
            <input type="submit" value="添加" class="btn">
        </form>

        <a href="./AllRelation"><input type="button" value="查询" class="btn"></a>
    </div>
</div>
</div>
<script>

</script>
</body>
</html>
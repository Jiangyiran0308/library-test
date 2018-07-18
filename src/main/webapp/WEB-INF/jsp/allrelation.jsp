<%@ page import="com.web.Model.UserAllBook" %>
<%@ page import="com.web.Model.Libbook" %>
<%@ page import="com.web.Model.Libuser" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\7\7 0007
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/css/zui.min.css">
<!-- ZUI Javascript 依赖 jQuery -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/lib/jquery/jquery.js"></script>
<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/js/zui.min.js"></script>
<body>
<div class="container">
    <div>
        <form id = "searchRelation" method="get">
            请输入用户ID，查询用户借书情况<br>
            <input type="text" name="searchRelationUserId" class="form-control" placeholder="用户ID" style="width: 400px"><br>
            <input type="submit" value="查询" class="btn">
        </form>
            <table id="searchRelationInfo" class="table table-hover">
    </div>
</div>

</body>
<script>
    var userBook = {
        userID:'',
        userName:'',
        allbook:[]
    } ;
    searchUserBook();

    function searchUserBook() {

        var str = "<tr><td>ID</td><td>用户名</td><td>ISBN</td><td>书名</td><td>作者</td><td>页数</td><td>操作</td></tr>" ;
        <%
        UserAllBook userAllBook = (UserAllBook) request.getAttribute("searchrelation") ;
        if(userAllBook != null){%>
            userBook.userID = "<%=userAllBook.getUser().getId()%>" ;
            userBook.userName = "<%=userAllBook.getUser().getName()%>";
            <%
            if(userAllBook.getAllbook() != null ){
                for(Libbook book : userAllBook.getAllbook()){%>
                    userBook.allbook.push({isbn:"<%=book.getIsbn()%>",bookName:"<%=book.getName()%>",bookAuthor:"<%=book.getAuthor()%>",bookPages:"<%=String.valueOf(book.getPages())%>"}) ;
                <%}
            }
        }%>

        var usertable = userBook.allbook.length ;
        str += "<tr><td rowspan="+ usertable +">"+userBook.userID+"</td><td rowspan="+ usertable +">"+userBook.userName+"</td>"
            + "<td>"+userBook.allbook[0].isbn+"</td><td>"+userBook.allbook[0].bookName+"</td><td>"+userBook.allbook[0].bookAuthor+"</td><td>"+userBook.allbook[0].bookPages+"</td>" ;

        //str += "<td><input id='deleteone' type='text' class='btn' value='删除' style='width: 52px' onclick=deleteRelation(0)></td></tr>" ;
        str += "<td><a href='./AllRelation?deleterelation="+userBook.allbook[0].id+";"+userBook.allbook[0].isbn+"' type='button' class='btn' style='width: 52px'>删除</a></td></tr>" ;
        for(var i = 1 ; i<usertable ; i++ ){
            str+="<tr><td>"+userBook.allbook[i].isbn+"</td><td>"+userBook.allbook[i].bookName+"</td><td>"+userBook.allbook[i].bookAuthor+"</td><td>"+userBook.allbook[i].bookPages+"</td>";
            str += "<td><a href='./AllRelation?deleterelation="+userBook.allbook[i].id+";"+userBook.allbook[i].isbn+"' type='button' class='btn' style='width: 52px'>删除</a></td></tr>" ;
        }
        if(userBook.allbook.length > 0)
            document.getElementById("searchRelationInfo").innerHTML = str ;

    }

</script>

</html>

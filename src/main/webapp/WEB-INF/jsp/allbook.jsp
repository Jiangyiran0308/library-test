<%@ page import="com.web.Model.Libuser" %>
<%@ page import="java.util.List" %>
<%@ page import="com.web.Model.Libbook" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\7\5 0005
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllBook</title>
</head>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/css/zui.min.css">
<!-- ZUI Javascript 依赖 jQuery -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/lib/jquery/jquery.js"></script>
<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/js/zui.min.js"></script>
<body>
<div class="container">
    <div>
        <form id = "searchBook" method="get">
            请输入ISBN或书名，查询书籍信息<br>
            <input type="text" name="searchUserByIsbn" class="form-control" placeholder="ISBN" style="width: 400px"><br>
            <input type="text" name="searchUserByName" class="form-control" placeholder="书名" style="width: 400px"><br>
            <input type="submit" value="查询" class="btn">
        </form>
    </div>
    <div style="height: 200px">
        <table id="searchBookInfo" class="table table-hover"></table>
        <button type="button" class="btn" data-toggle="modal" data-target="#myModal">编辑</button>
        <form id = "deleteBookInfo" method="get">
            <input id = "deleteInfo" type="text" class="btn" name="deleteBookByIsbn" value="删除" onclick="deleteBook()" style="width: 52px">
        </form>
    </div>
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
                    <h4 class="modal-title">编辑</h4>
                </div>
                <div class="modal-body">
                    <form id = "updateBookInfo" method="get">
                        ISBN：<input id="bookisbn" name="newBookIsbn" type="text" class="form-control" placeholder="ISBN" readonly><br>
                        书名：<input id="bookname" name="newBookName" type="text" class="form-control" placeholder="书名"><br>
                        作者：<input id="bookauthor" name="newBookAuthor" type="text" class="form-control" placeholder="作者"><br>
                        页数：<input id="bookpage" name="newBookPage" type="text" class="form-control" placeholder="页数"><br>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateUser()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <div>所有用户信息如下:
        <table id="allBookInfo" class="table table-hover"></table>
    </div>
</div>
</body>
<script type="text/javascript">
    var allBookInfo = [] ;
    var bookInfo ;
    getAllBook() ;
    searchBook() ;



    function getAllBook() {
        var str = "<tr><td>ISBN</td><td>书名</td><td>作者</td><td>页数</td></tr>" ;
        <%
        List<Libbook> allBook = (List<Libbook>) request.getAttribute("allbook");
        if(allBook .size() > 0){
            for (Libbook anAllBook : allBook) {%>
        allBookInfo.push({isbn:"<%=anAllBook.getIsbn()%>",name:"<%=anAllBook.getName()%>",author:"<%=anAllBook.getAuthor()%>",pages:"<%=String.valueOf(anAllBook.getPages())%>"}) ;
        <%}
    }%>
        for(var i = 0 ; i < allBookInfo.length ; i ++ ){
            str+= "<tr><td>"+allBookInfo[i].isbn+"</td><td>"+allBookInfo[i].name+"</td><td>"+allBookInfo[i].author+"</td><td>"+allBookInfo[i].pages+"</td></tr>"
        }
        if(allBookInfo.length>0)
            document.getElementById("allBookInfo").innerHTML = str ;
    }

    function searchBook() {
        var str1 = "<tr><td>ISBN</td><td>书名</td><td>作者</td><td>页数</td></tr>" ;
        <%
        Libbook book = (Libbook) request.getAttribute("searchbook") ;
        if(book != null)
        {%>
        bookInfo = {isbn:"<%=book.getIsbn()%>",name:"<%=book.getName()%>",author:"<%=book.getAuthor()%>",pages:"<%=String.valueOf(book.getPages())%>"};
        <%}
        %>
        if(bookInfo!= null){
            if(bookInfo.isbn !==null &&bookInfo.name !== null && bookInfo.author!== null && bookInfo.pages !== null) {
                str1+="<tr><td>"+bookInfo.isbn+"</td><td>"+bookInfo.name+"</td><td>"+bookInfo.author+"</td><td>"+bookInfo.pages+"</td></tr>";
                document.getElementById("bookisbn").value = bookInfo.isbn;
                document.getElementById("bookname").value = bookInfo.name;
                document.getElementById("bookauthor").value = bookInfo.author ;
                document.getElementById("bookpage").value = bookInfo.pages ;
            }else
                str1 = "<tr><td>请输入正确的用户信息！</td></tr>";
        }else
            str1 = "<tr><td>请输入正确的用户信息！</td></tr>";
        document.getElementById("searchBookInfo").innerHTML = str1 ;
    }

    function deleteBook() {
        f = document.getElementById("deleteBookInfo") ;
        info = document.getElementById("deleteInfo");
        if(bookInfo != null){
            if(bookInfo.isbn != null)
            {
                info.value = bookInfo.isbn ;
                f.submit() ;
            }
        }
        info.value = "删除" ;

    }

    function updateUser() {
        f = document.getElementById("updateBookInfo");
        f.submit();
    }



</script>
</html>

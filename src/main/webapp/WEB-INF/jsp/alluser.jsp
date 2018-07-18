
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\7\4 0004
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.web.Model.Libuser" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>allUser</title>
</head>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/css/zui.min.css">
<!-- ZUI Javascript 依赖 jQuery -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/lib/jquery/jquery.js"></script>
<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
<script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.8.1/js/zui.min.js"></script>
<body>
<div class="container">
    <div>
        <form id = "searchUser" method="get">
            请输入ID或用户名，查询用户<br>
            <input type="text" name="searchUserById" class="form-control" placeholder="ID" style="width: 400px"><br>
            <input type="text" name="searchUserByName" class="form-control" placeholder="用户名" style="width: 400px"><br>
            <input type="submit" value="查询" class="btn" >
        </form>
    </div>
    <div style="height: 200px">
        <table id="searchUserInfo" class="table table-hover"></table>
        <button type="button" class="btn" data-toggle="modal" data-target="#myModal">编辑</button>
        <form id = "deleteUserInfo" method="get">
            <input id = "deleteInfo" type="text" class="btn" name="deleteUserById" value="删除" onclick="deleteUser()" style="width: 52px">
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
                    <form id = "updateUserInfo" method="get">
                        ID：<input id="userid" name="newUserId" type="text" class="form-control" placeholder="ID" readonly><br>
                        用户名：<input id="username" name="newUserName" type="text" class="form-control" placeholder="用户名">
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
        <table id = "allUserInfo" class="table table-hover"></table>
    </div>
</div>
</body>

<script type="text/javascript">

    var allUserInfo = [] ;
    var userInfo ;
    getAllUser();
    searchUser();



    function getAllUser() {

        var str = "<tr><td>ID</td><td>用户名</td></tr>" ;
        <%
        List<Libuser> allUser = (List<Libuser>) request.getAttribute("alluser");
            if(allUser != null ){
                for (Libuser anAllUser : allUser) { %>
        allUserInfo.push({id: "<%= anAllUser.getId()%>",name:"<%= anAllUser.getName() %>" });
        <%}
            }%>
        for(var i = 0 ; i < allUserInfo.length ; i++ ){
            str += "<tr><td>"+allUserInfo[i].id+"</td><td>"+allUserInfo[i].name+"</td></tr>" ;
        }
        if(allUserInfo.length > 0 )
            document.getElementById("allUserInfo").innerHTML = str ;

    }

    function searchUser() {
        
        var str1 = "<tr><td>ID</td><td>用户名</td></tr>" ;
        <%
        Libuser user = (Libuser) request.getAttribute("searchuser") ;
        if(user != null)
        {%>
        userInfo={id:"<%=user.getId()%>",name:"<%=user.getName()%>"};<%
        }%>
        if(userInfo != null ) {
            str1 += "<tr><td>" + userInfo.id + "</td><td>" + userInfo.name + "</td></tr>";
            if(userInfo.id !== null && userInfo.name !== null) {
                document.getElementById("userid").value = userInfo.id ;
                document.getElementById("username").value = userInfo.name ;
                //document.getElementById("deleteInfo").value = userInfo.id ;
            }
        }else {
            str1 = "<tr><td>请输入正确的用户信息！</td></tr>";
        }
        document.getElementById("searchUserInfo").innerHTML = str1 ;

    }
    
    function deleteUser() {
        f = document.getElementById("deleteUserInfo") ;
        info = document.getElementById("deleteInfo") ;
        if(userInfo!= null) {
            if(userInfo.id != null) {
                info.value = userInfo.id;
                f.submit();
            }
        }
        info.value = "删除" ;
    }
    
    function updateUser() {
        f = document.getElementById("updateUserInfo") ;
        f.submit();
    }

</script>

</html>

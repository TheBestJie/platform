<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
    <script src="easyui/jquery.min.js"></script>
    <script src="easyui/jquery.easyui.min.js"></script>
    <script src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <style>
        li{
            list-style: none;
        }
    </style>
    <script>
        //登录事件
        function toLogin() {
            //提交异步表单
            $("#loginForm").form('submit',{
                url:'login.do'
                ,success:function (msg){
                    if(msg === '1'){
                        $.messager.alert('提示','登录成功','info',function (){
                            //登录成功，跳转页面
                            location.href="go_main.htm";
                        });
                    }else {
                        $.messager.alert('提示','账号或密码错误','error');
                    }
                }
            });
        }
    </script>
</head>
<body>
    <div id="loginBox" class="easyui-dialog" data-options="title:'用户登录',width:400,buttons:'#btn1',draggable:false,closable:false">
        <form id="loginForm" method="post">
            <ul>
                <li><input id="yhm" name="yhm" class="easyui-textbox" data-options="width:300,height:30,label:'用户名:',labelAlign:'left'"></li>
                <br>
                <li><input id="yhmm" name="yhmm" class="easyui-passwordbox" data-options="width:300,height:30,label:'密码:',labelAlign:'left'"></li>
                <br>
            </ul>
        </form>
    </div>
    <div>
        <a id="btn1" class="easyui-linkbutton" data-options="onClick:toLogin" href="javascript:;">登录</a>
    </div>
</body>
</html>
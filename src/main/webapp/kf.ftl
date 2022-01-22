<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "comm.ftl">
        <style>
            li{
                list-style:none;
                margin-top:8px;
            }
        </style>
        <script>
            function toAdd() {
                $("#kfAddDlg").dialog("open");
            }
            
            function toSave() {
                $("#kfAddForm").form('submit',{
                    url:'kfSave.do'
                    ,success:function () {
                        $.messager.show({
                            title: '提示'
                            ,msg: '添加成功'
                        });
                        $("#kfAddDlg").dialog("close");
                        $("#kfAddForm").form("clear");
                        $("#kfGrid").datagrid("reload");
                    }
                });
            }
            
            function toEdit() {
                var row = $("#kfGrid").datagrid("getSelected");
                if(row == null){
                    $.messager.alert('提示','请选择一条数据进行操作','error');
                    return ;
                }
                $.post('kfEdit.do',{kfbh:row.kfbh},function (kf) {
                    $("#kfbh2").val(kf.kfbh);
                    $("#kfmc2").textbox('setValue',kf.kfmc);
                    $("#kfwz2").textbox('setValue',kf.kfwz);
                    $("#kfEditDlg").dialog("open");
                },'json');
            }
            
            function toUpdate() {
                $("#kfEditForm").form('submit',{
                   url:'kfUpdate.do'
                   ,success: function (){
                       $.messager.show({
                           title: '提示'
                           ,msg: '编辑成功'
                       });
                       $("#kfEditDlg").dialog("close");
                       $("#kfEditForm").form("clear");
                       $("#kfGrid").datagrid("reload");
                    }
                });
            }
            
            function toDelete() {
                var row = $("#kfGrid").datagrid("getSelected");
                if(row == null){
                    $.messager.alert('提示','请选择一条数据进行操作','error');
                    return ;
                }
                $.messager.confirm('提示','是否删除',function (f) {
                    if(f === true){
                        $.post('kfDelete.do',{kfbh:row.kfbh},function (){
                            $.messager.show({
                                title:'提示'
                                ,msg:'删除成功'
                            });
                            $("#kfGrid").datagrid("reload");
                        });
                    }
                });
            }
            
            function toSelectFile() {
                $('#kfImportDlg').dialog("open");
            }
            
            function toImport() {
                $("#kfImportForm").form('submit',{
                    url:'kfImport.do'
                    ,success:function () {
                        $.messager.show({
                            title:'提示'
                            ,msg:'导入成功'
                        });
                        $("#kfImportDlg").dialog("close");
                        $("#kfImportForm").form("clear");
                        $("#kfGrid").datagrid("reload");
                    }
                });
            }
            
            function toExport() {
                location.href = "kfExport.do";
            }
        </script>
    </head>
    <body>
    <table id="kfGrid" class="easyui-datagrid" data-options="title:'商品列表',fit:true,fitColumns:true,pagination:true,toolbar:'#kfGrid-buttons',singleSelect:true,url:'kfList.do'">
            <thead>
                <tr>
                    <th data-options="field:'kfbh',width:80,align:'center'">库房编号</th>
                    <th data-options="field:'kfmc',width:80,align:'center'">库房名称</th>
                    <th data-options="field:'kfwz',width:80,align:'center'">库房位置</th>
                </tr>
            </thead>
        </table>
        <div id="kfGrid-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true,onClick:toAdd">新建</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true,onClick:toEdit">编辑</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true,onClick:toDelete">删除</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-import',plain:true,onClick:toSelectFile">批量导入</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-export',plain:true,onClick:toExport">批量导出</a>
        </div>
    
        <!--批量导入窗口-->
        <div id="kfImportDlg" class="easyui-dialog" data-options="title:'批量导入',width:400,modal:true,closed:true,buttons:'#kfimportdlg-buttons'">
            <form id="kfImportForm" method="post" enctype="multipart/form-data">
                <ul>
                    <li><input class="easyui-filebox" name="excel" data-options="width:300,height:30,label:'选择文件',required:true" /></li>
                    <!--                    <li><a class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true,onClick:toDownTemplate" >下载模板</a></li>-->
                </ul>
            </form>
        </div>
        <div id="kfimportdlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toImport">保存</a>
        </div>
        <!--新增窗口-->
        <div id="kfAddDlg" class="easyui-dialog" data-options="title:'添加操作',width:400,model:true,closed:true,buttons:'#kfAddDlg-buttons'">
            <form id="kfAddForm" method="post">
                <ul>
                    <li><input id="kfmc" class="easyui-textbox" name="kf.kfmc" data-options="width:300,height:30,label:'库房名称',required:true"></li>
                    <li><input id="kfwz" class="easyui-textbox" name="kf.kfwz" data-options="width:300,height:30,label:'库房位置',required:true"></li>
                </ul>
            </form>
        </div>
        <div id="kfAddDlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toSave">保存</a>
        </div>
        <!--修改窗口-->
        <div id="kfEditDlg" class="easyui-dialog" data-options="title:'修改操作',width:400,model:true,closed:true,buttons:'#kfEditFlg-buttons'">
            <form id="kfEditForm" method="post">
                <input type="hidden" id="kfbh2" name="kf.kfbh">
                <ul>
                    <li><input id="kfmc2" class="easyui-textbox" name="kf.kfmc" data-options="width:300,height:30,label:'供应商名称',required:true"></li>
                    <li><input id="kfwz2" class="easyui-textbox" name="kf.kfwz" data-options="width:300,height:30,label:'联系人',required:true"></li>
                </ul>
            </form>
        </div>
        <div id="kfEditFlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toUpdate">保存</a>
        </div>
    </body>
</html>
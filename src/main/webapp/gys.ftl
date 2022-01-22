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

            //显示添加窗口
            function toAdd() {
                $("#gysAddDlg").dialog("open");
            }

            //添加操作
            function toSave() {
                $("#gysAddForm").form('submit',{
                    url:'gysSave.do'
                    ,success:function () {
                        $.messager.show({
                            title:'提示'
                            ,msg:'添加成功'
                        });

                        $("#gysAddDlg").dialog("close");
                        $("#gysAddForm").form("clear");
                        $("#gysGrid").datagrid("reload");
                    }
                });
            }

            //显示修改窗口
            function toEdit() {
                //获取选中表格的数据
                var row = $("#gysGrid").datagrid("getSelected");
                //未选中判断
                if(row == null){
                    $.messager.alert('提示','请选择一条数据进行操作','error');
                    return ;
                }
                $.post('gysEdit.do',{gysbh:row.gysbh},function (gys) {
                    $("#gysbh2").val(gys.gysbh);
                    $("#gysmc2").textbox('setValue',gys.gysmc);
                    $("#lxr2").textbox('setValue',gys.lxr);
                    $("#lxdh2").textbox('setValue',gys.lxdh);
                    $("#dz2").textbox('setValue',gys.dz);
                    //打开窗口
                    $("#gysEditDlg").dialog("open");
                },'json');
            }

            //修改操作
            function toUpdate() {
                $("#gysEditForm").form('submit',{
                    url:'gysUpdate.do'
                    ,success:function () {
                        $.messager.show({
                            title:'提示'
                            ,msg:'修改成功'
                        });

                        $("#gysEditDlg").dialog("close");
                        $("#gysEditForm").form("clear");
                        $("#gysGrid").datagrid("reload");
                    }
                });
            }

            //删除操作
            function toDelete() {
                var row = $("#gysGrid").datagrid("getSelected");
                if(row == null){
                    $.messager.alert('提示','请选择一条数据进行操作','error');
                    return ;
                }
                $.messager.confirm('提示','是否删除',function (f) {
                    if(f === true){
                        $.post('gysDelete.do',{gysbh:row.gysbh},function (){
                            $.messager.show({
                                title:'提示'
                                ,msg:'删除成功'
                            });
                            $("#gysGrid").datagrid("reload");
                        });
                    }
                });

            }


            //批量导入窗口
            function toSelectFile() {
                $('#gysImportDlg').dialog('open');
            }

            //导入操作
            function toImport() {
                $('#gysImportForm').form('submit',{
                    url:'gysImport.do',
                    success:function(){
                        $.messager.show({
                            title:'提示',
                            msg:'导入成功'
                        });

                        $('#gysImportDlg').dialog('close');
                        $('#gysImportForm').form('clear');
                        $('#gysGrid').datagrid('reload') ;

                    }
                });
            }

            //导出操作
            function toExport() {
                location.href = 'gysExport.do';
            }
        </script>
    </head>
    <body>
        <table id="gysGrid" class="easyui-datagrid" data-options="title:'供应商列表',singleSelect:true,fit:true,fitColumns:true,url:'gysList.do',pagination:true,toolbar:'#gysgrid-buttons'">
            <thead>
                <tr>
                    <th data-options="field:'gysbh',width:80,align:'center'">供应商编号</th>
                    <th data-options="field:'gysmc',width:100,align:'center'">供应商名称</th>
                    <th data-options="field:'lxr',width:100,align:'center'">联系人</th>
                    <th data-options="field:'lxdh',width:100,align:'center'">联系电话</th>
                    <th data-options="field:'dz',width:120,align:'center'">地址</th>
                </tr>
            </thead>
        </table>

        <div id="gysgrid-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true,onClick:toAdd">新建</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true,onClick:toEdit">编辑</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true,onClick:toDelete">删除</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-import',plain:true,onClick:toSelectFile">批量导入</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-export',plain:true,onClick:toExport">批量导出</a>
        </div>

        <!-- 批量导入 -->
        <div id="gysImportDlg" class="easyui-dialog" data-options="title:'批量导入',width:400,modal:true,closed:true,buttons:'#gysimportdlg-buttons'">
            <form id="gysImportForm" method="post" enctype="multipart/form-data">
                <ul>
                    <li><input class="easyui-filebox" name="excel" data-options="width:300,height:30,label:'选择文件',required:true" /></li>
<!--                    <li><a class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true,onClick:toDownTemplate" >下载模板</a></li>-->
                </ul>
            </form>
        </div>
        <div id="gysimportdlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toImport">保存</a>
        </div>

        <!--添加窗口-->
        <div id="gysAddDlg" class="easyui-dialog" data-options="title:'添加操作',width:400,model:true,closed:true,buttons:'#gysAddDlg-buttons'">
            <form id="gysAddForm" method="post">
                <ul>
                    <li><input id="gysmc" class="easyui-textbox" name="gys.gysmc" data-options="width:300,height:30,label:'供应商名称',required:true"></li>
                    <li><input id="lxr" class="easyui-textbox" name="gys.lxr" data-options="width:300,height:30,label:'联系人',required:true"></li>
                    <li><input id="lxdh" class="easyui-textbox" name="gys.lxdh" data-options="width:300,height:30,label:'联系电话',required:true"></li>
                    <li><input id="dz" class="easyui-textbox" name="gys.dz" data-options="width:300,height:30,label:'地址',required:true"></li>
                </ul>
            </form>
        </div>
        <div id="gysAddDlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toSave">保存</a>
        </div>

        <!--修改操作-->
        <div id="gysEditDlg" class="easyui-dialog" data-options="title:'添加操作',width:400,model:true,closed:true,buttons:'#gysEditFlg-buttons'">
            <form id="gysEditForm" method="post">
                <input type="hidden" id="gysbh2" name="gys.gysbh">
                <ul>
                    <li><input id="gysmc2" class="easyui-textbox" name="gys.gysmc" data-options="width:300,height:30,label:'供应商名称',required:true"></li>
                    <li><input id="lxr2" class="easyui-textbox" name="gys.lxr" data-options="width:300,height:30,label:'联系人',required:true"></li>
                    <li><input id="lxdh2" class="easyui-textbox" name="gys.lxdh" data-options="width:300,height:30,label:'联系电话',required:true"></li>
                    <li><input id="dz2" class="easyui-textbox" name="gys.dz" data-options="width:300,height:30,label:'地址',required:true"></li>
                </ul>
            </form>
        </div>
        <div id="gysEditFlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toUpdate">保存</a>
        </div>
    </body>
</html>
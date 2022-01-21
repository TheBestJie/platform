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
            function toSelectFile() {
                $('#gysImportDlg').dialog('open');
            }
            
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
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
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
    </body>
</html>
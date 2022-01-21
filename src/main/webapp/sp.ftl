<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "comm.ftl">
        <script>
            //添加按钮点击事件
            function toAdd() {
                //显示添加窗口
                $("#spAddDialog").dialog('open');
            }

            //删除按钮点击事件
            function toDelete() {
                //删除选中的行
                var row = $("#spgrid").datagrid('getSelected');
                //未选择判断
                if(row === null){
                    $.messager.alert('提示','请选中要删除的记录','error');
                    return ;
                }
                //提示是否删除
                $.messager.confirm('提示','是否删除？',function (f) {
                    if(f === true){
                        $.post('spDelete.do',{'spbh':row.spbh},function (){
                            //删除成功提示
                            $.messager.show({
                                title:'提示'
                                ,msg:'删除成功'
                            });
                            //刷新表单
                            $("#spgrid").datagrid("reload");
                        });
                    }
                });
            }

            //编辑按钮点击事件
            function toEdit() {
                //获取选中行的数据
                var row = $("#spgrid").datagrid("getSelected");
                //未选中判断
                if(row === null){
                    $.messager.alert('提示','请选中要修改的记录','error');
                    return ;
                }
                //从数据库中获取选中行数据
                $.post('spEdit.do',{'spbh':row.spbh},function (sp){
                    $("#spbh2").val(sp.spbh);
                    $("#spmc2").textbox('setValue',sp.spmc);
                    $("#zjm2").textbox('setValue',sp.zjm);
                    $("#spdw2").textbox('setValue',sp.spdw);
                    $("#spgg2").textbox('setValue',sp.spgg);
                    $("#spjg2").textbox('setValue',sp.spjg);
                    $("#zzs2").textbox('setValue',sp.zzs);
                    // 显示修改窗口
                    $("#spEditDialog").dialog('open');
                },'json');

            }

            /**
             * 添加的操作
             */
            function toSave() {
                $("#spAddForm").form('submit',{
                    url:'spSave.do'
                    ,success:function () {
                        //自动提示框，走屏幕右下角提示后自动关闭
                        $.messager.show({
                            title:'提示'
                            ,msg:'保存成功'
                        });
                        //隐藏添加窗口
                        $("#spAddDialog").dialog('close');
                        //清空form表单
                        $("#spAddForm").form('clear');
                        //刷新表格
                        $("#spgrid").datagrid("reload");
                    }
                });
            }


            //修改操作
            function toUpdate() {
                $("#spEditForm").form('submit',{
                   url:'spUpdate.do'
                   ,success:function (){
                       //成功提示窗口
                       $.messager.show({title:'提示',msg:'修改成功'});
                       //隐藏修改窗口
                       $("#spEditDialog").dialog('close');
                       //清空form表单
                       $("#spEditForm").form('clear');
                       //刷新表格
                       $("#spgrid").datagrid('reload');
                    }
                });
            }

            //批量导入
            function toSelectFile() {
                $("#spImportDlg").dialog("open");
            }

            //批量导入操作
            function toImport() {
                $('#spImportForm').form('submit',{
                    url:'spImport.do',
                    success:function(){
                        $.messager.show({
                            title:'提示',
                            msg:'导入成功'
                        });
                        $('#spImportDlg').dialog('close');
                        $('#spImportForm').form('clear');
                        $('#spgrid').datagrid('reload') ;
                    }
                });
            }

            //导出操作
            function toExport() {
                location.href = 'spExport.do';
            }
        </script>
    </head>
    <body>
        <table id="spgrid" class="easyui-datagrid" data-options="title:'商品列表',fit:true,fitColumns:true,pagination:true,toolbar:'#spgrid-buttons',singleSelect:true,url:'spList.do'">
            <thead>
                <tr>
                    <th data-options="field:'spbh',width:80,align:'center'">商品编号</th>
                    <th data-options="field:'spmc',width:100,align:'center'">商品名称</th>
                    <th data-options="field:'zjm',width:100,align:'center'">助记码</th>
                    <th data-options="field:'spdw',width:100,align:'center'">商品单位</th>
                    <th data-options="field:'spgg',width:100,align:'center'">商品规格</th>
                    <th data-options="field:'spjg',width:100,align:'center'">商品价格</th>
                    <th data-options="field:'zzs',width:100,align:'center'">制造商</th>
                </tr>
            </thead>
        </table>

        <div id="spgrid-buttons">
            <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add',onClick:toAdd">新建</a>
            <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove',onClick:toDelete">删除</a>
            <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit',onClick:toEdit">编辑</a>
            <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-import',onClick:toSelectFile">批量导入</a>
            <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export',onClick:toExport">批量导出</a>
        </div>

        <!--新建对话框-->
        <div id="spAddDialog" class="easyui-dialog" data-options="title:'新增',width:400,draggable:false,closed:true,modal:true,buttons:'#spadddlg-buttons'">
            <form method="post" id="spAddForm">
                <ul>
                    <li><input id="spmc" name="sp.spmc" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'商品名称',"></li>
                    <li>
                        <select id="spdw" name="sp.spdw" class="easyui-combobox" data-options="width:300,height:30,required:true,label:'商品名称'">
                            <option value="台">台</option>
                            <option value="个">个</option>
                            <option value="盏">盏</option>
                            <option value="部">部</option>
                            <option value="套">套</option>
                        </select>
                    </li>
                    <li><input id="spgg" name="sp.spgg" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'商品规格'"></li>
                    <li><input id="spjg" name="sp.spjg" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'商品价格'"></li>
                    <li><input id="zzs" name="sp.zzs" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'制造商'"></li>
                </ul>
            </form>
        </div>

        <div id="spadddlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toSave">提交</a>
        </div>

        <!-- 编辑商品 -->
        <div id="spEditDialog" class="easyui-dialog" data-options="title:'编辑',width:400,draggable:false,closed:true,modal:true,buttons:'#spEditdlg-buttons'">
            <form method="post" id="spEditForm">
                <input type="hidden" id="spbh2" name="sp.spbh" />
                <ul>
                    <li><input id="spmc2" name="sp.spmc" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'商品名称',"></li>
                    <li>
                        <select id="spdw2" name="sp.spdw" class="easyui-combobox" data-options="width:300,height:30,required:true,label:'商品名称'">
                            <option value="台">台</option>
                            <option value="个">个</option>
                            <option value="盏">盏</option>
                            <option value="部">部</option>
                            <option value="套">套</option>
                        </select>
                    </li>
                    <li><input id="spgg2" name="sp.spgg" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'商品规格'"></li>
                    <li><input id="spjg2" name="sp.spjg" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'商品价格'"></li>
                    <li><input id="zzs2" name="sp.zzs" class="easyui-textbox" data-options="width:300,height:30,required:true,label:'制造商'"></li>
                </ul>
            </form>
        </div>
        <div id="spEditdlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toUpdate">提交</a>
        </div>

        <!--批量导入-->
        <div id="spImportDlg" class="easyui-dialog" data-options="title:'批量导入',width:400,modal:true,closed:true,buttons:'#spimportdlg-buttons'">
            <form id="spImportForm" method="post" enctype="multipart/form-data">
                <ul>
                    <li><input class="easyui-filebox" name="excel" data-options="width:300,height:30,label:'选择文件',required:true" /></li>
                </ul>
            </form>
        </div>
        <div id="spimportdlg-buttons">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toImport">保存</a>
        </div>
    </body>
</html>
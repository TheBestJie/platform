<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "comm.ftl">
        <style>
            li{
                list-style:none;
                float: left;
                margin-left:4px;
            }
        </style>
        <script>

            //从服务器获取当前时间
            $(function () {
                $.get('currentTime.do',{},function (data) {
                    $("#jhsj").textbox('setValue',data);
                });
            });
            
            function toSelectGys() {
                $("#gysSelectDlg").dialog("open");
            }

            //gys表双击事件
            function selectGys(index,row) {
                $("#gys").textbox('setValue',row.gysmc);
                $("#gysbh").val(row.gysbh);
                $("#gysSelectDlg").dialog("close");
            }

            //将kf表格状态设置为显示
            function toSelectKf() {
                $("#kfSelectDlg").dialog("open");
            }

            //kf表双击事件
            function selectKf(index,row) {
                $("#kf").textbox('setValue',row.kfmc);
                $("#kfbh").val(row.kfbh);
                $("#kfSelectDlg").dialog("close");
            }

            //监听输入框的回车事件
            $(function () {
                $("#spSelectBtn").textbox('textbox').bind('keydown',function (ev) {
                    var e = window.event || ev;
                    //回车监听
                    if(e.keyCode === 13){
                        var spmc = $("#spSelectBtn").textbox('getValue');
                        if(spmc === '') return ;
                        //获取表格数据
                        $.post('spListByMc.do',{'spmc':spmc},function (spList) {
                            //加载本地数据，同时移除旧数据
                            $("#spGrid").datagrid('loadData',spList);
                        },'json');
                    }
                });
            })

            //sp表格双击函数
            function selectSp(index,row) {
                //判断数据是否重复
                var rows = $("#spxqGrid").datagrid('getRows');
                for(var i=0;i<rows.length;i++){
                    if(row.spbh === rows[i].spbh){
                        $.messager.alert('提示','不能重复选择相同的商品','error');
                        return ;
                    }
                }
                //追加表格数据
                $("#spxqGrid").datagrid('appendRow',row);
            }

            //单元格重置方法
            function changeSpjg(value,row,index) {
                return '<input style="width: 80px;" class="spjg" type="number">';
            }

            function chengeSpsl(value,row,index) {
                return '<input style="width: 80px;" class="spsl" type="number">';
            }

            //商品详情双击函数
            function selectSpxq(index,row) {
                if(!$('.spsl').eq(index).attr('disabled')){
                    $.messager.alert('提示','请勿重复操作','error');
                    return ;
                }
                $('.spsl').eq(index).prop('disabled',false);
                $('.spjg').eq(index).prop('disabled',false);

                var zj = $('#jhzj').textbox('getValue');
                var spjg = $('.spjg').eq(index).val();
                var spsl = $('.spsl').eq(index).val();
                spsl = parseInt(spsl);
                spjg = parseInt(spjg);
                zj -= spsl * spjg;
                $('#jhzj').textbox('setValue',zj);
            }

            //总计操作
            function zj() {
                var zj = 0;
                $('.spsl').each(function (i,input) {
                    var spsl = $(input).val();
                    var spjg = $('.spjg').eq(i).val();
                    spsl = parseInt(spsl);
                    spjg = parseInt(spjg);
                    zj += spsl * spjg;
                    //将已经计算过的商品置灰
                    $(input).prop('disabled',true);
                    $('.spjg').eq(i).prop('disabled',true);
                })
                $('#jhzj').textbox('setValue',zj);
            }

            function del() {
                //获取所有被选中行
                var rows = $('#spxqGrid').datagrid('getSelections');
                // 未选中行判断
                if(rows.length === 0){
                    $.messager.alert('提示','请选中数据后操作','error');
                    return ;
                }
                $.messager.confirm('提示','是否删除' + rows.length + '行数据?',function (f) {
                    if(f === true){
                        //获取当前中进货总价
                        var jhzj = $('#jhzj').textbox('getValue');
                        // 循环删除选中行金额，并将选中行删除
                        for(var i=0;i<rows.length;i++){
                            var index = $('#spxqGrid').datagrid('getRowIndex',rows[i]);
                            var spjg = $('.spjg').eq(index).val();
                            var spsl = $('.spsl').eq(index).val();
                            spsl = parseInt(spsl);
                            spjg = parseInt(spjg);
                            jhzj -= spjg * spsl ;
                            $("#spxqGrid").datagrid('deleteRow',index);
                        }
                        //将最终总价写入
                        $('#jhzj').textbox('setValue',jhzj);
                    }
                });
            }
            
            function toSaveJhd() {
                var jhzj = $('#jhzj').textbox('getValue');
                if(jhzj <= 0){
                    $.messager.alert('提示','请选择商品','error');
                    return ;
                }
                if($('.spsl:enabled').length > 0){
                    $.messager.alert('提示','有未确认的商品','error');
                    return ;
                }
                //表单处理
                var rows = $('#spxqGrid').datagrid('getRows')
                ,spbhs = ""
                ,spsls = ""
                ,spjgs = "";
                for(var i=0;i<rows.length;i++){
                    var row = rows[i];
                    var spbh = row.spbh
                    ,spsl = $('.spsl').eq(i).val()
                    ,spjg = $('.spjg').eq(i).val();
                    spbhs += spbh + ",";
                    spsls += spsl + ',';
                    spjgs += spjg + ",";
                }
                $('#spbhs').val(spbhs);
                $('#spsls').val(spsls);
                $('#spjgs').val(spjgs);
                //表单提交
                if(!$('#jhdAddForm').form('validate')){
                    $.messager.alert('提示','请填写完整的进货单信息','error');
                    return ;
                }
                $.messager.confirm('提示','是否提交?',function (f) {
                    if(f === true){
                        $.messager.alert('提示','提交成功','info');
                        $('#jhdAddForm').submit() ;
                    }
                })
            }
        </script>
    </head>
    <body>
        <div class="easyui-layout" data-options="fit:true">
            <!--基本信息-->
            <div data-options="title:'进货单基本信息',region:'north',height:120">
                <form id="jhdAddForm" method="post" action="jhdSave.do">
                    <input type="hidden" id="spbhs" name="spbhs">
                    <input type="hidden" id="spjgs" name="spjgs">
                    <input type="hidden" id="spsls" name="spsls">
                    <ul>
                        <li><input id="jhsj" name="jhd.jhsj" class="easyui-textbox" data-options="width:250,height:30,editable:false,label:'进货时间'"></li>
                        <li>
                            <input id="jhr" name="jhr" class="easyui-textbox" data-options="width:250,height:30,editable:false,label:'进货人',value:'${Session.loginYh.yhm}'">
                            <input id="jhrbh" type="hidden" name="jhd.jhrbh" value="${Session.loginYh.yhbh}">
                        </li>
                        <li>
                            <input id="gys" name="gys" class="easyui-textbox" data-options="width:250,height:30,editable:false,required:true,label:'供应商',icons:[{iconCls:'icon-search',handler:toSelectGys}]">
                            <input type="hidden" id="gysbh" name="jhd.gysbh">
                        </li>
                        <li>
                            <input id="kf" name="kf" class="easyui-textbox" data-options="width:250,height:30,editable:false,required:true,label:'库房',icons:[{iconCls:'icon-search',handler:toSelectKf}]">
                            <input type="hidden" id="kfbh" name="jhd.kfbh">
                        </li>
                        <li>
                            <input id="jhzj" name="jhd.jhzj" class="easyui-numberbox" data-options="width:250,height:30,editable:false,label:'总价',value:'0'">
                        </li>
                    </ul>
                </form>


                <!--供应商选择窗口-->
                <div id="gysSelectDlg" class="easyui-dialog" data-options="title:'选择供应商',width:400,height:400,modal:true,closed:true">
                    <table id="gysCrid" class="easyui-datagrid" data-options="singleSelect:true,fit:true,fitColumns:true,url:'gysList.do',pagination:true,onDblClickRow:selectGys">
                        <thead>
                        <tr>
                            <th data-options="field:'gysbh',width:80,align:'center'">供应商编号</th>
                            <th data-options="field:'gysmc',width:100,align:'center'">供应商名称</th>
                            <th data-options="field:'dz',width:120,align:'center'">地址</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <!--库房选择-->
            <div id="kfSelectDlg" class="easyui-dialog" data-options="title:'选择库房',width:400,height:400,model:true,closed:true">
                <table id="kfGrid" class="easyui-datagrid" data-options="title:'商品列表',fit:true,fitColumns:true,pagination:true,singleSelect:true,url:'kfList.do',onDblClickRow:selectKf">
                    <thead>
                    <tr>
                        <th data-options="field:'kfbh',width:80,align:'center'">库房编号</th>
                        <th data-options="field:'kfmc',width:80,align:'center'">库房名称</th>
                        <th data-options="field:'kfwz',width:80,align:'center'">库房位置</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <!--========================================-->
            <!--商品选择-->
            <div data-options="region:'center'">
                <table id="spxqGrid" class="easyui-datagrid" data-options="title:'进货详情',fit:true,fitColumns:true,onDblClickRow:selectSpxq,toolbar:'#spxqGrid-buttons'">
                    <thead>
                        <tr>
                            <th data-options="field:'spbh',width:60,align:'center'">编号</th>
                            <th data-options="field:'spmc',width:100,align:'center'">名称</th>
                            <th data-options="field:'spgg',width:100,align:'center'">规格</th>
                            <th data-options="field:'spjg',width:100,align:'center',formatter:changeSpjg">价格</th>
                            <th data-options="field:'spsl',width:100,align:'center',formatter:chengeSpsl">数量</th>
                        </tr>
                    </thead>
                </table>
                <div id="spxqGrid-buttons">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true,onClick:zj">确定</a>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true,onClick:del">删除</a>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true,onClick:toSaveJhd">提交</a>
                </div>
            </div>

            <!--已选择商品清单-->
            <div data-options="region:'east',width:300">
                <table id="spGrid" class="easyui-datagrid" data-options="title:'选择商品',fit:true,singleSelect:true,toolbar:'#spgrid-buttons',onDblClickRow:selectSp">
                    <thead>
                        <tr>
                            <th data-options="field:'spbh',width:60,align:'center'">编号</th>
                            <th data-options="field:'spmc',width:100,align:'center'">名称</th>
                            <th data-options="field:'spgg',width:100,align:'center'">规格</th>
                            <th data-options="field:'spdw',width:60,align:'center'">单位</th>
                            <th data-options="field:'zzs',width:100,align:'center'">制造商</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div id="spgrid-buttons">
                <div style="margin-left: 25px"><input id="spSelectBtn" class="easyui-textbox" data-options="width:250,height:30"></div>
            </div>
        </div>
    </body>
</html>
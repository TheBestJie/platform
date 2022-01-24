<!DOCTYPE html>
<html lang="en">
    <head>
        <#include "comm.ftl">
        <style>
            li{
                list-style:none;
                float: left;
                margin-left:10px;
            }
        </style>
        <script>
            function changeJhr(value,row,index){
                return value.yhm;
            }

            function changeKf(value,row,index) {
                return value.kfmc;
            }

            function changeGys(value,row,index){
                return value.gysmc;
            }

            function toShowJhxq(index,row) {
                //加载基本信息
                $('#jhdbh').textbox('setValue',row.jhdbh);
                $('#jhsj').textbox('setValue',row.jhsj);
                $('#jhr').textbox('setValue',row.jhr.yhm);
                $('#gys').textbox('setValue',row.gys.gysmc);
                $('#kf').textbox('setValue',row.kf.kfmc);
                $('#jhzj').textbox('setValue',row.jhzj);
                //加载详细信息
                $('#jhxqGrid').datagrid('loadData',row.jhxqList);

                $('#jhxqDlg').dialog('open');
            }

            function changeSpmc(value,row,index) {
                return row.sp.spmc;
            }

            function changeSpgg(value,row,index) {
                return row.sp.spgg;
            }

            function changeZzs(value,row,index) {
                return row.sp.zzs;
            }
        </script>
    </head>
    <body>
        <table id="jhdGrid" class="easyui-datagrid" data-options="title:'商品列表',fit:true,fitColumns:true,pagination:true,singleSelect:true,url:'jhdList.do',onDblClickRow:toShowJhxq">
            <thead>
                <tr>
                    <th data-options="field:'jhdbh',width:120,align:'center'">进货单编号</th>
                    <th data-options="field:'jhsj',width:120,align:'center'">进货时间</th>
                    <th data-options="field:'jhzj',width:120,align:'center'">进货总价</th>
                    <th data-options="field:'jhr',width:100,align:'center',formatter:changeJhr">进货人</th>
                    <th data-options="field:'kf',width:100,align:'center',formatter:changeKf">库房</th>
                    <th data-options="field:'gys',width:120,align:'center',formatter:changeGys">供应商</th>
                </tr>
            </thead>
        </table>
        <!--进货详情窗口-->
        <div id="jhxqDlg" class="easyui-dialog" data-options="title:'进货详情',width:750,height:450,modal:true,closed:true">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',height:130">
                    <ul id="jhdInfo">
                        <li><input id="jhdbh" class="easyui-textbox" data-options="width:230,height:30,label:'进货单编号',editable:false"></li>
                        <li><input id="jhsj" class="easyui-textbox" data-options="width:200,height:30,label:'进货时间',editable:false"></li>
                        <li><input id="jhr" class="easyui-textbox" data-options="width:200,height:30,label:'进货人',editable:false"></li>
                        <li style="margin-right: 30px"><input id="gys" class="easyui-textbox" data-options="width:200,height:30,label:'供应商',editable:false"></li>
                        <li><input id="kf" class="easyui-textbox" data-options="width:200,height:30,label:'库房',editable:false"></li>
                        <li><input id="jhzj" class="easyui-textbox" data-options="width:200,height:30,label:'进货总价',editable:false"></li>
                    </ul>
                </div>
                <div data-options="region:'center'">
                    <table id="jhxqGrid" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true">
                        <thead>
                        <tr>
                            <th data-options="field:'spbh',width:80,align:'center'">商品编号</th>
                            <th data-options="field:'spmc',width:100,align:'center',formatter:changeSpmc">商品名称</th>
                            <th data-options="field:'spgg',width:100,align:'center',formatter:changeSpgg">商品规格</th>
                            <th data-options="field:'zzs',width:120,align:'center',formatter:changeZzs">制造商</th>
                            <th data-options="field:'spsl',width:80,align:'center'">数量</th>
                            <th data-options="field:'spjg',width:80,align:'center'">价格</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
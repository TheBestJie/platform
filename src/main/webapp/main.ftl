<!DOCTYPE html>
<html lang="en">
<head>
    <title>采购-销售一体化综合管理平台</title>
    <#include "comm.ftl">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',height:110,closable:false,collapsible:false,minimizable:false,maximizable:false,title:'采购-销售一体化综合管理系统'">
            <h1 style="color: red">此系统为学习项目，并非实际项目，项目功能仅供参考。</h1>
        </div>
        <div data-options="region:'west',width:220,title:'主菜单'">
            <ul class="easyui-tree" data-options="animate:true">
                <li>
                    <span>基本信息管理</span>
                    <ul>
                        <li><span><a href="go_sp.htm" target="right">商品管理</a></span></li>
                        <li><span><a href="go_gys.htm" target="right">供应商管理</a></span></li>
                        <li><span><a href="go_kf.htm" target="right">库房管理</a></span></li>
                    </ul>
                </li>
                <li>
                    <span>经营管理</span>
                    <ul>
                        <li><span><a href="go_jhdAdd.htm" target="right">新建进货单</a></span></li>
                        <li><span><a href="go_jhdList.do" target="right">进货单列表</a></span></li>
                        <li><span>退货管理</span></li>
                        <li><span>销售管理</span></li>
                        <li><span>销退管理</span></li>
                    </ul>
                </li>
                <li>
                    <span>库存管理</span>
                    <ul>
                        <li><span>库存报表</span></li>
                        <li><span>盘亏</span></li>
                        <li><span>盘盈</span></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div data-options="region:'center',title:'欢迎【${Session.loginYh.yhm}】登录'">
            <iframe name="right" width="100%" height="100%" frameborder="0"></iframe>
        </div>
    </div>
</body>
</html>
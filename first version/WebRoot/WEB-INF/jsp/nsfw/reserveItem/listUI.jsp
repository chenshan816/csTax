ing<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>预约服务管理</title>
    <script type="text/javascript">
        var list_url = "${basePath}nsfw/reserveItem_listUI.action"; 
        //选中所有
        function doSelectAll(){
        	// jquery 1.6 前
			//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
			//prop jquery 1.6+建议使用
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));
        }
        //添加事项
        function doAdd(){
        	document.forms[0].action="${basePath}nsfw/reserveItem_addUI.action";
        	document.forms[0].submit();
        }
        //搜索
        function doSearch(){
        	document.forms[0].action="${basePath}nsfw/reserveItem_listUI.action";
        	document.forms[0].submit();
        }
        //编辑
        function doEdit(id){
        	document.forms[0].action="${basePath}nsfw/reserveItem_editUI.action?item.resItemId="+id;
			document.forms[0].submit();
        }
        //删除
        function doDelete(id){
        	document.forms[0].action="${basePath}nsfw/reserveItem_delete.action?item.resItemId="+id;
			document.forms[0].submit();
        }
        //删除选中
        function doDeleteAll(){
        	document.forms[0].action="${basePath}nsfw/reserveItem_deleteAll.action";
			document.forms[0].submit();
        }
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>预约事项</strong></div> </div>
                <div class="search_art">
                    <li>
                        事项编号：<s:textfield name="item.itemId" cssClass="s_text"  cssStyle="width:160px;"/>
                    </li>
					<li>
                        事项名称：<s:textfield name="item.resItemName" cssClass="s_text" cssStyle="width:160px;"/>
                    </li>
					<li>
                        处理部门：<s:select name="item.resItemDept" list="#{'':'全部','部门A':'部门A','部门B':'部门B'}"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td align="center">事项编号</td>
                            <td width="120" align="center">事项名称</td>
                            <td width="120" align="center">处理部门</td>
                            <td width="140" align="center">处理人</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                       		<s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if>>
                                <td align="center"><input type="checkbox" name="selectedRow" value='<s:property value='resItemId'/>'/></td>
                                <td align="center"><s:property value="itemId"/> </td>
                                <td align="center">
                                	<s:property value="resItemName"/>
                                </td>
                                <td align="center"><s:property value="resItemDept"/></td>
                                <td align="center"><s:property value="resItemToName"/></td>
                                <td align="center"><s:property value="#itemStateMap[state]"/></td>
                                <td align="center">
	                                <a href="javascript:doEdit('<s:property value='resItemId'/>')">编辑</a>
	                                <a href="javascript:doDelete('<s:property value='resItemId'/>')">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
            <jsp:include page="/common/PageNavigator.jsp"></jsp:include>
        </div>
    </div>
</form>

</body>
</html>
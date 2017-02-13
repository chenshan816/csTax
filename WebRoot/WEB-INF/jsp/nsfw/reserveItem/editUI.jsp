<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>预约服务管理</title>
    <script>
    	window.onload = doSelectDept();
    	function doSelectDept(){
    		var dept = $("#resItemDept option:selected").val();
   			//根据部门信息查询用户列表
    		$.ajax({
    			url:"${basePath}nsfw/reserveItem_getUserJson.action",
    			data:{"dept":dept},
    			type:"post",
    			dataType:"json",//返回json数据类型
    			success:function(data){
    				if(data != null && data !="" && data != undefined){
    					if("success" == data.msg){
    						var resItemToName= $("#resItemToName");
    						resItemToName.empty();
    						//添加到下拉列表中
    						$.each(data.userList,function(index,user){
    							resItemToName.append("<option value='"+user.name+"'>"+user.name+"</option>");
    						});
    					}else{
    						alert("获取处理人列表失败");
    					}
    				}else{
    					alert("获取被处理人列表失败");
    				}
    			},
    			error:function(){alert("获取被处理人列表失败")}
    		}); 
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath}nsfw/reserveItem_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>预约事项</strong>&nbsp;-&nbsp;修改事项</div></div>
    <div class="tableH2">修改事项</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">事项编号：</td>
            <td><s:textfield name="item.itemId" /></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">事项名称：</td>
            <td><s:textfield name="item.resItemName"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">处理部门：</td>
            <td colspan="3"><s:select id="resItemDept" name="item.resItemDept" list="#{'':'全部','部门A':'部门A','部门B':'部门B'}" onchange ="doSelectDept()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">处理人：</td>
            <td colspan="3"><select id="resItemToName" name="item.resItemToName" >
            	
            </select></td>
        </tr>
        <tr>
        	<td class="tdBg" width="200px">备注：</td>
            <td colspan="3"><s:radio name="item.state" list="#{ '1':'有效','0':'无效'}"/></td>
        </tr>
        
    </table>
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    <s:hidden name="strNo"/>
    <s:hidden name="strName"/>
    <s:hidden name="strDept"/>
    <s:hidden name="pageNo"/>
    <s:hidden name="item.resItemId"/>
    </div></div></div>
</form>
</body>
</html>
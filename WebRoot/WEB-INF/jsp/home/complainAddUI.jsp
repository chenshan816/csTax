<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>我要投诉</title>
    <script type="text/javascript" charset="utf-8" src="${basePath}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
    	window.UEDITOR_HOME_URL = "${basePath}/js/ueditor/";
    	var ue = UE.getEditor("editor");
    	
    	//根据部门查询该部门下所有用户列表
    	function doSelectDept(){
    		var dept = $("#toCompDept option:selected").val();
    		if(dept !=""){
    			//根据部门信息查询用户列表
	    		$.ajax({
	    			url:"${basePath}home_getUserJson.action",
	    			data:{"dept":dept},
	    			type:"post",
	    			dataType:"json",//返回json数据类型
	    			success:function(data){
	    				if(data != null && data !="" && data != undefined){
	    					if("success" == data.msg){
	    						var toCompName= $("#toCompName");
	    						toCompName.empty();
	    						//添加到下拉列表中
	    						$.each(data.userList,function(index,user){
	    							toCompName.append("<option value='"+user.name+"'>"+user.name+"</option>");
	    						});
	    					}else{
	    						alert("获取被投诉人列表失败");
	    					}
	    				}else{
	    					alert("获取被投诉人列表失败");
	    				}
	    			},
	    			error:function(){alert("获取被投诉人列表失败")}
	    		}); 
    		}else{
    			//清空被投诉人列表下拉框信息
    			$("#toCompName").empty();
    		}
    	}
    	
    	//提交表单
    	function doSubmit(){
    		//1.提交表单 ajax
    		$.ajax({
    			url:"${basePath}home_complainAdd.action",
    			data:$("#form").serialize(),
    			type:"post",
    			async:false,
    			success:function(msg){
    				if("success" == msg){
    					//2.提示用户已提交表单
    					alert("投诉成功");
    					//3.刷新父窗口
    					window.opener.parent.location.reload(true);
    					//4.关闭该窗口
    					window.close();
    				}
    				
    			},
    			error:function(){
    				alert("投诉失败");
    			}
    		});
    	}
    </script>
</head>
<body>
<form id="form" name="form" action="${basePath}/home_complainAdd" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要投诉</div></div>
    <div class="tableH2">我要投诉</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="250px">投诉标题：</td>
            <td><s:textfield name="comp.compTitle"/></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人部门：</td>
            <td><s:select id="toCompDept" name="comp.toCompDept" list="#{'':'请选择','部门A':'部门A','部门B':'部门B'}" onchange ="doSelectDept()"></s:select></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人姓名：</td>
            <td><select id="toCompName" name="comp.toCompName">
            
            </select></td>
        </tr>
        <tr>
            <td class="tdBg">投诉内容：</td>
            <td><s:textarea id="editor" name="comp.compContent" cssStyle="width:90%;height:160px;" /></td>
        </tr>
        <tr>
            <td class="tdBg">是否匿名投诉：</td>
            <td><s:radio name="comp.isNm" list="#{'false':'非匿名投诉','true':'匿名投诉' }" value="true"/></td>
        </tr>
       
    </table>
	<s:hidden name="comp.compCompany" value="%{#session.SYS_USER.dept}"></s:hidden>
	<s:hidden name="comp.compName" value="%{#session.SYS_USER.name}"></s:hidden>
	<s:hidden name="comp.compMobile" value="%{#session.SYS_USER.mobile}"></s:hidden>
    <div class="tc mt20">
        <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div>
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>
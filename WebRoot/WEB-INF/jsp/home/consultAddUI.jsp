<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>我要咨询</title>
    <script type="text/javascript" charset="utf-8" src="${basePath}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/js/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
    	window.UEDITOR_HOME_URL = "${basePath}/js/ueditor/";
    	var ue = UE.getEditor("editor");
    
    	//提交表单
    	function doSubmit(){
    		//1.提交表单 ajax
    		$.ajax({
    			url:"${basePath}home_consultAdd.action",
    			data:$("#form").serialize(),
    			type:"post",
    			async:false,
    			success:function(msg){
    				if("success" == msg){
    					//2.提示用户已提交表单
    					alert("咨询成功");
    					//3.刷新父窗口
    					window.opener.parent.location.reload(true);
    					//4.关闭该窗口
    					window.close();
    				}
    				
    			},
    			error:function(){
    				alert("咨询失败");
    			}
    		});
    	}
    </script>
</head>
<body>
<form id="form" name="form" action="${basePath}/home_consultAdd" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要咨询</div></div>
    <div class="tableH2">我要咨询</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="250px">咨询标题：</td>
            <td><s:textfield name="consult.consTitle"/></td>
        </tr>
        
        <tr>
            <td class="tdBg">投诉内容：</td>
            <td><s:textarea id="editor" name="consult.consContent" cssStyle="width:90%;height:160px;" /></td>
        </tr>
        
       
    </table>
	<s:hidden name="consult.consDept" value="%{#session.SYS_USER.dept}"></s:hidden>
	<s:hidden name="consult.consName" value="%{#session.SYS_USER.name}"></s:hidden>
	<s:hidden name="consult.consMobile" value="%{#session.SYS_USER.mobile}"></s:hidden>
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", path);
String url = path+"/images/blog/articleListBg.jpg";
request.setAttribute("url", url);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<title>文章全文</title>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.10.2.min.js"></script>
<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
.col-center-block {  
    float: none;  
    display: block;  
    margin-left: auto;  
    margin-right: auto;  
}
.table th,.table td{
	text-align: center;
	font-size:14px;
}
</style>
</head>
<%@include file="/common/blog_header.jsp" %>
<body style="text-align: center">
	<div class="container col-center-block">
		<div class="col-md-8 col-md-pull-1" >
			<s:if test="%{user.headImg != null && user.headImg != ''}">
	            <img class="img-rounded" src="${ctx}/upload/${user.headImg}" width="100" height="100"/>
	        </s:if>
	        <s:else>
				<img class="img-rounded" src="${ctx}/images/home/gs09.png" />
			</s:else>
		</div>
		<div class="col-md-4 col-md-pull-6">
			<h2 style="font-family:'方正舒体'">${user.name}</h2>
		</div>
		<div class="col-md-12">
			<h2 class="text-center">${article.title}</h2>
		</div>
		<div class="col-md-12">
			<h4 class="text-center">作者：${user.name}</h4>
			<h5 class="text-center"><s:date name="article.date" format="yyyy-MM-dd HH:mm"/></h5>
		</div>
		<div class="col-md-9 col-md-offset-2">
			<h4 class="text-left"><s:property value="article.content" escape="false"/></h4>
		</div>
		<div class="col-md-9 col-md-offset-2" >
			<div style="margin-bottom:50px;">
				<h3 class="text-left">评论区</h3>
				<div id="showCritique" class="text-left">
					<s:if test="#critiques != null">
						<s:iterator value="#critiques">
							<div>
								<div class="col-md-8">用户名：${name}</div>
								<div class="col-md-4 text-right">${createTime}</div>
							</div>
							<div heigh="50px" style="margin-left:5%;font-size:15px;">
								<s:property value="content" escape="false"/>
							</div>
						</s:iterator>
					</s:if>
					<s:else>
						<div><h4>暂无评论</h4></div>
					</s:else>
				</div>
			</div>
		</div>
		<div class="col-md-9 col-md-offset-2" id="critique">
			<input type="hidden" id="caId" value="${article.id}">
			<div style="margin-bottom:50px;">
				<h3 style="margin-bottom:20px;" class="text-left">回复</h3>
				<div style="margin-left:18px;" class="form-horizontal text-left">
					<div class="form-group">
						<label>回复标题:</label>
						<s:textfield class="input-xxlarge" placeholder="填写回复标题" id="cTitle"/>
					</div>
					<div class="form-group">
						<label>回复昵称:</label>
						<s:if test="%{#session.SYS_USER.name == null ||#session.SYS_USER.name ==''}">
							<s:textfield class="input-xxlarge" id="cName"/>
						</s:if>
						<s:else>
							<s:textfield disabled="true" class="input-xxlarge" value="%{#session.SYS_USER.name}" id="cName"/>
						</s:else>
					</div>
					<div class="form-group">
						<s:textarea id="editor" cssStyle="width:100%;height:160px;" />
					</div>
					<div class="form-group text-center">
						<button class="btn btn-primary" onclick="doSubmit()">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- 尾部{ -->
<div class="foot">版权所有©国税局 2017</div>
<!-- }尾部 -->
<script type="text/javascript">
window.UEDITOR_HOME_URL = "${ctx}/js/ueditor/";
var ue = UE.getEditor("editor");
//异步提交刷新
function doSubmit(){
	var caId = $("#caId").val();
	var title = $("#cTitle").val();
	var cName = $("#cName").val();
	var cContent = UE.getEditor('editor').getContent();
	$.ajax({
		url:"${ctx}/blog/article_addCritique.action",
	    data:{"critique.article.id":caId,"critique.title":title,"critique.name":cName,"critique.content":cContent},
	    type:"post",
	    dataType:"json",//返回json数据类型
	    success:function(data){
	    	if(data != null && data !="" && data != undefined){
	    		if("success" == data.msg){
	    			//在评论展示区进行展示
	    			var showCritique = $("#showCritique");
	    			var temp = data.critique[0];
	    			var html="<div><div class='col-md-8'>用户名:"+
	    			temp.name+"</div><div class='col-md-4 text-right'>"+temp.createTime
	    			+"</div></div><div heigh='50px' style='margin-left:5%;font-size:15px;'>"+temp.content+"</div>";
	    			showCritique.prepend(html);
	    			$("#caId").val("");
	    			$("#cTitle").val("");
	    			$("#cName").val(cName);
	    			UE.getEditor('editor').setContent("");
	    		}
	    	}
	    },
	    error:function(XMLResponse){
	    	alert(XMLResponse.responseText);
	    }
	});
	return false;
}
</script>
</html>

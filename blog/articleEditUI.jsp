<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", path);
String url = path+"/images/blog/article_bg.jpg";
request.setAttribute("url", url);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>	
<title>添加文章</title>
<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.10.2.min.js"></script>
<script>
   	window.UEDITOR_HOME_URL = "${basePath}/js/ueditor/";
   	var ue = UE.getEditor("editor");
   	//选择文章类型，只能选择一个--也可以自己写，自己写则增加了新的分类
   	$(function(){
   		var allBox = $(":checkbox");
   		allBox.click(function(){
			 if(this.checked || this.checked=='checked'){
	               allBox.removeAttr("checked");
	               $(this).prop("checked", true);
	               $("#type").val($(this).val()); 
	         }else{
	         	$("#type").val(""); 
	         }
   		});
   	});
</script>
</head>
<%@include file="/common/blog_header.jsp" %>
<body>
	<div class="container col-lg-9 col-lg-offset-3 col-sm-11 col-sm-offset-2 col-xs-12 col-xs-offset-1">
		<div style="margin-top: 15%">
			<form action="article_articleEdit.action" method="post" >
				<div class="form-group">
					<label>文章标题</label>
					<s:textfield class="input-xxlarge" placeholder="填写文章标题" name="article.title"/>
				</div>
				<div class="form-group">
					<label>文章文类</label>
					<s:textfield id="type" class="input-xxlarge" placeholder="填写文章分类" name="article.articletype.type"/>
				</div>
				<div class="form-group">
					<s:iterator value="articletypes">
						<input type="checkbox" value="${type}" />${type}
					</s:iterator>
				</div>
				<div class="form-group">
					<label>文章内容</label><br/><s:textarea id="editor" name="article.content" cssStyle="width:85%;height:30%;"/>
				</div>
				<div class="form-group" style="margin-left: 30%">
					<input class="btn btn-primary" type="submit" value="提交" />
				</div>	
				<input type="hidden" name="article.id" value="${article.id}">
			</form>
		</div>
	</div>
</body>
</html>

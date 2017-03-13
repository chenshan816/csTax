<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", path);
String url = path+"/images/blog/intro_bg.jpg";
request.setAttribute("url", url);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<%@include file="/common/header.jsp"%>
<title>个性化设置</title>
<script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}js/jquery/jquery-1.10.2.min.js"></script> 

<style type="text/css">
body {
	background: url('${basePath}/images/blog/intro_bg.jpg') no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
  	background-color:#333;
  	font-family: 'microsoft yahei' ,Arial,sans-serif;
}
</style>
</head>
<%@include file="/common/blog_header.jsp"%>
<body>

<div class="container" style="margin-top: 5%">
	<div class="text-center">
		<h1>个性化设置</h1>
	</div>
	<form class="form-horizontal" action="${basePath}/blog_setting.action" method="post" enctype="multipart/form-data"> 
		<div class="form-group">
			<label class="control-label col-xs-3">博客标题</label>
			<div class="col-xs-6">
				<s:textfield class="form-control" id="title" name="blog.title" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-3">个性签名 </label>
			<div class="col-xs-6">
				<s:textarea rows="5" class="form-control" id="content" name="blog.content" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-3">个性头像 </label>
			<div class="col-xs-6">
				 <s:if test="%{user.headImg != null && user.headImg !=''}">
	              	<img src="${basePath}upload/<s:property value='user.headImg'/>" width="160" height="160"/>
	            	<s:hidden name="user.headImg"/>
	             </s:if>
	             <input type="file" name="headImg"/>
	         </div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-3">电子邮箱</label>
			<div class="col-xs-6">
				<s:textfield class="form-control" id="email" name="user.email" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-3">手机号码</label>
			<div class="col-xs-6">
				<s:textfield class="form-control" id="mobile" name="user.mobile"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-3">生日</label>
			<div class="col-xs-6">
				<s:textfield class="form-control" id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'});"/>
			</div>
		</div>
		<div class="form-group">
			<input class="btn btn-primary center-block" type="submit" value="提交" />
		</div>
	</form>
</div>
</body>
</html>


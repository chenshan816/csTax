<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<%@include file="/common/header.jsp"%>
<title>个性化设置</title>
<script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
<!-- bootstrap -->
<link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css"></link>
<script type="text/javascript" src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
body{ width:100%; background:url("${basePath}/images/blog/intro_bg.jpg") #d4f5fe no-repeat 0px 0px;
    background-size:100% auto;
    filter : progid:DXImageTransform.Microsoft.AlphaImageLoader ( sizingMethod='scale' , src='../images/blog/intro_bg.jpg') ;
    margin:0;
    padding:0;
}
.vertical-center{
  position: absolute;
  top: 40%;
  left: 45%;
  transform: translate(-50%, -50%);
}
.left{
	position: absolute;
  	top: 30%;
  	left: 20%;
  	transform: translate(-50%, -50%);
}
</style>
</head>
<%@include file="/common/blog_header.jsp"%>
<body>
<div class="left">
			<img src="${basePath}images/blog/serv_icon2.png">
		</div>
<div class="container">
   <div class="col-xs-6 col-md-5 vertical-center">
	<form id="Form1" action="${basePath}/blog_setting.action" method="post" enctype="multipart/form-data"> 
		<table width="1000" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr height="100">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td align="right" width="300" valign="top"></td>
				<td width="20">&nbsp;</td>
				<td valign="top">
					<table width="500" border="1" cellpadding="0" cellspacing="0">
						<tr height="30">
							<td colspan="2" align="center"><span class="STYLE1">个性化设置</span></td>
						</tr>
						<tr height="30">
							<td align="right">博客标题</td>
							<td><s:textfield id="title" name="blog.title" />*</td>
						</tr>
						<tr height="30">
							<td>个性签名</td>
							<td><s:textarea id="content" name="blog.content" />*</td>
						</tr>
						<tr height="60">
							<td align="right">个性头像</td>
							<td>
							 	<s:if test="%{user.headImg != null && user.headImg !=''}">
                   				<img src="${basePath}upload/<s:property value='user.headImg'/>" width="100" height="100"/>
                  				    <s:hidden name="user.headImg" />
               					</s:if>
               					<input type="file" name="headImg"/>
							</td>
						</tr>
						<tr height="30">
							<td align="right">电子邮箱</td>
							<td><s:textfield id="email" name="user.email" /></td>
						</tr>
						<tr height="30">
							<td align="right">手机号码</td>
							<td><s:textfield id="mobile" name="user.mobile" /></td>
						</tr>
						<tr height="30">
           					<td align="right">生日：</td>
           					<td><s:textfield id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'});"/></td>
       					</tr>
						<tr height="30">
							<td colspan="2" align="center"><input type="submit" value="提交" name="submit" />&nbsp&nbsp&nbsp
							<input type="reset" value="重置" name="reset" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>		
	</form>
	</div>
</div>
</body>
</html>


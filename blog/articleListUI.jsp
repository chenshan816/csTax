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
<title>文章列表</title>
<!-- 日历获取器 -->
<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
<!-- 富文本编辑器 -->
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.10.2.min.js"></script>
</head>
<%@include file="/common/blog_header.jsp" %>
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
a{
	color: black;
}
</style>
<body style="text-align: center">
	<form action="" method="post">
		<div class="container col-center-block">
			<div class="col-md-6" >
				<s:if test="%{user.headImg != null && user.headImg != ''}">
		            <img class="img-rounded" src="${ctx}/upload/${user.headImg}" width="100" height="100"/>
		        </s:if>
		        <s:else>
					<img class="img-rounded" src="${ctx}/images/home/gs09.png" />
				</s:else>
			</div>
			<div class="col-md-6 col-md-pull-5">
				<h2 style="font-family:'方正舒体'">${user.name}</h2>
			</div>
			<div class="col-md-12">
				<h2 class="text-center">文章列表</h2>
			</div>
			<div class="col-md-10 col-md-offset-1">
				<ul class="list-inline">
					<li>
						<label>类型：</label>
						<s:select name="typeId" listValue="type" listKey="id" list="#articletypes" headerKey="" headerValue="全部"/>
					</li>
					<li>
						<label>开始时间</label>
						<s:textfield id="startTime" name="startTime" cssClass="s_text"  cssStyle="width:160px;"
			             	readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/>
			             - 
			            <s:textfield id="endTime" name="endTime" cssClass="s_text"  cssStyle="width:160px;"
			             	readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/>
					</li>
					<li>
						<button class="btn btn-info" onclick="doSearch()">搜索</button>
					</li>
					<li>
						<s:if test="#session.SYS_USER.id == user.id">
							<button class="btn btn-primary" onclick="doAddArticle()">添加文章</button>
						</s:if>
					</li>
				</ul>
			</div>
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-striped">
					<tr>
						<th>文章标题</th>
						<th>文章分类</th>
						<th>发表时间</th>
						<th>操作</th>
					</tr>
					<tbody>
						<s:iterator value="pageResult.items" status="st" >
							 <tr <s:if test="#st.even">class="success"</s:if>>
							 	<td><a target="_blank" href="${ctx}/blog/article_articleUI.action?article.id=${id}&user.id=${user.id}"> ${title}</a></td>
							 	<td>${articletype.type}</td>
							 	<td><s:date name="date" format="yyyy-MM-dd HH:mm"/></td>
							 	<td>
							 		<div class="dropdown" >
									  	<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									    	编辑
									  	<span class="caret"></span>
									  	</button>
									  	<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									    	<li><a href="${ctx}/blog/article_articleEditUI.action?article.id=${id}">修改</a></li>
									    	<li><a href="${ctx}/blog/article_articleDelete.action?article.id=${id}">删除</a></li>
									  	</ul>
									  	<span align="right"  style="font-size:3px;">点击量(${hasReads})</span>
									</div>
							 	</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<jsp:include page="/common/PageNavigator.jsp"></jsp:include>
			</div>
		</div>
		<input type="hidden" id="userId" value="${user.id}">
	</form>
</body>
<script type="text/javascript">
var list_url = "${ctx}/blog/article_articleListUI.action"; 
	//查询
	function doSearch(){
		$("#pageNo").val(1);
		var id = $("#userId").val();
		document.forms[0].action="${ctx}/blog/article_articleListUI.action?user.id="+id;
		document.forms[0].submit();
	}
	//添加文章
	function doAddArticle(){
		document.forms[0].action="${ctx}/blog/article_addUI.action";
        document.forms[0].submit();
	}

</script>
</html>

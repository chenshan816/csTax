<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", path);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
	<title>我的空间</title>
	<meta name="description" content="" />
  	<meta name="keywords" content="" />
	<meta name="robots" content="" />
	<link href="${ctx}/css/blog/reset.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/blog/960.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/blog/styles.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/blogFancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/blog/smooth-scroll.js"></script>
	<script type="text/javascript" src="${ctx}/js/blog/jquery.sticky.js"></script>
	<script type="text/javascript" src="${ctx}/js/blog/jquery.easing-1.3.pack.js"></script>
	<script type="text/javascript" src="${ctx}/blogFancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/blog/cform.js"></script>
<style type="text/css">
.imgShow{
	margin: 10px;
}
</style>
</head>
<%@include file="/common/blog_header.jsp"%>

<!-- 图片展示 -->
<!-- 添加图片弹窗进行添加 -->
<div class="container">
	<div class="modal fade" id="add_img" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" style="width:95%;">
	    	<div class="modal-content">
	      		<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	       		  	<h4 class="modal-title" id="myModalLabel">添加图片</h4>
	      		</div>
	      		<form  id="uploadForm" action="${ctx}/blog/blog_addImg.action" enctype="multipart/form-data">
		      		<div id="mainImg" class="modal-body" style="height:650px;">
		      			<img class="imgShow" src="${ctx}/images/blog/uploadImg.png" onclick="selectFile()" width="150px" height="150px">
		      			<input type="file"  name="fileToUpload" id="fileToUpload" onchange="changImg(event)"
		      			style="filter:alpha(opacity=0);opacity:0;width:0;height: 0;"/>
		      		</div>
		      		<div class="modal-footer">
						<button type="button" class="btn btn-primary" onclick="uploadFile()">开始上传</button>
		        		<button type="button" class="btn btn-default" onclick="selectFile()">继续添加</button>
		      		</div>
	      		</form> 
	   	 	</div>
		</div>
	</div>
</div>
<!-- 显示图片 -->
<div class="modal fade text-center" id="img" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-lg" style="width:90%;">
    	<div class="modal-content">
      		<div class="modal-header">
      		  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       		  <h4 class="modal-title" id="myModalLabel">图片展示</h4>
      		</div>
      		<div class="modal-body">
      			<div class="row">
      				<div class="col-md-8">
      					<img id="show_img" class="img-responsive">
      				</div>
      				<div class="col-md-4">
      					<div class="row">
      						<div class="col-md-8 col-md-pull-2">
      							<dl class="dl-horizontal" style="font-size: 20px">
      								<dt>图片名称:</dt>
      								<dd><label id="imgTitle" style="font-size:16px"></label><br/></dd>
								</dl>
							</div>		
	      					<div class="col-md-4 text-right">
	      						<input type="hidden" id="show_imgId"/>
	      						<s:if test="#session.SYS_USER.id == user.id">
	      							<button class="btn btn-primary" onclick="addImgDetail()">修改描述</button><br/>
	      						</s:if>
	      					</div> 
	      				</div>
	      				<div id="imgContext" class="text-left" style="font-size:16px;"></div>
	      			</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
      	</div>
   	 </div>
</div>


<div id="intro">
	<div class="title">
		<h1>
			<span class="name"><s:property value="%{#blog_info.title}"/></span><br/>
			<span class="intro_line"></span><span class="amp">&</span><span class="intro_line"></span><br/>
			<span class="small"><s:property value="%{#blog_info.content}"/></span><br/>
		</h1>
	</div> <!-- end .title -->
</div> <!-- end #intro -->

<nav class="nav nav-pills nav-justified">
	<ul>
		<li><a href="#intro">主页</a></li>
		<li><a href="#work">我的图片</a></li>
		<li><a href="#services">我的文章</a></li>
		<li><a href="#about">关于我</a></li>
		<li><a href="#contact">请联系</a></li>
	</ul>
</nav>

<!-- 图片展示 -->
<div id="work">
<div class="container_16">
		<div class="subheader">
			<div class="subheader_line"></div>
			<h2>我的图片</h2>
			<div class="subheader_line"></div>
				<s:if test="#session.SYS_USER.id == user.id">
					<h4><button style="float:right;" class="btn btn-default" data-toggle="modal" data-target="#add_img">添加图片</button></h4>
				</s:if>
		</div>
			<div id="myCarousel" class="carousel slide" style="margin-top:50px;;height:600px;width:850px;">
				<ol class="carousel-indicators">
					<s:iterator value="images" status="st">
							<li data-target="#myCarousel" data-slide-to="${st.index}" class= "<s:if test="#st.index==0">active</s:if>"></li>
					</s:iterator>
				</ol>
				<div class="carousel-inner">
					<s:iterator value="images" var="img" status="st">
						<div style="margin: 0 auto;" id="mainImg" class="text-center item <s:if test="#st.index==0">active</s:if>">
							<img data-toggle="modal" id="${img.id}" onclick="showImg(this)" src="${ctx}/upload/${img.imgUrl}"><br/>
							<span>${img.title}</span>
						</div>
					</s:iterator>
				</div>
				<a href="#myCarousel" data-slide="prev" class="carousel-control left">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    				<span class="sr-only">Previous</span>
				</a>
				<a href="#myCarousel" data-slide="next" class="carousel-control right">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    				<span class="sr-only">Next</span>
				</a>
			</div>
	</div>
</div>

<div class="work_bg_bottom"></div>

<!-- 文章展示(这里显示分类)，点击进入文章展示区域 -->
<div id="services">
	<div class="container_16">
		<div class="subheader">
			<div class="subheader_line"></div><h2>我的文章</h2><div class="subheader_line"></div>
			<s:if test="#session.SYS_USER.id == user.id"><h4><a href="${ctx}/blog/article_addUI.action" style="float:right" class="btn btn-default">添加文章</a></h4></s:if>
		</div>		
		<div class="serv_icons">
			<form id="ArticleForm" name="ArticleForm" method="post" action="">
				<s:iterator value="articletypes" status="st" var="articletype">
					<div class="grid_4" onclick="articleListUI('<s:property value='id'/>')">
						<div class="serv_icon1" style="background-image: url('${ctx}/images/blog/serv_icon${(st.index+1)%4}.png');"></div>
						<h3>${type}</h3>
					</div>
				</s:iterator>
			</form>
		</div>
	</div> <!-- end .container_16 -->
</div> <!-- end #services -->

<div class="services_bg_bottom"></div>

<div id="about">
	<div class="container_16">
	
		<div class="subheader">
			<div class="subheader_line"></div><h2>关于我</h2><div class="subheader_line"></div>
		</div>
	
	<div class="grid_5">
		<div>
			<s:if test="%{user.headImg != null && user.headImg != ''}">
            	<img class="about_pic" src="${ctx}/upload/${user.headImg}" />
            </s:if>
            <s:else>
				<img class="about_pic" src="${ctx}/images/home/gs09.png" />
			</s:else>
		</div>
		<div class="social">
			<ul>
				<li><a href="#"><div class="twitter sprite"></div></a></li>
				<li><a href="#"><div class="facebook sprite"></div></a></li>
				<li><a href="#"><div class="linkedin sprite"></div></a></li>
				<li><a href="#"><div class="dribble sprite"></div></a></li>
			</ul>
		</div> <!-- end .social -->
	</div> <!-- end .grid_5 -->
	
	<div class="grid_6">
			<div class="about_copy">
			<h4>Who am I?</h4>
			<s:if test="user.memo != null && user.memo != ''">
				<p>${user.memo}</p>
			</s:if>
			<s:else>
				<p>这人很懒，什么都没有留下</p>
			</s:else>
		</div> <!-- end .about_copy -->
	</div> <!-- end .grid_6 -->
	
	<div class="grid_5">
		<div class="skills">
			<h4>Skills</h4>
			<ul>
				<li><div class="skill1"><p>Photoshop</p></div></li>
				<li><div class="skill2"><p>Illustrator</p></div></li>
				<li><div class="skill3"><p>HTML/CSS</p></div></li>
				<li><div class="skill4"><p>Javascript</p></div></li>
				<li><div class="skill5"><p>Wordpress</p></div></li>
				<li><div class="skill6"><p>PHP/MySQL</p></div></li>
			</ul>
		</div> <!-- end .skills -->
	</div> <!-- end .grid_5 -->
	
	</div> <!-- end .container_16 -->
</div> <!-- end #about -->

<div class="about_bg_bottom"></div>

<div id="contact">
	<div class="container_16">
	
		<div class="subheader">
			<div class="subheader_line"></div><h2>请联系</h2><div class="subheader_line"></div>
		</div>
		
		<div class="grid_7">
			<div class="contact_form">
				<h4>Get in touch</h4>
				<form method="post">
					<input type="text" name="Name" id="name" value="Name" onfocus="this.value = this.value=='Name'?'':this.value;" onblur="this.value = this.value==''?'Name':this.value;" />
					<input type="text" name="Email" id="email" value="Email" onfocus="this.value = this.value=='Email'?'':this.value;" onblur="this.value = this.value==''?'Email':this.value;" />
					<input type="text" value="Subject" id="subject" onfocus="this.value = this.value=='Subject'?'':this.value;" onblur="this.value = this.value==''?'Subject':this.value;" />
					<textarea name="Message" id="body" onfocus="this.value = this.value=='Message'?'':this.value;" onblur="this.value = this.value==''?'Message':this.value;">Message</textarea>
					<input type="submit" name="submit" id="submit" value="send" class="submit-button" onClick="return check_values();" />
				</form>
				<div id="confirmation" style="display:none; position: relative; z-index: 600; font-family: 'Open Sans', sans-serif; font-weight: 300; font-size: 16px; color: #4e4e4e;"></div>
			</div> <!-- end .contact_form -->
				
		</div> <!-- end .grid_7 -->
				
		<div class="grid_9">
		
			<div class="contact_info">
				<h4>联系方式</h4>
				<div class="grid_4 alpha">
					<p><img src="${ctx }/images/blog/icn_phone.png" alt="" /> <s:property value="user.mobile"/></p>
					<p><img src="${ctx }/images/blog/icn_mail.png" alt="" /> <s:property value="user.email"/></p>
				</div>
				<div class="grid_4 omega">
					<p><img src="${ctx }/images/blog/icn_address.png" alt="" /> Fifth Avenue<br />
					<span class="address">767 5th Ave, New York,</span><br />
					<span class="address">NY 10153</span></p>
				</div>
			</div>
			
		</div> <!-- end .grid_9 -->
		
	</div> <!-- end .container_16 -->
</div> <!-- end #contact -->

<div class="contact_bg_bottom"></div>

<footer>
	<p>&copy; 2017 All rights reserved</p>
</footer>
<input type="hidden" id="user" name="user.id" value="${user.id}">
<script type="text/javascript" src="${ctx}/js/blog/home_main.js"></script>
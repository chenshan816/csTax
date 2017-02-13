<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>咨询详细信息</title>
</head>
<body class="rightBody">
    <div class="vp_d_1">
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            	<div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>咨询管理</strong>&nbsp;-&nbsp;投诉受理</div></div>
    <div class="tableH2">咨询详细信息<span style="color:red;">&nbsp;&nbsp;<s:property value ="#consultStateMap[consult.state]" /></span></div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
    	<tr><td colspan="2" align="center">咨询人信息</td></tr>
        <tr>
            <td class="tdBg">咨询人单位：</td>
            <td>
            	<s:property value="consult.consDept"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg">咨询人姓名：</td>
            <td>
            	<s:property value="consult.consName"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg">咨询人手机：</td>
            <td>
            	<s:property value="consult.consMobile"/>
            </td>
        </tr>
        <tr><td colspan="2" align="center">咨询信息</td></tr>
        <tr>
            <td class="tdBg">咨询时间：</td>
            <td>
            	<s:date name="consult.consTime" format="yyyy-MM-dd HH:mm"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg">咨询标题：</td>
            <td><s:property value="consult.consTitle"/></td>
        </tr>
        <tr>
            <td class="tdBg">咨询内容：</td>
            <td><s:property value="consult.consContent" escape="false"/></td>
        </tr>
	        <s:if test = "%{consult.consultReply != null}">
	        	<tr><td colspan="2" align="center">受理信息</td></tr>
	        	 <tr>
		            <td colspan="2">
		            	<fieldset style="border: solid 1px #c0c0c0;margin-top:5px;"><legend style="color:green;font-weight:bold;">回复<s:property value = "#st.count"/>&nbsp;</legend>
							<div style="width:100%; text-align:center;color:#ccc;maring-top:5px;">
							回复部门：<s:property value = "consult.consultReply.replyDept"/>&nbsp;&nbsp;
							回复人：<s:property value = "consult.consultReply.replyer"/>&nbsp;&nbsp;
							回复时间：<s:date name = "consult.consultReply.replyTime" format="yyyy-MM-dd HH:mm"/>
							</div>
							<div style="width:100%;maring-top:10px;font-size:13px;padding-left:5px;"><s:property value = "consult.consultReply.replyContent"/></div>
						</fieldset>
		            </td>
		            </tr>
	        </s:if>
	   </table>
    <div class="tc mt20">
        <input type="button" onclick="javascript:window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div></div>
</body>
</html>
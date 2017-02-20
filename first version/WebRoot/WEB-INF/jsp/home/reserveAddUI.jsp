<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>我要预约</title>
</head>
<body>
<form id="form" name="form" action="" method="post" enctype="multipart/form-data">
    <div class="vp_d_1">
        <div style="width=1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;我要预约</div></div>
    <div class="tableH2">我要预约</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="250px">预约事项：</td>
            <td></td>
        </tr>
        <tr>
            <td class="tdBg">预约时间：</td>
            <td></td>
        </tr>
        <tr>
            <td class="tdBg">预约地点：</td>
            <td></td>
        </tr>
        <tr>
            <td class="tdBg">预约说明：</td>
            <td></td>
        </tr>
        <tr>
            <td class="tdBg">预约人：</td>
            <td></td>
        </tr>
       
    </table>

    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="window.close()" class="btnB2" value="关闭" />
    </div>
    </div></div>
    <div style="width=1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>投诉受理管理</title>
    <script type="text/javascript" src="${basePath}js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	var list_url = "${basePath}nsfw/consult_listUI.action"; 
    	//搜索
		function doSearch(){
			$("#pageNo").val(1);
			document.forms[0].action="${basePath}nsfw/consult_listUI.action";
			document.forms[0].submit();
		}
		//受理模块
		function doDeal(consId){
			document.forms[0].action="${basePath}nsfw/consult_dealUI.action?consult.consId="+consId;
			document.forms[0].submit();
		}
		//详细信息展示
		function showDetailInfo(consId,state){
			if(state == "1"){
				document.forms[0].action="${basePath}nsfw/consult_dealUI.action?consult.consId="+consId;
				document.forms[0].submit();
			}
		}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>咨询管理</strong></div> </div>
                <div class="search_art">
                    <li>
                       	咨询标题：<s:textfield name="consult.consTitle" cssClass="s_text"  cssStyle="width:160px;"/>
                    </li>
                    <li>
                       	咨询时间：<s:textfield id="startTime" name="startTime" cssClass="s_text"  cssStyle="width:160px;"
                       			readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/>
                              - 
                             <s:textfield id="endTime" name="endTime" cssClass="s_text"  cssStyle="width:160px;"
                             	readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/>
                    </li>
                    <li>
                       	状态：<s:select name="consult.state" list="#consultStateMap" headerKey="" headerValue="全部"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td align="center">咨询标题</td>
                            <td width="120" align="center">咨询人单位</td>
                            <td width="120" align="center">咨询人</td>
                            <td width="140" align="center">咨询时间</td>
                            <td width="100" align="center">状态</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                       <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if> onclick="showDetailInfo('<s:property value='consId'/>','<s:property value='state'/>')" >
                                <td align="center"><s:property value="consTitle"/></td>
                                <td align="center"><s:property value="consDept"/></td>
                                <td align="center"><s:property value="consName"/></td>
                                <td align="center"><s:date name="consTime" format="yyyy-MM-dd HH:mm"/></td>
                                <td align="center"><s:property value="#consultStateMap[state]"/></td>
                                <td align="center">
                                	<s:if test="state == 0">
                                    	<a href="javascript:doDeal('<s:property value='consId'/>')">处理</a>
                                    </s:if>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>

        <jsp:include page="/common/PageNavigator.jsp"></jsp:include>

        </div>
    </div>
</form>

</body>
</html>
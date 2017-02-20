<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div class="c_pate" style="margin-top: 5px;">
        <s:if test="pageResult.totalCount >0" >
		<table width="100%" class="pageDown" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td align="right">
                 	总共<s:property value="pageResult.totalCount"/> 条记录，当前第 <s:property value="pageResult.pageNo"/> 页，共 <s:property value="pageResult.pageTotalCount"/> 页 
                 	<s:if test="pageResult.pageNo > 1">
                 	&nbsp;&nbsp;<a href="javascript:doGoPage(<s:property value="pageResult.pageNo-1"/>)">上一页</a>
                 	</s:if>
                 	<s:if test="pageResult.pageNo < pageResult.pageTotalCount">
                    &nbsp;&nbsp;<a href="javascript:doGoPage(<s:property value="pageResult.pageNo+1"/>)">下一页</a>
					</s:if>
					到&nbsp;<input type="text" id="pageNo" name="pageNo" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
					max="<s:property value="pageResult.pageTotalCount"/>" value="<s:property value="pageResult.pageNo"/>" /> &nbsp;&nbsp;
			    </td>
			</tr>
		</table>
		</s:if>
		<s:else>
			暂无数据！
		</s:else>	
		<script type="text/javascript">
			//分页进行数据显示
			function doGoPage(pageNo){
				document.forms[0].action=list_url;
				document.getElementById("pageNo").value = pageNo;
				document.forms[0].submit();
			}
		</script>
</div>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="servlet" required="true"%>
<%@ attribute name="textName" required="true"%>
<%@ attribute name="passwordName" required="true"%>

<form action="${servlet}" method="post">
	<table>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="${textName}"></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="text" name="${passwordName}"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="登录"></td>
		</tr>
	</table>
</form>
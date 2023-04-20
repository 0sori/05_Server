<form action="<%=request.getContextPath()%>/binsert" method="post">

	<table align="center">

		<tr>
			<td>제목</td>

			<td><input type="text" name="btitle"></td>
		</tr>

		<tr>
			<td>작성자</td>

			<td><input type="text" readonly name="bwriter"
				value="<%=loginUser.getUserId()%>"></td>
		</tr>

		<tr>
			<td>내용</td>

			<td><textarea cols="50" rows="7" name="bcontent"></textarea></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><input type="submit" value="등록하기">

				<a href="<%=request.getContextPath()%>/first/blist?page=1">목록으로</a></td>
		</tr>

	</table>

</form>
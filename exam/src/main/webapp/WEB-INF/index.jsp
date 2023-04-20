<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.vo.*"%>

<%

Board board = (Board) request.getAttribute("board");

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>

	<div align="center">

		<form action="<%=request.getContextPath()%>/update" method="post">

			<table>

				<input type="hidden" name="boardnum"
					​ value="<%=board.getBoardNum()​%>">

				<tr>

					<td>제목</td>

					<td><input type="text" name="boardtitle"
						value="<%=board.getBoardTitle()%>"></td>



					<td>작성자</td>

					<td><input type="text" name="boardwritear"
						value​="<%=board.getBoardWriter()%>"></td>

				</tr>

				<tr>

					<td>내용</td>

				</tr>

				<tr>

					<td colspan="4"><textarea name="content" cols="60" rows="15"
							style="resize: none;"><%=board.getBoardContent()%></textarea></td>

				</tr>

			</table>

			<div align="center">

				<button type="button" onclick="history.back();">취소</button>

				<button type="submit">수정하기</button>

			</div>

		</form>

	</div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题</title>
<script src="../../js/jquery-1.6.4.js"></script>
<link type="text/css" rel="stylesheet" href="../../css/bootstrap.css">
<script type="text/javascript" src="../../js/bootstrap.js"></script>
<script type="text/javascript" src="../../js/jquery-3.3.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container">
		<form id="myForm" method="POST">
			<table class="table table-striped">
				<c:forEach items="${QuestionDepository}" var="Question"
					varStatus="str">
					<tr>
						<td>问题<c:out value="${str.count }"/></td>
						<td><c:out value="${Question}"/></td>
						<td>答案<c:out value="${str.count }"/>：<input type="text" value="" name="answer" class="form-control"/></td>
					</tr>
				</c:forEach>
			</table>
			<button id="submit" class="btn btn-success">提交</button>
			<input id="return" type="reset" value="返回" class="btn btn-info">
		</form>
	</div>
</body>
<script>
$("#submit").click(
		function(){
			$.post("/getScore",$("#myForm").serialize(),function(data){
				alert(data);
			}
			);
		}
);
$("#return").click(
		function(){
			window.location.href = "../../html/studentIndex.html";	
		}
);
</script>
</html>
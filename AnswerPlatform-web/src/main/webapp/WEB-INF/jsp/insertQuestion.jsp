<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>添加题</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/jquery-1.6.4.js"></script>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.css">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form id="questions" method="POST">
					<p>问题：<input type="text" name="question" class="form-control"></p>
					<p>答案：<input type="text" name="answer" class="form-control"></p>
					<p><button id="submit" class="btn btn-success">提交</button></p>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<script>
	$("#submit").click(
			function(){
				$.post("/insertQuestion",$("#questions").serialize(),function(data){
					alert(data);
				}
				);
			}
	);
	</script>
</body>
</html>
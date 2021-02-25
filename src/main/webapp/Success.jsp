<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

    <script type="text/javascript">
        $(function(){
        $("#header").load("header.html");
        $("#footer").load("footer.html");
        });

    </script>
</head>
<body>
<%
    Integer id= (Integer) request.getAttribute("id");
%>
<div id="header"></div>
<form action = "Result" method = "post" enctype = "multipart/form-data">

<div id="UploadData" style="padding:20px;">
    <h4>Successfully Recorded Transaction. Transaction id= <%= id %></h4>

</div>
</form>
<div id="footer"></div>
</body>

<script>

</script>
</html>
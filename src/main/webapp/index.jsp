<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

    <script>
        $(function(){
        $("#header").load("header.html");
        $("#footer").load("footer.html");
        });
    </script>

</head>
<body>

<div id="header"></div>
<!--Remaining section-->

<!--<div style="background-color:black;color:white;padding:30px;">-->

<h1 style="display: flex; justify-content: center"> Production Entries</h1>

<div style="display: flex; justify-content: space-between">
    <a href="AddData">
    <button type="button"  >add data</button>
    </a>
    <a href="Test">
        <button type="button"  >+</button>
    </a>
</div>


<br>
<br>
<a href="Display">
<button type="button">check</button>
</a>

<br>

<!--</div> -->
<div id="footer"></div>

</body>

</html>


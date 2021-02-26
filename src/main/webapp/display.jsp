<%@ page import ="java.util.*" %>
<%@ page import ="com.sample.model.Display" %>
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
   List<Display> result= (List) request.getAttribute("display");
   Iterator it=result.iterator();
   %>

<div id="header"></div>
<form action = "Display" method = "post" enctype = "multipart/form-data">
<div id="TableDisplay" style="display: flex; justify-content: center">
<style>
  td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
</style>
    <table id="mytab1">

        <tr>
            <th>Transaction id</th>
            <th>Start date</th>
            <th>End Date</th>
        </tr>
        <%
        while(it.hasNext()){
        Display display= (Display) it.next() ;
        %>
        <tr>
          <td><%= display.getTransactionId() %></td>
          <td><%= display.getStartDate() %></td>
          <td><%= display.getEndDate() %></td>
        </tr>
        <%
        }
        %>

    </table>



</div>

</form>
<div id="footer"></div>
</body>

<script>

</script>
</html>
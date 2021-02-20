<%@ page import ="java.util.*" %>
<%@ page import ="com.sample.model.Purchases" %>
<%@ page import ="com.sample.model.Sales" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
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
List<Purchases> result= (List) request.getAttribute("purchases");
List<Sales> result1= (List) request.getAttribute("sales");
String endDate = (String) request.getAttribute("endDate");
String startDate = (String) request.getAttribute("startDate");
Iterator it = result.iterator();
Iterator it1= result1.iterator();

%>
<!--<div style="background-color:black;color:white;padding:30px;">-->
<div id="header"></div>
<h1 style="display: flex; justify-content: center"> Production Entries</h1>


<form name="loginForm" method="post" action="createProductionEntry">



<div id="TableDisplay">
    <style>
  td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
</style>
    <h3> <%= startDate %> To <%= endDate %></h3>

    <table id="mytab1">
        <tr>
            <th>Product Name</th>
            <th>Previous Stock</th>
            <th>Purchase/Sales Quantity</th>
            <th>Purchase/Sales Amount</th>
            <th>Avg Cost</th>
            <th>Consumption Qty</th>
            <th>Consumption%</th>
            <th>Consumption cost</th>
            <th>Stock</th>
        </tr>

        <%
            while(it.hasNext()){
            Purchases purchase =(Purchases) it.next() ;
        %>
         <tr>
                    <td><%= purchase.getItemName() %></td>
                    <td>0</td>
                    <td><%= purchase.getQty() %></td>
                    <td><%= purchase.getAmount() %></td>
                    <td>0</td>
                    <td><input type="number" id=<%= purchase.getItemName() %> name="consumptionQty" value="0" onchange="changeconsumptionqty(this.id)"></td>
                    <td>0%</td>
                    <td>0</td>
                    <td>0</td>
                </tr>
        <%
            }
        %>

        <tr>
            <td>Total</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td id="cqtotal">0</td>
            <td></td>
            <td id="cctotal">0</td>
            <td></td>
        </tr>

             <%
                    while(it1.hasNext()){
                    Sales sale =(Sales) it1.next() ;
                %>
                 <tr>
                            <td><%= sale.getItemName() %></td>
                            <td>0</td>
                            <td><%= sale.getQty() %></td>
                            <td><%= sale.getAmount() %></td>
                            <td>0</td>
                            <td><input type="number" id=<%= sale.getItemName() %> name="udCake" value="0" onchange="changeudCake(this.id,'10')"></td>
                            <td>0%</td>
                            <td>0</td>
                            <td>0</td>
                        </tr>
                <%
                    }
                %>

<!--
        <tr>
            <td>UD Cake</td>
            <td>0</td>
            <td>100905</td>
            <td id="udcakeamt">2572531.00</td>
            <td>0</td>
            <td><input type="number" id="udcake" name="udCake" value="0" onchange="changeudCake(this.id,'10')"></td>
            <td>0%</td>
            <td id="udcakecc">0</td>
            <td>0</td>
        </tr>

-->
        <tr>
            <td>Wastage</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td id="wastagetotal">0</td>
            <td id="wastagepercent">0%</td>
            <td></td>
            <td></td>
        </tr>

    </table>

    <label>Processing Rate:</label>
    <input type="text" id="processingRate" name="processingRate" value="0" onchange="calculateprofit()">
    <br>
    <label>GST:</label>
    <input type="text" id="GST" name="GST" value="0" onchange="calculateprofit()">
    <br>
    <label>Processing Fee:</label>
    <input type="text" id="processingFee" value="0" disabled>
    <br>
    <label>Total Expense:</label>
    <input type="text" id="totalExpense" value="0" disabled>
    <br>
    <label>Profit:</label>
    <input type="text" id="profit" value="0" disabled>

</div>
    <div>
        <button>Save Production Entry</button>
    </div>
</form>
<!--</div> -->

<div id="footer"></div>
</body>
<script>
function calculateprofit()
{
	var processingRate= parseFloat(document.getElementById("processingRate").value);

    var gst= parseFloat(document.getElementById("GST").value);

    var processingFee= parseFloat(processingRate*(1+(gst/100))*document.getElementById("udcake").value);
    document.getElementById("processingFee").value= processingFee.toFixed(2);

    var totalExpense= parseFloat(processingFee + parseFloat(document.getElementById("cctotal").innerHTML));
    document.getElementById("totalExpense").value= totalExpense.toFixed(2);

    var profit= parseFloat(parseFloat(document.getElementById("udcakecc").innerHTML)-totalExpense);
    document.getElementById("profit").value= profit.toFixed(2);

}
function addData() {
   var x = document.getElementById("UploadData");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function displayTable()
{   var x = document.getElementById("TableDisplay");
  if (x.style.display === "none")
  {
    x.style.display = "block";
  }
  else
  {
    x.style.display = "none";
  }
}

function changeudCake(input1,row)
{
	var cqTotal=parseInt(document.getElementById("cqtotal").innerHTML);

    var consumptionQty=parseInt(document.getElementById(input1).value);


    var table = document.getElementById("mytab1");

    var cpColumn= 6;
    var prevstockColumn=1;
    var salesColumn=3;
    var quantityColumn=2;
    var avgcostColumn=4;
    var ccColumn=7;
    var stockColumn=8;



        var col= table.rows[row].cells[cpColumn];
        var z=parseFloat(consumptionQty*100/parseInt(cqTotal)).toFixed(2);
        col.innerHTML=z+"%";

       var avgCost= parseFloat(table.rows[row].cells[salesColumn].innerHTML) / parseInt(table.rows[row].cells[quantityColumn].innerHTML);
 	    //consumption cost= consumption qty* avg cost
		table.rows[row].cells[avgcostColumn].innerHTML= avgCost.toFixed(2);
        var consumptionCost= (avgCost*consumptionQty).toFixed(2);
        table.rows[row].cells[ccColumn].innerHTML= consumptionCost;


        var currentStock= table.rows[row].cells[quantityColumn].innerHTML;
        var previousStock= table.rows[row].cells[prevstockColumn].innerHTML;

        table.rows[row].cells[stockColumn].innerHTML=previousStock+currentStock-consumptionQty;

       //table.rows[row+1].cells[5].innerHTML = cqTotal-consumptionQty;

       document.getElementById("wastagetotal").innerHTML=cqTotal-consumptionQty;
       document.getElementById("wastagepercent").innerHTML= parseFloat(100- z).toFixed(2)+"%";

}



function changeconsumptionqty(input1)
{
	var consumptionQty=parseInt(document.getElementById(input1).value);
    var cqTotal=document.getElementById("cqtotal");




    var table = document.getElementById("mytab1");
    var ccTotal=document.getElementById("cctotal");
    ccTotal.innerHTML=0;
    cqTotal.innerHTML=0;

    var cpColumn= 6;
    var prevstockColumn=1;
    var salesColumn=3;
    var quantityColumn=2;
    var avgcostColumn=4;
    var ccColumn=7;
    var stockColumn=8;
    for (var i = 1; i<9; i++)
	{
        var temp= parseInt(document.getElementsByName("consumptionQty")[i-1].value);
        cqTotal.innerHTML=temp+parseInt(cqTotal.innerHTML);
    }
	for (var i = 1; i<9; i++)
	{
        var temp= parseInt(document.getElementsByName("consumptionQty")[i-1].value);

        var col= table.rows[i].cells[cpColumn];
        col.innerHTML=(temp*100/parseInt(cqTotal.innerHTML)).toFixed(2)+"%";

      	var avgCost= parseFloat(table.rows[i].cells[salesColumn].innerHTML) / 		parseInt(table.rows[i].cells[quantityColumn].innerHTML);
 	    //consumption cost= consumption qty* avg cost
		  table.rows[i].cells[avgcostColumn].innerHTML=avgCost.toFixed(2);

        var consumptionCost= (avgCost*temp).toFixed(2);
        table.rows[i].cells[ccColumn].innerHTML= consumptionCost;

        ccTotal.innerHTML= parseFloat(consumptionCost)+ parseFloat(ccTotal.innerHTML);

        var currentStock= table.rows[i].cells[quantityColumn].innerHTML;
        var previousStock= table.rows[i].cells[prevstockColumn].innerHTML;

        table.rows[i].cells[stockColumn].innerHTML=previousStock+currentStock-temp;

   	}
    changeudCake("udcake",'10');
}

</script>
</html>


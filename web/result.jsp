<%--
  Created by IntelliJ IDEA.
  User: dongqingqing
  Date: 17/9/11
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script type="text/javascript">
    function delayedRedirect(){
      window.location = "http://localhost:8080/tianyu/servlet/allStatsServlet";
    }
  </script>
</head>
<body onLoad="setTimeout('delayedRedirect()',1000)">
<h2><%=request.getAttribute("message") %></h2>
</body>
</html>

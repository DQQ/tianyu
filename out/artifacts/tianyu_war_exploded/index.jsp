<%--
  Created by IntelliJ IDEA.
  User: dongqingqing
  Date: 17/9/6
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tianyu.mode.StatsBean" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<%
  List list = (List)request.getAttribute("list");
%>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>查看所有提测信息</title>
  <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
  <link href="/tianyu/static/css/buttons.css" rel="stylesheet" media="screen">
  <link href="/tianyu/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
  <script src="/tianyu/static/js/jquery-2.1.1.min.js"></script>
  <script src="/tianyu/static/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/tianyu/static/js/bootstrap-datetimepicker.js"></script>
  <script type="text/javascript" src="/tianyu/static/js/bootstrap-datetimepicker.min.js"></script>
  <script type="text/javascript" src="/tianyu/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
  <script type="text/javascript" src="/tianyu/static/js/stats.js"></script>
</head>
<script type="text/javascript">
  $(document).ready(function()
  {
    var group = "{{group}}";
    jsSelectitemByValue_index(document.getElementById("selectedgroup"), group);
  });
</script>
<body>

<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12">
      <ul class="breadcrumb">
        <li><a href="/tianyu/addstats.jsp">返回添加页面</a> <span class="divider"></span></li>
        <%--<li><a href="/tianyu/allstats.jsp">主页</a> <span class="divider"></span></li>--%>
      </ul>
    </div>

    <div class="row">
      <div class="form-group">
        <form class="form-horizontal" id="filterbyday" action="/tianyu/servlet/allStatsServlet" method="get">
          <div class="form-group col-md-3">
            <ul class="nav nav-pills">
              <li><label style="margin-left:20px" class="control-label">小组</label></li>
              <div class="dropdown col-sm-10">
                <select class="form-control" id="selectedgroup" name="selectedgroup">
                  <option value ="all">ALL</option>
                  <option value="CMS">CMS</option>
                  <option value="CM">CM</option>
                  <option value="batterydoctor">电池医生</option>
                  <option value ="test">test</option>
                  <option value="sjz">手助+市场</option>

                </select>
              </div>
            </ul>
          </div>

          <div class="form-group col-md-2">
            <label for="dayfrom" class="col-md-3 control-label">从</label>
            <div class='input-group date form_date'><input class="form-control" type="text" {% if dayfrom %}value={{dayfrom}}{% endif %} name="dayfrom" id="datetimepicker1" data-date-format="yyyy-mm-dd"></div>
          </div>

          <div class="form-group col-md-2">
            <label for="dayto" class="col-md-3 control-label">到</label>
            <div class='input-group date form_date'><input class="form-control" type="text" {% if dayto %}value={{dayto}}{% endif %} name="dayto" id="datetimepicker2" data-date-format="yyyy-mm-dd"></div>
          </div>
          <div class="form-group col-md-1">
            <input type="button" class="btn btn-success" onclick="filter('filterbyday')" value="筛选"></input>
          </div>

          <div class="form-group col-md-2" style="position: relative; top: -15px;">
            <ul class="nav nav-pills">
              <li role="presentation" class="active"><a href="?pagerets=否">查看不合格记录</a></li>
            </ul>
          </div>
        </form>
      </div>
    </div>
  </div>



  <%--<div class="row">--%>
  <%--<div class="col-md-8"><h4>提测合格率为：{{passrate}}, {% if totalcount %}提测次数：{{totalcount}}{% endif %}{% if failcount %}不合格次数：{{failcount}}{% endif %}</h4></div>--%>
  <%--</div>--%>
  <%----%>
</div>

<table class="table table-bordered">
  <thead>
  <tr class="info">
    <th>时间</th>
    <th>小组</th>
    <th>提测邮件</th>
    <th>版本号</th>
    <th>对应插件</th>
    <th>需求评审</th>
    <th>产品体验</th>
    <th>临时需求</th>
    <th>合格</th>
    <th>原因</th>
    <th>提测人</th>
  </tr>
  </thead>

  <%
    if (list != null) {
      for (int i=0;i<list.size();i++) {
        StatsBean stb = (StatsBean) list.get(i);
  %>
  <tbody>
  <tr>
    <td style="width: 8%;"><%=stb.getTime() %></td>
    <td style="width: 6%;"><%=stb.getGroups()%></td>
    <td style="width: 25%;"><a href="/tianyu/servlet/editStatsServlet?id=<%=stb.getId()%>"><%=stb.getContent() %></a></td>
    <td style="width: 8%;"><%=stb.getApkversion() %></td>
    <td style="width: 8%;"><%=stb.getApkplugs() %></td>
    <td style="width: 6%;"><%=stb.getIsrequirement() %></td>
    <td style="width: 6%;"><%=stb.getIsproducts() %></td>
    <td style="width: 6%;"><%=stb.getIsboning() %></td>
    <td style="width: 6%;"><%=stb.getResult() %></td>
    <td style="width: 15%;"><%=stb.getReason() %></td>
    <td style="width: 6%;"><%=stb.getDeveloper() %></td>
  </tr>
  </tbody>
  <%} }%>
</table>

<div>
  <ul class="pagination">
    {% if pagerets.number != 1%}
    <li><a href="?page=1"><span aria-hidden="true">首页</span><span class="sr-only">
    {% endif %}

    {% if pagerets.has_previous %}
    <li><a href="?page={{ pagerets.previous_page_number }}"><span aria-hidden="true">前一页</span><span class="sr-only">Previous</span></a></li>
    <li><a href="?page={{ pagerets.previous_page_number }}"><span>{{pagerets.previous_page_number}}</span></a></li>
    {% endif %}

    <li><a href="?page={{ pagerets.number}}">{{pagerets.number}}</a></li>

    {% if pagerets.has_next %}
    <li><a href="?page={{ pagerets.next_page_number }}"><span>{{pagerets.next_page_number}}</span></a></li>
    <li><a href="?page={{ pagerets.next_page_number }}"><span aria-hidden="true">后一页</span><span class="sr-only">Next</span></a></li>
    {% endif %}

    {% if pagerets.number != pagerets.paginator.num_pages%}
    <li><a href="?page={{ pagerets.paginator.num_pages}}"><span aria-hidden="true">末页</span><span class="sr-only">
    {% endif %}

    <li class="disable"><span> (共{{pagerets.paginator.num_pages}}页,{{pagerets.paginator.count }}条记录）</span></li>
  </ul>
</div>
</div>
</div>
</div>

</body>
<script type="text/javascript">
  function edit()
  {
    $("#mymodel").modal();
  }
  $("#selectedgroup").change(function(){
    var select_value=$("#selectedgroup").val();
    if(select_value == "防御组"){
      self.location.href="fyz";
    }
  });
</script>
</html>

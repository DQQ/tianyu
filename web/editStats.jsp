<%--
  Created by IntelliJ IDEA.
  User: dongqingqing
  Date: 17/9/8
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tianyu.mode.StatsBean" %>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>添加提测信息</title>
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

<%
  StatsBean statsBean = (StatsBean)request.getAttribute("stats");
%>

<script type="text/javascript">
  $(document).ready(function()
  {
    var group = "{{results.groups}}";
    jsSelectitemByValue(document.getElementById("groups"), group);
  });
</script>
<body>
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">请选择本次提测不通过的原因</h4>
      </div>
      <div class="modal-body">
        <div class="checkbox">
          <label><input type="checkbox" id="1" value="没有自测" />没有自测</label>
        </div>
        <div class="checkbox">
          <label><input type="checkbox" id="2" value="写错提测文件或包编译路径">写错提测文件或包编译路径</input></label>
        </div>
        <div class="checkbox">
          <label><input type="checkbox" id="3" value="漏提代码或者把测试调试代码带入">漏提代码或者把测试调试代码带入</input></label>
        </div>
        <div class="checkbox">
          <label><input type="checkbox" id="4" value="提交了别的模块代码">提交了别的模块代码</input></label>
        </div>
        <div class="checkbox">
          <label><input type="checkbox" id="5" value="提交了不在计划内的代码">提交了不在计划内的代码</input></label>
        </div>
        <div class="checkbox">
          <label><input type="checkbox" id="6" value="">其他（自行填写）</input></label>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="setfailreason()" data-dismiss="modal">确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="container-fluid theme-showcase" role="main">
  <div class="row-fluid">
    <div class="span12">
      <ul class="breadcrumb">
        <li><a href="/tianyu/addstats.jsp">返回添加页面</a> <span class="divider"></span></li>
        <li><a href="/tianyu/servlet/allStatsServlet">返回列表页面</a> <span class="divider"></span></li>
      </ul>
    </div>
  </div>

  <div class="form-group">
    <form class="form-horizontal" id="editstats" action="/tianyu/servlet/editStatsServlet" method="post">
      <input type="hidden" name="id" value="<%=statsBean.getId()%>">
      <input type="hidden" name="method" value="update"/>
      <div class="form-group">
        <label for="datetimepicker1" class="col-sm-2 control-label">发布时间</label>
        <div class='col-sm-2'>
          <div class='input-group date form_date'>
            <input class="form-control" type="text" value=<%=statsBean.getTime()%> name="time" id="datetimepicker1" data-date-format="yyyy-mm-dd">
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div>
        </div>
      </div>

      <div class="form-group">
        <label for="groups" class="col-sm-2 control-label">小组</label>
        <div class="dropdown col-sm-10" style="position: relative;width: 200px;">
          <select class="form-control" id="groups" name="groups">
            <option><%=statsBean.getGroups()%></option>
            <option value="CMS">CMS</option>
            <option value="CM">CM</option>
            <option value="CMB">CMB</option>
            <option value ="xcamera">Master相机</option>
            <option value="heytime">好时光</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label for="content" class="col-sm-2 control-label">提测邮件</label>
        <div class="col-sm-10">
          <input class="form-control" id="content" type="text" name="content" style="position: relative;width: 500px;" value="<%=statsBean.getContent()%>">
        </div>
      </div>

      <div class="form-group">
        <label for="apkversion" class="col-sm-2 control-label">版本号（选填）</label>
        <div class="col-sm-10">
          <input class="form-control" id="apkversion" type="text" name="apkversion" style="position: relative;width: 300px;" value=<%=statsBean.getApkversion()%>>
        </div>
      </div>

      <div class="form-group">
        <label for="apkplugs" class="col-sm-2 control-label">插件版本号（选填）</label>
        <div class="col-sm-10">
          <input class="form-control" id="apkplugs" type="text" name="apkversion" style="position: relative;width: 300px;" value=<%=statsBean.getApkplugs()%>>
        </div>
      </div>

      <div class="form-group">
        <label for="developer" class="col-sm-2 control-label">提测人</label>
        <div class="col-sm-10">
          <input class="form-control" id="developer" type="text" name="developer" style="position: relative;width: 300px;" value=<%=statsBean.getDeveloper()%>
                  </div>
        </div>

        <div class="form-group">
          <label for="pd" class="col-sm-2 control-label">产品负责人（选填）</label>
          <div class="col-sm-10">
            <input class="form-control" id="pd" type="text"  name="pd" style="position: relative;width: 300px;" value=<%=statsBean.getPd()%>>
          </div>
        </div>

        <div class="form-group">
          <label for="tester" class="col-sm-2 control-label">记录人（选填）</label>
          <div class="col-sm-10">
            <input class="form-control" id="tester" type="text"  name="tester" style="position: relative;width: 300px;" value=<%=statsBean.getTester()%>>
          </div>
        </div>

        <div class="form-group">
        <label for="isrequirement" class="col-sm-2 control-label">是否需求评审*</label>
        <div class="dropdown col-sm-10" style="position: relative;width: 180px;">
        <select class="form-control" id="isrequirement" name="isrequirement">
        <option><%=statsBean.getIsrequirement()%></option>
        <option value ="是">是</option>
        <option value ="否">否</option>
        </select>
        </div>
        </div>

        <div class="form-group">
        <label for="isproducts" class="col-sm-2 control-label">是否产品体验*</label>
        <div class="dropdown col-sm-10" style="position: relative;width: 180px;">
        <select class="form-control" id="isproducts" name="isproducts">
        <option><%=statsBean.getIsproducts()%></option>
        <option value ="是">是</option>
        <option value ="否">否</option>
        </select>
        </div>
        </div>

        <div class="form-group">
        <label for="isboning" class="col-sm-2 control-label">是否临时需求*</label>
        <div class="dropdown col-sm-10" style="position: relative;width: 180px;">
        <select class="form-control" id="isboning" name="isboning">
        <option><%=statsBean.getIsboning()%></option>
        <option value ="否">否</option>
        <option value ="是">是</option>
        </select>
        </div>
        </div>



        <div class="form-group">
          <label class="col-sm-2 control-label" for="reason" id="reasonlable">不通过原因</label>
          <div class="col-sm-10">
            <textarea class="form-control" id="reason" rows="3"  name="reason" style="position: relative;width: 400px;"><%=statsBean.getReason()%></textarea>
          </div>
        </div>

        </br>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" class="button button-glow button-border button-rounded button-primary"  value="保存" name="submit"/>
            <%--<input type="button" class="button button-glow button-border button-rounded button-caution"  value="删除记录" style="position: relative;left: 120px;"/>--%>
          </div>
        </div>

      </div>
      <%--</form>--%>
      <%--<div class="form-group">--%>
      <%--<form class="form-horizontal" id="editstats2" action="/tianyu/servlet/editStatsServlet" method="post">--%>
      <%--<input type="hidden" name="id" value="<%=statsBean.getId() %>">--%>
      <%--<input type="hidden" name="method" value="delete"/>--%>
      <%--<input type="submit" class="button button-glow button-border button-rounded button-caution"  value="删除记录" style="position: relative;left: 420px;"/>--%>

      <%--</form>--%>
  </div>
</div>
</div>
</body>
<script type="text/javascript">
  $("#ispass").change(function(){
    var select_value=$("#ispass").val();
    if(select_value == "否"){
      $("#myModal").modal();
    }
  });
</script>
</html>

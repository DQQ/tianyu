<%--
  Created by IntelliJ IDEA.
  User: dongqingqing
  Date: 17/9/6
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%
  Object message = request.getAttribute("message");
  if (message != null && !"".equals(message)) {
%>
<script type="text/javascript">
  alert("<%=message%>");
  </script>
<%} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<script type="text/javascript">
  $(document).ready(function()
  {
    var group = getCookie("mygroup");
    jsSelectitemByValue(document.getElementById("group"), group);
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
          <label><input type="checkbox" id="1" value="没有自测">没有自测</input></label>
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
        <li>
          <a href="#">返回测试平台</a> <span class="divider"></span>
        </li>
        <li><a href="/tianyu/servlet/allStatsServlet">查看提测合格率统计</a> <span class="divider"></span></li>
      </ul>
    </div>
  </div>

  <div class="form-group">
    <form class="form-horizontal" id="addstats" action="/tianyu/servlet/addStatsServlet" method="post">

      <div class="form-group">
        <label for="datetimepicker1" class="col-sm-2 control-label">发布时间*</label>
        <div class='col-sm-2'>
          <div class='input-group date form_date'>
            <input class="form-control" type="text" name="time"
                   value="<% out.print(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date())); %>"
                   id="datetimepicker1" data-date-format="yyyy-mm-dd">
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
            </span>
          </div>
        </div>
      </div>

      <div class="form-group">
        <label for="group" class="col-sm-2 control-label">小组*</label>
        <div class="dropdown col-sm-10" style="position: relative;width: 200px;">
          <select class="form-control" id="group" name="group">
            <option value ="all">ALL</option>
            <option value="CMS">CMS</option>
            <option value="CM">CM</option>
            <option value="batterydoctor">电池医生</option>
            <option value ="test">test</option>
            <option value="sjz">手助+市场</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label for="content" class="col-sm-2 control-label">提测邮件*</label>
        <div class="col-sm-10">
          <input class="form-control" id="content" type="text" name="content" style="position: relative;width: 500px;">
        </div>
      </div>

      <div class="form-group">
        <label for="model" class="col-sm-2 control-label">所属模块(选填)</label>
        <div class="col-sm-10">
          <input class="form-control" id="model" type="text" name="model" style="position: relative;width: 300px;">
        </div>
      </div>

      <div class="form-group">
        <label for="developer" class="col-sm-2 control-label">提测人</label>
        <div class="col-sm-10">
          <input class="form-control" id="developer" type="text" name="developer" style="position: relative;width: 300px;">
        </div>
      </div>

      <div class="form-group">
        <label for="pd" class="col-sm-2 control-label">产品负责人（选填）</label>
        <div class="col-sm-10">
          <input class="form-control" id="pd" type="text" name="pd" style="position: relative;width: 300px;">
        </div>
      </div>

      <div class="form-group">
        <label for="tester" class="col-sm-2 control-label">记录人*</label>
        <div class="col-sm-10">
          <input class="form-control" id="tester" type="text" name="tester" style="position: relative;width: 300px;">
        </div>
      </div>

      <div class="form-group">
        <label for="ispass" class="col-sm-2 control-label">是否合格*</label>
        <div class="dropdown col-sm-10" style="position: relative;width: 180px;">
          <select class="form-control" id="ispass" name="ispass">
            <option value ="是">是</option>
            <option value ="待审核">否（待审核）</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label" for="reason" id="reasonlable" style="display: none;">不通过原因</label>
        <div class="col-sm-10">
          <textarea class="form-control" id="reason" rows="3" name="reason" style="display: none;position: relative;width: 500px;"></textarea>
        </div>
      </div>

      </br>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <input type="submit" class="button button-glow button-border button-rounded button-primary" value="添加"/>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
<script type="text/javascript">
  $("#ispass").change(function(){
    var select_value=$("#ispass").val();
    if(select_value == "待审核"){
      $("#myModal").modal();
    }
  });
</script>
</html>

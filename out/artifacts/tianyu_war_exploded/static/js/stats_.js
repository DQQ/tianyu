function smt(id)
{
var mailtitle = document.getElementById("mailtitle");
var developer = document.getElementById("developer");
if (mailtitle.value.length == 0)
{
alert("请填写邮件标题");
return;
}
document.getElementById(id).submit();
}

function setfailreason()
{
$("#reasonlable").show();
$("#reason").show();

var checkbox1 = document.getElementById('1');
var text = ""
if(checkbox1.checked == true){
text = text + checkbox1.value;
}
var checkbox2 = document.getElementById('2');
if(checkbox2.checked == true){
text = text + ", " + checkbox2.value;
}
var checkbox3 = document.getElementById('3');
if(checkbox3.checked == true){
text = text + ", " + checkbox3.value;
}
var checkbox4 = document.getElementById('4');
if(checkbox4.checked == true){
text = text + ", " + checkbox4.value;
}
var checkbox5 = document.getElementById('5');
if(checkbox5.checked == true){
text = text + ", " + checkbox5.value;
}
var checkbox6 = document.getElementById('6');
if(checkbox6.checked == true){
text = text + checkbox6.value;
}
document.getElementById("reason").value=text;
}

$(function () {
	$('#datetimepicker1').datetimepicker({ 
　　    minView: "month", //选择日期后，不会再跳转去选择时分秒 
		language: 'zh-CN',
		autoclose:true 
}).next().on('click', function() {
	$(this).prev().focus();
});
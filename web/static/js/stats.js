function smt(id)
{
var mailtitle = document.getElementById("content");
var developer = document.getElementById("developer");
var tester = document.getElementById("tester");
if (mailtitle.value.length == 0)
{
alert("请填写邮件标题");
return;
}
if(tester.value.length == 0)
{
	alert("记录人为必填项，请填写");
	return;
}
var obj = document.getElementById("group");
var index = obj.selectedIndex;
var group = obj.options[index].value;
addCookie("mygroup", group, 10);
document.getElementById(id).submit();
}

function setfailreason()
{
$("#reasonlable").show();
$("#reason").show();

var checkbox1 = document.getElementById('1');
var text = ""
if(checkbox1.checked == true){
text = text + checkbox1.value + "\n";
}
var checkbox2 = document.getElementById('2');
if(checkbox2.checked == true){
text = text + checkbox2.value + "\n";
}
var checkbox3 = document.getElementById('3');
if(checkbox3.checked == true){
text = text + checkbox3.value + "\n";
}
var checkbox4 = document.getElementById('4');
if(checkbox4.checked == true){
text = text + checkbox4.value + "\n";
}
var checkbox5 = document.getElementById('5');
if(checkbox5.checked == true){
text = text + checkbox5.value + "\n";
}
var checkbox6 = document.getElementById('6');
if(checkbox6.checked == true){
text = text + checkbox6.value;
}
document.getElementById("reason").value=text;
}

$(function () {
	$('#datetimepicker1').datetimepicker({ 
　　    minView: "month", 
		language: 'zh-CN',
		autoclose:true 
}).next().on('click', function() {
	$(this).prev().focus();
});});

$(function () {
	$('#datetimepicker2').datetimepicker({ 
　　    minView: "month", 
		language: 'zh-CN',
		autoclose:true 
}).next().on('click', function() {
	$(this).prev().focus();
});});

function jsSelectitemByValue(objSelect, objitemText){
	for(var i=0; i<objSelect.options.length;i++){
		if(objSelect.options[i].value.toLowerCase() == objitemText.toLowerCase()){
			objSelect.options[i].selected = true;
			break;
		}
	}
}

function jsSelectitemByValue_index(objSelect, objitemText){
	for(var i=0; i<objSelect.options.length;i++){
		if(objSelect.options[i].innerText == objitemText){
			objSelect.options[i].selected = true;
			break;
		}
	}
}

function deletestat(id)
{
var url="http://10.20.224.140:8089/deletestats/" + id;
var ret = confirm("确认删除吗？");
if (ret == true)
{
window.location.href=url;
}
else
{
return;
}
}


function filter(id){
//document.getElementById(id).submit();
var url = 'http://localhost:8080/tianyu/';
var group = document.getElementById('selectedgroup').value;
var dayfrom = document.getElementById('datetimepicker1').value;
var dayto = document.getElementById('datetimepicker2').value;

url = url + group + '/'

if (dayfrom == '' || dayto == ''){
	window.location.href = url;
}
else{
	url = url + '?' + 'dayfrom=' + dayfrom + '&' + 'dayto=' + dayto;
	window.location.href = url;
}

}


function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return ""
}


function addCookie(name,value,expiredays){
             var cookieString=name+"=" + value;
             //判断是否设置过期时间
             if(expiredays>0){
                    var date=new Date();
                    date.setTime(date.getTime()+expiredays*24*3600*1000);
                    cookieString=cookieString+"; expires="+date.toGMTString();
             }
             document.cookie=cookieString;
}

function onchange_select(value){
	var url = 'http://localhost:8080/tianyu/';
	var dayfrom = document.getElementById('datetimepicker1');
	var dayto = document.getElementById('datetimepicker2');
	if (dayfrom == '' || dayto == ''){
		url = url + value;
		window.location.href = url;
	}
	else{
		url = url + value + '/?' + 'dayfrom=' + dayfrom + '&' + 'dayto=' + dayto;
		window.location.href = url;
	}
}
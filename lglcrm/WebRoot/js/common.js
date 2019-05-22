//--------------------------------------------
//------确认提示--------------
function warn(msg) 
{
	if (confirm(msg)==true){
		return true;
	}else{
		return false;
	}
}
//------转URL-----------
function to(url){
	window.location.href=url;
}
//-----按ID提交表单----------
function dosubmit(formID)
{
	document.getElementById(formID).submit();
}
//-----返回上一页-----------
function back()
{
	history.go(-1);
}
//------全选--全不选-------------
function sel(name)
{
  all=document.getElementsByName(name);
  for(i=0;i<all.length;i++)
   all[i].checked=event.srcElement.checked;
}
//-------帮助信息弹框--------------
function help(msg){
	alert('欢迎使用:'+msg);
}

//----------各种验证--------开始---------------------
//-----非空验证------------
function ck_null(ID,infoID)
{
	var str = document.getElementById(ID).value;
	var info = document.getElementById(infoID);
	info.innerHTML = "";
	var reg = /^\S{1,}$/;
	if(reg.test(str) == false){
		info.innerHTML = "*必填";
		return false;
	}
	return true;	
}

//---验证用户名、名字------------
function ck_name(ID,infoID)
{
	var str = document.getElementById(ID).value;
	var info = document.getElementById(infoID);
	info.innerHTML = "";
	var reg = /^\S{1,30}$/;
	if(reg.test(str) == false){
		info.innerHTML = "*必填(不含空格,不超30个字符)";
		return false;
	}
	return true;
}
//----验证密码--------------
function ck_pwd(ID,infoID)
{
	var str = document.getElementById(ID).value;
	var info = document.getElementById(infoID);
		info.innerHTML = "";
	var reg = /^.{1,30}$/;
	if(reg.test(str) == false){
		info.innerHTML = "*必填(不超30个字符)";
		return false;
	}
	return true;
}
//-----验证电话----------
function ck_phone(ID,infoID)
{
	var str = document.getElementById(ID).value;
	var info = document.getElementById(infoID);
		info.innerHTML = "";
	var reg = /^\d{11,}$/;
	if(reg.test(str) == false){
		info.innerHTML = "*必填(11个以上数字)";
		return false;
		}
	return true;	
}
//------验证纯是否为纯数字-------------
function ck_number(ID,infoID)
{
	var str = document.getElementById(ID).value;
	var info = document.getElementById(infoID);
	info.innerHTML = "";
	var reg = /^\d+$/;
	if(reg.test(str) == false){
		info.innerHTML = "*请输入数字";
		return false;
	}
	return true;	
}
//--------当前时间-------------------
function setCurTime(ID)
{
var today,hour,second,minute,year,month,date;
today=new Date();
year = today.getFullYear();
month = today.getMonth()+1;
date = today.getDate();
hour = today.getHours();
minute =today.getMinutes();
second = today.getSeconds();
document.getElementById(ID).value = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second; //显示时间

}

//--------验证附件上传，限制大小-----------------------------------------------
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function ck_fileSize(ID,infoID){
 var upload = document.getElementById(ID);
 var info = document.getElementById(infoID); 
 info.innerHTML = "";
 if (isIE && !upload.files) {   
  var filePath = upload.value;     //得到文件路径 
  var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
  var file = fileSystem.GetFile (filePath);
  fileSize = file.Size;   //文件大小
 }else{   
    fileSize = upload.files[0].size;   //文件大小
 }  
 var size = fileSize/1024/1024;  //以M为单位
 if(size > 9){
	 info.innerHTML = "*当前文件"+size+"M,请上传小于9M的附件。";
	 return false;
 }
 return true;
}
//-------------------------------------------------------------------

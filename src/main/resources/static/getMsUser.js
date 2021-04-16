var body=document.body;
var realName=null;
//var token = window.location.search
var token = localStorage.getItem("token");
function getMsUser(){
	$.ajax({
		type:'POST',
		url:'/msUser/getMsUser?token='+token,
		contentType:'application/json',
		data:JSON.stringify({sysId:token}),
		success:function(res){
			if(res.status == '01'){
				realName=res.rows.realName;
			}else{
				alert("登录超时，请重新登录！")
				window.open("/",'_top')
			}
		}
	})
}
$(function(){
	getMsUser();
})
body.onclick=function(){
	getMsUser();
}
$('a').click(function(){
	if(realName == "" || realName==null){
		$(this).each(function(){
			if($(this).html().indexOf("退出系统") < 0){
				if($(this).html().indexOf("系统设置") < 0){
					if($(this).html().indexOf("个人资料") < 0){
						alert("请在【系统设置-个人资料】里录入该账号真实姓名！")
						window.location.reload()
					} 
				}
			}
		})
	}
})

/*$("#price").change(function(){
	var price = $(this).val();
	$("#price").val((price/1).toFixed(2));
})*/

/*$("#discount").keyup(function(){
	var num = 0;
	var freeMoney=0;
	var count = $("#count").val();
	var discount = $("#discount").val();
	var price = $("#price").val();
	if(discount/1 > 10){
		$("#discount").val("")
		alert("请输入1-10之间的折扣数！")
	}else{
		if(price != ""){
			freeMoney = (price/1-price*discount*0.1).toFixed(2);
			if(count != "" && count != undefined){
				freeMoney = (freeMoney*count/1).toFixed(2);
				$("#freeMoney").val(freeMoney); 
				num = (price*discount*count*0.1).toFixed(2);
			}else{
				$("#freeMoney").val(freeMoney); 
				num = (price*discount/10).toFixed(2);
			}
		}else{
			$("#discount").val("")
			alert("产品和价格不能为空！")
			//alert("请先输入折扣数（0-10直接的整数或者小数）！")
		}
		if(num >= 0){
			$("#amount").val(num)
			$("#trueMoney").val(num)
		}else{
			$("#amount").val("0")
			$("#trueMoney").val("0")
			alert("折扣数过低！")
		}
	}
})*/

String.prototype.startWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substr(0,str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  
String.prototype.endWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substring(this.length-str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  

var Login=function(){
	var b=function(){
		if($.fn.uniform){
			$(":radio.uniform, :checkbox.uniform").uniform()
		}
	};
	var c=function(){
		$(".sign-up").click(
			function(h){
				h.preventDefault();
				$(".login-form").slideUp(350,function(){
					$(".register-form").slideDown(350);
					$(".sign-up").hide()
				})
			}
		);
		$(".back").click(
			function(h){
				h.preventDefault();
				$(".register-form").slideUp(350,function(){$(".login-form").slideDown(350);
				$(".sign-up").show()})
			}
		)
	};
	var g=function(){
		$(".forgot-password-link").click(function(h){h.preventDefault();
		$(".forgot-password-form").slideToggle(200);
		$(".inner-box .close").fadeToggle(200)});
		$(".inner-box .close").click(function(){$(".forgot-password-link").click()})
	};
	var e=function(){
		if($.validator){
			$.extend($.validator.defaults,{errorClass:"has-error",validClass:"has-success",highlight:function(k,i,j){if(k.type==="radio"){this.findByName(k.name).addClass(i).removeClass(j)}else{$(k).addClass(i).removeClass(j)}$(k).closest(".form-group").addClass(i).removeClass(j)},unhighlight:function(k,i,j){if(k.type==="radio"){this.findByName(k.name).removeClass(i).addClass(j)}else{$(k).removeClass(i).addClass(j)}$(k).closest(".form-group").removeClass(i).addClass(j);$(k).closest(".form-group").find('label[generated="true"]').html("")}});
			var h=$.validator.prototype.resetForm;
			$.extend($.validator.prototype,{resetForm:function(){h.call(this);
				this.elements().closest(".form-group").removeClass(this.settings.errorClass+" "+this.settings.validClass)},showLabel:function(j,k){
					var i=this.errorsFor(j);
					if(i.length){i.removeClass(this.settings.validClass).addClass(this.settings.errorClass);
					if(
						i.attr("generated")){
							i.html(k)
						}
					}else{i=$("<"+this.settings.errorElement+"/>").attr({"for":this.idOrName(j),generated:true}).addClass(this.settings.errorClass).addClass("help-block").html(k||"");
						if(this.settings.wrapper){
							i=i.hide().show().wrap("<"+this.settings.wrapper+"/>").parent()
						}
						if(!this.labelContainer.append(i).length){
							if(this.settings.errorPlacement){
								this.settings.errorPlacement(i,$(j))
							}else{
								i.insertAfter(j)
							}
						}
					}
						if(!k&&this.settings.success){
						i.text("");
						if(typeof this.settings.success==="string"){
							i.addClass(this.settings.success)
						}else{
							this.settings.success(i,j)
						}
					}
					this.toShow=this.toShow.add(i)
				}
			})
		}
	};
	//登录
	var d=function(){
		if($.validator){
			$(".login-form").validate({
				invalidHandler:function(i,h){
					NProgress.start();
					$(".login-form .alert-danger").show();
					NProgress.done()
				},submitHandler:function(h){
					var data = {
						userName:$("input[name='username']").val(),
						passWord:$("input[name='password']").val(),
						loginWord:$("input[name='loginWord']").val()
					}
					$.ajax({
						type:'POST',
						data:JSON.stringify(data),
						url:'/msUser/loginMsUser',
						contentType:'application/json',
						success:function(res){
							if(res.status == '01'){
								localStorage.removeItem('token');
								localStorage.setItem("token", res.rows);
								window.location.href="/index?token="+res.rows
							}else{
								alert(res.message)
							}
						},
						error:function(res){
							console.log(res)
					    }
					})
				}
			})
		}
	};
	//密码重置
	var f=function(){
		if($.validator){
			$(".forgot-password-form").validate({
				submitHandler:function(h){
					$(".inner-box").slideUp(350,
						function(){
							$.ajax({
								type:"POST",
								url:"/pmValidate/sendValidationEmail",
								contentType:'application/json',
								data:JSON.stringify({email:$("#email").val()}),
								dateType:"JSON",
								success:function(res){
									console.log(res)
									if(res.status == "01"){
										$(".forgot-password-form").hide();
										$(".forgot-password-link").hide();
										$(".inner-box .close").hide();
										$(".forgot-password-done").show();
										$(".inner-box").slideDown(350)
									}else{
										alert(res.message)
										$(".forgot-password-form").show();
										$(".forgot-password-link").show();
										$(".inner-box .close").show();
										$(".forgot-password-done").hide();
										$(".inner-box").slideDown(350)
									}
								}
							})
						}
					);
					return false
				}
			})
		}
	};
	//注册
	var a=function(){
		if($.validator){
			$(".register-form").validate({
				invalidHandler:function(i,h){
					
				},
				submitHandler:function(h){
					var data = {
						userName:$("input[name='userName']").val(),
						passWord:$("input[name='passWord']").val(),
						passwordConfirm:$("input[name='passwordConfirm']").val(),
						email:$("input[name='email']").val(),
					}
					$.ajax({
						type:'POST',
						data:JSON.stringify(data),
						url:'/msUser/saveMsUser',
						contentType:'application/json',
						success:function(res){
							if(res.status == '01'){
								alert("注册成功！")
								localStorage.removeItem('token');
								localStorage.setItem("token", res.rows);
								window.location.href="/index?token="+res.rows
							}else{
								alert(res.message)
							}
						},
						error:function(res){
							console.log(res)
					    }
					})
				}
			})
		}
	};
	return{
		init:function(){
			b();c();g();e();d();f();a()
		},
	}
}();
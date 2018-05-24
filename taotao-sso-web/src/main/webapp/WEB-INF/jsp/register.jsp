<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
    <title>注册-个人用户</title>
    <link type="text/css" rel="stylesheet" href="/css/regist.personal.css"/>
    <link type="text/css" rel="stylesheet" href="/css/passport.base.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
</head>
<body>
<div class="w" id="logo">
    <div>
    	<a href="http://localhost:8082">
    		<img src="/images/taotao-logo.gif" alt="淘淘商城" width="170" height="60"/>
    	</a> <b></b>
    </div>
</div>

<div class="w" id="regist">
    <div class="mt">
        <ul class="tab">
            <li class="curr">个人用户</li>
        </ul>
        <div class="extra">
        <span>我已经注册，现在就&nbsp;
        	<a href="http://localhost:8088/page/login" class="flk13">登录</a>
        </span>
        </div>
    </div>
    <div class="mc">
        <form id="personRegForm" method="post" onsubmit="return false;">
            <div class="form" onselectstart="return false;">
                <div class="item" id="select-regName">
                    <span class="label"><b class="ftx04">*</b>用户名：</span>

                    <div class="fl item-ifo">
                        <div class="o-intelligent-regName">
                            <input type="text" id="regName" name="username" class="text" tabindex="1" autoComplete="off"
                                   onpaste="return false;" onblur="REGISTER.checkUserName();"/>
                            <i class="i-name"></i>
                            <ul id="intelligent-regName" class="hide"></ul>
                            <label id="regName_succeed" class="blank"></label>
                            <label id="regName_error" class="hide"></label>
                        </div>
                    </div>
                </div>
                <div id="o-password">
                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请设置密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwd" name="password" class="text" tabindex="2"
                                   style="ime-mode:disabled;" autocomplete="off" onblur="REGISTER.checkPassword();"/>
                            <i class="i-pass"></i>
                            <label id="pwd_succeed" class="blank"></label>
                            <label id="pwd_error"></label>
                            <span class="clr"></span>
                        </div>
                    </div>

                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请确认密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwdRepeat" name="pwdRepeat" class="text" tabindex="3"
                                   onpaste="return  false" autocomplete="off" onblur="REGISTER.checkPassword2();"/>
                            <i class="i-pass"></i>
                            <label id="pwdRepeat_succeed" class="blank"></label>
                            <label id="pwdRepeat_error"></label>
                        </div>
                    </div>
					<div class="item" id="dphone">
						<span class="label"><b class="ftx04">*</b>验证手机：</span>

						<div class="fl item-ifo">
							<input type="text" id="phone" maxlength="11" name="phone"
								class="text" tabindex="4"
								autocomplete="off" onblur="REGISTER.checkPhone();"/> <i class="i-phone"></i> <label
								id="phone_succeed" class="blank"></label> <label
								id="phone_error"></label>
						</div>
					</div>
					</div>
                <div class="item item-new">
                    <span class="label">&nbsp;</span>

                    <div class="fl item-ifo">
                        <input type="checkbox" class="checkbox" checked="checked" id="readme"
                               onclick="agreeonProtocol();">
                        <label for="protocol">我已阅读并同意<a href="#" class="blue" id="protocol">《淘淘用户注册协议》</a></label>
                        <span class="clr"></span>
                        <label id="protocol_error" class="error hide">请接受服务条款</label>
                    </div>
                </div>
                <div class="item">
                    <span class="label">&nbsp;</span>
                    <input type="button" class="btn-img btn-regist" id="registsubmit" value="立即注册" tabindex="8"
                           clstag="regist|keycount|personalreg|07" onclick="REGISTER.registerSubmit();"/>
                </div>
            </div>
            <div class="phone">
                <img width="180" height="180" src="/images/phone-bg.jpg">
            </div>
            <span class="clr"></span>
        </form>
    </div>
<script type="text/javascript">
	var REGISTER={
		param:{
			//单点登录系统的url
			surl:""
		},

		checkUserName:function(){
			var passed=true;
			//不能为空检查
			if ($("#regName").val() == "") {
				console.debug("用户名为空");
				//passed=false;
				$("#regName").attr("passed", "false");
			}else{
				$.ajax({
					url : REGISTER.param.surl + "/user/check/"+escape($("#regName").val())+"/1?r=" + Math.random(),
					success:function(data){
						if(data.status == 200){
							console.debug("用户名" + data.msg);
							//passed=true;
							$("#regName").attr("passed", "true");
						}else if(data.status == 400){
							console.debug("用户名" + data.msg);
							//passed=false;
							$("#regName").attr("passed", "false");
						}
					}
				});
			}
				// return passed;
		},
		
		checkPassword:function(){
		var passed=true;
			// 验证密码
			if ($("#pwd").val() == "") {
				console.debug("密码为空");
				//passed=false;
				$("#pwd").attr("passed", "false");
				$("#pwd").blur();
			}else{
				$("#pwd").attr("passed", "true");
				//passed=true;
			}
			// return passed;
		},
		checkPassword2:function(){
		var passed = true;
			// 验证两次密码输入是否一致
			//密码检查
			if ($("#pwd").val() != $("#pwdRepeat").val()) {
				console.debug("确认密码和密码不一致，请重新输入！");
				//passed=false;
				$("#pwdRepeat").attr("passed", "false");
			}else{
				//passed=true;
				console.debug("确认密码和密码相同");
				$("#pwdRepeat").attr("passed", "true");
			}
			// return passed;
		},
		
		checkPhone:function(){
		var passed=true;
			// 验证手机号
			if ($("#phone").val() == "") {
				// $("#phone").html("手机号不能为空");
				console.debug("手机号为空");
				// passed =  false;
				$("#phone").attr("passed", "false");
			}else{
				var phone = /^1(3|4|5|7|8)\d{9}$/;
				if ($("#phone").val().match(phone)) {
					$.ajax({
						url : REGISTER.param.surl + "/user/check/"+escape($("#phone").val())+"/3?r=" + Math.random(),
						success:function(data){
							if(data.status == 200){
								// $("#phone").html(data.msg);
								console.debug("手机号" + data.msg);
								//passed=true;
								$("#phone").attr("passed", "true");
							}else if(data.status == 400){
								// $("#phone").html(data.msg);
								console.debug("手机号" + data.msg);
								//passed=false;
								$("#phone").attr("passed", "false");
							}
						}
					});
				}else{
					console.debug("手机号格式不正确");
					//passed=false;
					$(this).attr("passed", "false");
				}
			}
			// return passed;
		},
		
		doSubmit:function() {
			$.post("/user/register",$("#personRegForm").serialize(), function(data){
				if(data.status == 200){
					console.debug('用户注册成功，请登录！');
					REGISTER.login();
				} else if(data.status == 400){
					console.debug("注册失败");
				}
			});
		},
		login:function() {
			 location.href = "/page/login";
			 return false;
		},
		
		// 所有的文本框都正确后，提交表单
		registerSubmit:function(){
		/* alert("用户名" + this.checkUserName() != true);
		alert("密码" + this.checkPassword() != true);
		alert("手机号" + this.checkPhone() != true);
			if(this.checkUserName() != true){
			}else if(this.checkPassword() != true){
			}else if(this.checkPhone() != true){
			}else{
				REGISTER.doSubmit();
			} */
			/* alert($("#regName").attr("passed"));
			alert($("#pwd").attr("passed"));
			alert($("#pwdRepeat").attr("passed"));
			alert($("#phone").attr("passed")); */
			if($("#regName").attr("passed") == "true" && $("#pwd").attr("passed") == "true" && $("#pwdRepeat").attr("passed") == "true" && $("#phone").attr("passed") == "true"){
				REGISTER.doSubmit();
			}else{
				console.debug("请填写必填字段");
			}
		}
	};
		
</script>
</body>
</html>

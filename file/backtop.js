/**
 * 返回顶部 js
 * @author zhaosx
 */
$(function(){

	//创建一个div  
    var backToTop = document.createElement("div");  
    //添加到页面  
    document.body.appendChild(backToTop);
	
	backToTop.id="back_to_top";

	backToTop.style.width = '40px';
	backToTop.style.height = '40px';
	backToTop.style.position = 'fixed';
	backToTop.style.bottom = '50px';
	backToTop.style.right = '40px';
	backToTop.style.borderRadius = '3px 3px 3px 3px';
	backToTop.style.textDecoration = 'none';
	backToTop.style.textAlign = 'center';
	backToTop.style.display = 'none';
	backToTop.style.backgroundColor = '#B3B3B3';
	backToTop.style.cursor = 'pointer';
	
	var backtop_png_base64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAACXBIWXMAAC4jAAAuIwF4pT92AAAAoElEQVR42u3WMQqAIBTGcQf3Fi/SZTpEdYLuVnNDUCfqpSDkEAi9z3L4hP8i8viBgxoRMTVnCCSQQAIJhA60NQN73+abfa42YMCdcq8DhUTiQlPEwZBI3Bj3HBKJxpkEuSOQJXBQpBY3ZM6qkSVxEGRpnBr5BU6F/Ar3Gpkb2AFxT8hFC2x8KxCXIsOb3SKu2PI/SCCBBBJIIIEEEkjgX11a9O6YNYlr3gAAAABJRU5ErkJggg==";
	
	backToTop.innerHTML = '<div id="bttt" style="display:block;position:relative;top: 2px;line-height:40px;height:40px;background:url(' + backtop_png_base64 + ') no-repeat;" title="to top"></div><div id="bttd" style="display:none;font-size:13px;font-family: Tahoma;color:#fff;line-height:17px;width:37px;padding-top: 3px;"title="返回顶部">回到顶部</div>';

	backToTop.onmouseover = function(){
		document.getElementById('bttt').style.display='none';
		document.getElementById('bttd').style.display='block';
	}

	backToTop.onmouseout = function(){
		document.getElementById('bttt').style.display='block';
		document.getElementById('bttd').style.display='none';
	}

	// 滚动窗口来判断按钮显示或隐藏
	$(window).scroll(function() {
		if ($(this).scrollTop() > 200) {
			$('#back_to_top').fadeIn(400);
		} else {
			$('#back_to_top').stop().fadeOut(400);
		}
	});
	// jQuery实现动画滚动
	$('#back_to_top').click(function(event) {
		event.preventDefault();
		$('html, body').animate({scrollTop: 0}, 200);
	});

});
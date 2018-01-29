
$(function (){
	
	var boxProduct = document.querySelectorAll('.box-product > div');
	console.log(boxProduct)
	for(var i = 0; i<boxProduct.length; i++){
		boxProduct[i].onmouseover = function(){
			var H = this.offsetHeight;
			var W = this.offsetWidth;
			
			var hoverBox = this.querySelector('.hover-box');
			hoverBox.style.height = H+'px';
			hoverBox.style.width = W+'px';
			
			var more = hoverBox.querySelector('i');
			if(more.classList.contains('invisible')){
				more.classList.remove('invisible');
			}
		}
		boxProduct[i].onmouseout = function(){
			var hoverBox = this.querySelector('.hover-box');
			var more = hoverBox.querySelector('i');
			
			if(!more.classList.contains('invisible')){
				more.classList.add('invisible');
			}
		}
	}
});
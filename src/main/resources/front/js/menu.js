


window.onload = function (){
	var searchMobile = $('#search-mobile');
	var navMobile = document.querySelector('#nav-mobile');
	
	
	navMobile.style.left = '-'+navMobile.offsetWidth+'px';
	
	if (window.innerWidth>767) {
		searchMobile.addClass('invisible');
	}
	else{
		searchMobile.removeClass('invisible');
	}
}


window.onresize = function(event) {
	var searchMobile = $('#search-mobile');
	if (window.innerWidth>767) {
		searchMobile.addClass('invisible');
	}
	else{
		searchMobile.removeClass('invisible');
	}
};



window.onload = function (){
	var searchMobile = $('#search-mobile');
	var navMobile = document.querySelector('#nav-mobile');
	
	console.log(navMobile.offsetWidth);
	var userMenuSize = navMobile.offsetWidth; 
	navMobile.style.left = '-'+userMenuSize+'px';
	
	if (window.innerWidth>767) {
		searchMobile.addClass('invisible');
	}
	else{
		searchMobile.removeClass('invisible');
	}
	
	
	var btnMobile = $('#button-mobile');
	btnMobile.on('click', function(){
		console.log('test mobile');
		if (navMobile.classList.contains('hors-ecran')) {
			console.log(navMobile.offsetWidth);
			navMobile.classList.remove('hors-ecran');
			navMobile.style.left = '+0px';
		}
		else {
			console.log(navMobile.offsetWidth);
			navMobile.classList.add('hors-ecran');
			navMobile.style.left = '-'+userMenuSize+'px';
		}
		
	})
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


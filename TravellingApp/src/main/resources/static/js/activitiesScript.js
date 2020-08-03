$(function() {
			$(".video").videoPopup();
		});

		var x = document.getElementsByClassName("container");

		var min = document.getElementById("minPrice");
		var max = document.getElementById("maxPrice");

		min.addEventListener('change', checkMin);
		max.addEventListener('change', checkMax);

		function checkMin() {
			for (let i = 0; i < x.length; i++) {
				if (x[i].childNodes[1].value < parseInt(min.value)) {
					x[i].style.display = 'none';
				} else {
					x[i].style.display = "";
				}
			}
		}

		function checkMax() {
			for (let i = 0; i < x.length; i++) {
				if (x[i].childNodes[1].value > parseInt(max.value)) {
					x[i].style.display = 'none';
				} else {
					x[i].style.display = "";
				}
			}
		}
		
// 		<span class="fa fa-star checked"></span> <span
// 		class="fa fa-star checked"></span> <span
// 		class="fa fa-star checked"></span> <span
// 		class="fa fa-star checked"></span> <span class="fa fa-star"></span>
		
		function calcStars(var1, var2){
			console.log(var1);
			console.log(var2);
		}
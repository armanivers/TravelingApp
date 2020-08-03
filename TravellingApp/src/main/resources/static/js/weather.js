//INCLUDE THIS IN THE HEADER, NEEDED FOR JS SCRIPTS WHICH USE $ (Jquery)!!!
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

<script>
Old method to get weather API information
const cities = document.getElementsByClassName("temperature");
//const icons = document.getElementsByClassName("icon");

for(i = 0 ; i < cities.length ; i++){
	//console.log(cities[i].id);
	//icons[i].innerHTML = "<img src=\"static/img/weatherIcons/01d@2x.png\" th:src=\"@{img/weatherIcons/01d@2x.png}\" alt='weather'/>";
	json(cities[i].id,cities[i]);
}

function json (id,city) { $.getJSON('http://api.openweathermap.org/data/2.5/weather?q='+id+'&appid=be4c21b762990f1de89ead12a83c90ff', function(data) {

    	//substracting 273.15 to get value in celsius
    	//console.log(Math.round(data.main.temp-273.15));
    	var result = Math.round(data.main.temp-273.15);
    	var celsius = '&#8451'
    	
    	//city.innerHTML= "<img "+"src='static/img/weatherIcons/01d@2x.png' " +"th:src='@{img/weatherIcons/01d@2x.png}'' "+"/>";
    	city.innerHTML = "<p>"+result + celsius+"</p>";

})};
</script>
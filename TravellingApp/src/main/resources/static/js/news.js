//	<h2>Aktuelle Nachrichten (Top 4)- Kategorie: Gesundheit</h2>
//	
//	<div id="test">
//	<p id="us"></p>
//	<p id="de"></p>
//	</div>	


<script>
	const container = document.getElementById("test");
	
	var e1 = container.getElementsByTagName("p");
	
	var c1 = e1[0].id;
	var c2 = e1[1].id;
	
	json(c1);
	json(c2);
	
    function json (x) { $.getJSON('https://newsapi.org/v2/top-headlines?country='+x+'&category=health&apiKey=8f5192b1865d46718199cf36af7cc73c', function(data) {
        for(var i = 0; i < 4 ; i++){
        	// Lieber elemente nutzen statt innerHTML!
        	// var p = document.createElement("p");
        	
        	container.innerHTML += "Artikel "+i + " Land " +x +"<br />" +"Title = "+data.articles[i].title +"<br />" + " desc = " + data.articles[i].description +"<br />"; 
        	
        	// Tests
        	// console.log("Article " +i)
        	// console.log(data.articles[i].title)
        	// console.log(data.articles[i].description)
        }
        container.innerHTML += "<br />";
    });}
    </script>
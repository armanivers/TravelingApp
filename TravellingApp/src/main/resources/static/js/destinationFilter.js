////////////////////////////////////////////////////////////////////////////
// destinationFilter.js ////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

var countries = Object();

countries['Asia'] = '|China|Russia|Japan';
countries['Europe'] = '|Germany|Turkey|Poland|Bulgaria|France|Netherlands|United Kingdom';
countries['Middle East'] = '|United Arab Emirates';
countries['North America'] = '|USA';

////////////////////////////////////////////////////////////////////////////

var cities = Object();

// Asia
cities['China'] = '|Beijing||Shanghai';
cities['Japan'] = '|Seoul||Daegu';
cities['Russia'] = '|Moscow||Novosibirsk';

// Europe
cities['Bulgaria'] = '|Sofia||Plovdiv';
cities['Germany'] = '|Berlin||Duesseldorf|Muenchen';
cities['Netherlands'] = '|Amsterdam||The Hague';
cities['Poland'] = '|Warsaw||Dolnoslaskie';
cities['Turkey'] = '|Istanbul||Ankara';
cities['United Kingdom'] = '|London||Liverpool';
cities['France'] = '|Paris||Brest';

// Middle East
cities['United Arab Emirates'] = '|Dubai||Abu Dhabi';

// North America
cities['USA'] = '|Washington DC||New York';

/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////

function setContinents() {
	for (continent in countries)
		document.write('<option value="' + continent + '">' + continent
				+ '</option>');
}

function set_country(oContinentSel, oCountrySel, oCity_Sel) {
	var countryArr;
	oCountrySel.length = 0;
	oCity_Sel.length = 0;
	var continent = oContinentSel.options[oContinentSel.selectedIndex].text;
	if (countries[continent]) {
		oCountrySel.disabled = false;
		oCity_Sel.disabled = true;
		oCountrySel.options[0] = new Option('SELECT COUNTRY', '');
		countryArr = countries[continent].split('|');
		for (var i = 0; i < countryArr.length; i++)
			oCountrySel.options[i + 1] = new Option(countryArr[i],
					countryArr[i]);
		document.getElementById('continentplace').innerHTML = continent;
		document.getElementById('continentplaceu').setAttribute("value",
				continent);
		document.getElementById('countryplaceu').setAttribute("value", null);
		document.getElementById('cityplaceu').setAttribute("value", null);
		document.getElementById('countryplace').innerHTML = "";
		document.getElementById('cityplace').innerHTML = "";
		
		document.getElementById('cityplace').innerHTML = '';
	} else
		oCountrySel.disabled = true;
}

function set_city(oCountrySel, oCity_Sel) {
	var city_Arr;
	oCity_Sel.length = 0;
	var country = oCountrySel.options[oCountrySel.selectedIndex].text;
	if (cities[country]) {
		oCity_Sel.disabled = false;
		oCity_Sel.options[0] = new Option('SELECT CITY', '');
		city_Arr = cities[country].split('|');
		for (var i = 0; i < city_Arr.length; i++)
			oCity_Sel.options[i + 1] = new Option(city_Arr[i], city_Arr[i]);
		document.getElementById('countryplace').innerHTML = country;
		document.getElementById('countryplaceu').setAttribute("value", country);
	} else
		oCity_Sel.disabled = true;
}

function print_city(oCountrySel, oCity_Sel) {
	var country = oCountrySel.options[oCountrySel.selectedIndex].text;
	var city = oCity_Sel.options[oCity_Sel.selectedIndex].text;
	if (city && cities[country].indexOf(city) != -1) {
		document.getElementById('cityplace').innerHTML = city;
		document.getElementById('cityplaceu').setAttribute("value", city);
	} else
		document.getElementById('countryplace').innerHTML = country;
}
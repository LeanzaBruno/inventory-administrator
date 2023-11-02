const API_URL = "http://localhost:8080/articles";
fetch(API_URL)
.then(response => response.json())
.then(data => {
     const articles = data;
     table = document.querySelector(".table");

     for(var i = 0 ; i < articles.length ; ++i){
        var row = table.insertRow();

        var cellNumber = row.insertCell();
        cellNumber.appendChild(document.createTextNode(String(i+1)));

        var cellDescription = row.insertCell();
        cellDescription.appendChild(document.createTextNode(articles[i].description));

        var cellPrice = row.insertCell();
        cellPrice.appendChild(document.createTextNode("$ " + articles[i].price ));

        var cellIVA = row.insertCell();
        const iva = String(articles[i].iva * 100) + " %";
        cellIVA.appendChild(document.createTextNode(iva));

        var cellStock = row.insertCell();
        cellStock.appendChild(document.createTextNode(articles[i].stock));
     }
 });

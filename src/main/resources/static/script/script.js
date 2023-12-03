const API_URL = "http://localhost:8080/api/articles";
const form = document.querySelector('#form');
const modal = new bootstrap.Modal('#form');

( () => {
   document.querySelector('#add-button').addEventListener('click', () => {
      form.classList.remove('edit-mode');
      form.classList.add('create-mode');
      clearForm();
   });

   document.querySelector('#delete-btn').addEventListener('click', () => {
      if(!confirm("Seguro que desea eliminar permanentemente este artículo?")) return;
      const articleCode = parseInt(form.getAttribute('article-code'));
      deleteArticle(articleCode);
      reloadTable();
   });
/*
   document.querySelector('#update-btn').addEventListener('click', () => {
      const json = toJSON(Object.fromEntries(new FormData(form).entries()));
      putArticle(json);
      console.log("Artículo actualizado");
      reloadTable();
   });
*/
   document.querySelector('#insert-btn').addEventListener('click', () => {
      const json = toJSON(Object.fromEntries(new FormData(form).entries()));
      postArticle(json);
      reloadTable();
   });

   reloadTable();
})();


/**
 * Fills the table with the articles
 * @param {*} articles 
 */
function reloadTable(){
   modal.hide();
   clearTable();
   getArticles().then(response => response.map( article => prepareRow(tbody.insertRow(), article) ));
}


/**
 * Deletes all rows from the table
 */
function clearTable(){
   if(tbody != null ) tbody.remove();
   const newBody = document.createElement('tbody');
   newBody.setAttribute('id', 'tbody');
   newBody.classList.add('align-center');
   table.appendChild(newBody);
}


/**
 * Clears form for the create mode
 */
function clearForm(){
   form.querySelector('#description').value = "";
   form.querySelector('#price').value = "";
   form.querySelector('#stock').value = "";
   form.querySelector('#iva').value = 21.0;
}


/**
 * Fills row with the article's data
 */
function prepareRow(row, article){
   var cellCode = row.insertCell();
   cellCode.appendChild(document.createTextNode(String(article.code)));

   var cellDescription = row.insertCell();
   cellDescription.appendChild(document.createTextNode(article.description));

   var cellPrice = row.insertCell();
   cellPrice.appendChild(document.createTextNode("$ " + article.price ));

   var cellIVA = row.insertCell();
   cellIVA.appendChild(document.createTextNode(String(article.iva) + " %"));

   var cellStock = row.insertCell();
   cellStock.appendChild(document.createTextNode(article.stock));


   row.setAttribute("data-bs-toggle", "modal");
   row.setAttribute("data-bs-target", "#form");
   row.addEventListener('click', () => {
      form.classList.remove('create-mode');
      form.classList.add('edit-mode');
      form.setAttribute('article-code', article.code);
      form.querySelector('#description').value = article.description;
      form.querySelector('#price').value = article.price;
      form.querySelector('#stock').value = article.stock;
      form.querySelector('#iva').value = article.iva;
   });
}



/**
 * 
 * CONNECTION WITH THE REST API
 * 
 * */


/**
 * Gets all the articles from the api
 * @returns an array containing the articles
 */
async function getArticles(){
   const array = [];
   await fetch(API_URL)
      .then( response => response.json() )
      .then( articles => articles.map( article => array.push(article)) )
      .catch( err => console.log("Solicitud fallida", err));
   return array;
}
    


/**
 * Parses an object to json format
 * @param {*} obj the object 
 * @returns the object in json format
 */
function toJSON(obj){
   obj.price = parseFloat(obj.price);
   obj.iva = parseFloat(obj.iva);
   obj.stock = parseInt(obj.stock);
   return obj;
}
    


/**
 * Obtains an article by its index from the rest api
 * @param {} index the article's index
 */
function getArticle(index){
   try{
      fetch(API_URL + "/" + (index-1))
         .then( response => response.json() )
         .then( article => console.log(article) );
   }catch(error){ console.log("Error: ", error); }
}


/**
 * Deletes article from the api
 * @param {int} code 
 */
function deleteArticle(code){
   try{
      fetch(API_URL + '/' + code, { method: 'DELETE' });
   }
   catch(error){ console.err("ERROR: Couldn't remove article")}
}


function putArticle(json){
   try{
      fetch(API_URL, { method: 'PUT',
                             headers: {'Content-type': 'application/json'},
                             body: JSON.stringify(json) });
   } catch(error){ console.log("Error: ", error); }
}


/**
 * Creates or updates an article into the rest api
 * @param {JSON} json json containing the article's data
 */
function postArticle(json){
   try{
      fetch(API_URL, { method: 'POST',
                             headers: {'Content-type': 'application/json'},
                             body: JSON.stringify(json) });
   } catch(error){ console.log("Error: ", error); }
}
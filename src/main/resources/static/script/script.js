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
      deleteArticle(form.getAttribute('article-code'));
      reloadTable();
   });

   document.querySelector('#update-btn').addEventListener('click', () => {
      const json = toJSON(Object.fromEntries(new FormData(form).entries()));
      putArticle(form.getAttribute('article-code'), json);
      reloadTable();
   });

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
   var cellDescription = row.insertCell();
   cellDescription.appendChild(document.createTextNode(article.description));

   var cellPrice = row.insertCell();
   cellPrice.appendChild(document.createTextNode(article.price ));
   cellPrice.classList.add('text-center');

   var cellIVA = row.insertCell();
   cellIVA.appendChild(document.createTextNode(String(article.iva)));
   cellIVA.classList.add('text-center');

   var cellStock = row.insertCell();
   cellStock.appendChild(document.createTextNode(article.stock));
   cellStock.classList.add('text-center');


   row.setAttribute("data-bs-toggle", "modal");
   row.setAttribute("data-bs-target", "#form");
   row.addEventListener('click', () => {
      form.classList.remove('create-mode');
      form.classList.add('edit-mode');
      form.setAttribute('article-code', article.code);
      form.querySelector('#code').value = article.code;
      form.querySelector('#description').value = article.description;
      form.querySelector('#price').value = "$" + article.price;
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
 * Converts an article object into json format
 * @param {Article} article the article 
 * @returns the article in json format
 */
function toJSON(article){
   article.price = parseFloat(article.price);
   article.iva = parseFloat(article.iva);
   article.stock = parseInt(article.stock);
   return article;
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
 * Sends a delete request to the api
 * @param {int} code article's code
 */
function deleteArticle(code){
   const url = API_URL + '/' + code;
   try{
      fetch(url, { method: 'DELETE' });
   }
   catch(error){ console.err("ERROR: Couldn't remove article")}
}


/**
 * Sends an update request to the api
 * @param {int} code article's code
 * @param {JSON} json json with data
 */
function putArticle(code, json){
   const url = API_URL + '/' + code;
   try{
      fetch(url, { method: 'PUT',
                   headers: {'Content-type': 'application/json'},
                   body: JSON.stringify(json) });
   } catch(error){ console.log("Error: ", error); }
}


/**
 * Sends a crate request to the api
 * @param {JSON} json json containing the article's data
 */
function postArticle(json){
   try{
      fetch(API_URL, { method: 'POST',
                       headers: {'Content-type': 'application/json'},
                       body: JSON.stringify(json) });
   } catch(error){ console.log("Error: ", error); }
}
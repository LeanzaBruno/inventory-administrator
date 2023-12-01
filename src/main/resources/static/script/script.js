const API_URL = "http://localhost:8080/api/articles";
const form = document.querySelector('#form');
const modal = new bootstrap.Modal('#form');

( () => {
   document.querySelector('#add-button').addEventListener('click', () => {
      form.classList.remove('edit-mode');
      form.classList.add('create-mode');
      clearForm();
   });

/*
   document.querySelector('#delete-btn').addEventListener('click', () => {
      const articleIndex = parseInt(deleteArticle(form.getAttribute('data')));
      deleteArticle(articleIndex);
      console.log("Artículo eliminado");
      reloadTable();
   });
   document.querySelector('#update-btn').addEventListener('click', () => {
      const json = toJSON(Object.fromEntries(new FormData(form).entries()));
      putArticle(json);
      console.log("Artículo actualizado");
      reloadTable();
   });
   document.querySelector('#insert-btn').addEventListener('click', () => {
      const json = toJSON(Object.fromEntries(new FormData(form).entries()));
      postArticle(json);
      console.log("Artículo agregado");
      reloadTable();
   });
   */

   reloadTable();
})();


/**
 * Fills the table with the articles
 * @param {*} articles 
 */
function reloadTable(){
   modal.hide();
   clearTable();
   getArticles().then(response => response.map( (article,index) => prepareRow(tbody.insertRow(), article, index) ));
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
function prepareRow(row, article, index){
   var cellNumber = row.insertCell();
   cellNumber.appendChild(document.createTextNode(String(index+1)));

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
      form.setAttribute('data-article', index);
      form.querySelector('#description').value = article.description;
      form.querySelector('#price').value = article.price;
      form.querySelector('#stock').value = article.stock;
      form.querySelector('#iva').value = article.iva;
   });
}



/**
 * This function takes care of the form related events (create, modify, delete).
 * @param {Event} event 
 */
function handleSubmit(event){
   event.preventDefault();

   const selectedArticle = parseInt(event.target.getAttribute('data-article'));

   if(!deleteArticle){
   }
   else{


   }


   fillTable();
}


/**
 * 
 * API FUNCTIONS
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
 * @param {int} index 
 */
function deleteArticle(index){
   try{
      fetch(API_URL + '/' + index, { method: 'DELETE' })
         .then(res => res.text() )
         .then(res => console.log(res));
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
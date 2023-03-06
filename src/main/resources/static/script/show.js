const UrlParams = new URLSearchParams(window.location.search);
const idFoto = UrlParams.get('id');

const btnComment = document.getElementById('btnComment')


function showFoto(idFoto) {
const url = `http://localhost:8080/api/photos/+${photoId}`;

axios.get(url).then(response => {
    console.log("richiesta ok", response);
    const photo = response.data;
    let categorie = photo.categorie;
    let commenti = photo.commenti;
 
    document.getElementById("id-foto").innerHTML = photo.id;
    document.getElementById("titolo-foto").innerHTML = photo.titolo;
    document.getElementById("tag-foto").innerHTML = photo.tag;
    document.getElementById("descrizione-foto").innerHTML = photo.descrizione;
    document.getElementById("url-foto").innerHTML = photo.url;
    if(categorie.length > 0){
        document.getElementById("categorie-foto").innerHTML = "Categorie: ";
        categorie.forEach(category => {
            document.getElementById("singola-categoria").innerHTML += `<li>${category.nome}</li>`;
        });
    };
    if(commenti.length > 0){
        document.getElementById("commenti-foto").innerHTML = "Commenti: ";
        commenti.forEach(comment => {
            document.getElementById("singolo-commento").innerHTML += `
             <li>${comment.text}</li>
            `;
        });
    };
}).catch(error => {
    console.log("richiesta errata", error);
})
}

function scriviCommento(photoId) {
  const textComment = document.querySelector('#text').value;

  axios.post(`http://localhost:8080/api/comments/create/${photoId}`, {
    text: textComment
  })
    .then((res) => {
      console.log(res.data);
      window.location.reload(); 
    })
    .catch((err) => {
      console.error('Errore nella richiesta', err.response.data.errors);
      document.getElementById('text_err').innerHTML = '';
     	showValidationErrors(err.response.data.errors)
    });
}


function showValidationErrors(error) {
	const errorList = error;
	errorList.forEach(error => {
		document.getElementById('text_err').innerHTML += "<li>" + error.defaultMessage + "</li>";
	})

}

btnComment.onclick = function(){scriviCommento(photoId)};

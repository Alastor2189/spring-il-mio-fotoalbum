let photos;
let cardFoto = document.getElementById("card-foto");
const input = document.getElementById('value') 

function fotoList() {
    axios.get( `http://localhost:8080/api/photos/search=${filtro}`).then((response) => {
        photos = response.data;
        if(photos.length > 0) {
        cardFoto.innerHTML='';
        photos.forEach(photo => {
			if(photo.length> 0 ) {
            	cardFoto.innerHTML += '';
             	photos.forEach(photo =>) {
					 	if (photo.visibilità) {
							cardFoto.innerHTML +=	 
						 
            `<div class="card-foto">
		        <img src="${photo.url}" class="foto" alt="${photo.nome}">
		        <div class="card-body">
		          <h5 class="titolo">Title: ${photo.nome}</h5>
		          <p class="testo">${photo.descrizione}</p>
		          <p class="tag">Tags: ${photo.tag}</p>
		         <div>
		         <a href="/fotoList" class="btn btn-primary">Torna indietro</a>
          	</div>`
          	}}}
        	
        }) ;
        } else {
			cardFoto.innerHTML='<h2>Nessuna foto presente</h2>';
		}
    }).catch((error) => {
    })
};

fotoList();

const element = document.getElementById('filtroBottone');

function search(){
	const filtro = input.value;
    axios.get(`http://localhost:8080/api/photos?search=${filtro}`).then((response) =>{
		//console.log(response);
        photos = response.data;
        //console.log(photos);
        cardFoto.innerHTML='';
        photos.forEach(photo => {
            //console.log(photo);
            if(photo.visibilità){
            cardFoto.innerHTML += `
             <tr>
                <td>${photo.id}</td>
                <td><a href="/show?id=${photo.id}">${photo.titolo}</a></td>
                <td>${photo.tag}</td>
                <td>${photo.descrizione}</td>
                <td>${photo.url}</td>
          </tr>`;
			}
    	});
	});
}
element.onclick = function(){search()};
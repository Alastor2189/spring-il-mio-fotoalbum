let photos;
let cardFoto = document.getElementById("card-foto");

function fotoList() {
    axios.get('http://localhost:8080/api/photos').then((response) => {
        photos = response.data;
        cardFoto.innerHTML='';
        photos.forEach(photo => {
            //console.log(photo);
            cardFoto.innerHTML += `
            <tr>
                <td>${photo.id}</td>
                <td><a href="/show?id=${photo.id}">${photo.titolo}</a></td>
                <td>${photo.tag}</td>
                <td>${photo.descrizione}</td>
                <td>${photo.url}</td>
                <td>${photo.visibilità}</td>
          </tr>`;
        });

    }).catch((error) => {
    })
};

fotoList();
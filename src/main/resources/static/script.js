document.getElementById('fetch-libros').addEventListener('click', () => {
    fetch('/libros')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const librosTableBody = document.querySelector('#libros-table tbody');
            librosTableBody.innerHTML = ''; // Limpiar tabla anterior
            data.forEach(libro => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${libro.titulo}</td>
                    <td>${libro.autor.nombre}</td>
                `;
                librosTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al buscar libros:', error);
            alert('No se pudieron cargar los libros.');
        });
});

document.getElementById('fetch-autores').addEventListener('click', () => {
    const anio = document.getElementById('anio-input').value;
    if (!anio) {
        alert('Por favor, introduce un año.');
        return;
    }
    fetch(`/autores/vivos?anio=${anio}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const autoresTableBody = document.querySelector('#autores-table tbody');
            autoresTableBody.innerHTML = ''; // Limpiar tabla anterior
            if (data.length === 0) {
                const row = document.createElement('tr');
                const cell = document.createElement('td');
                cell.colSpan = 3;
                cell.textContent = 'No se encontraron autores vivos para el año especificado.';
                row.appendChild(cell);
                autoresTableBody.appendChild(row);
            } else {
                data.forEach(autor => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${autor.nombre}</td>
                        <td>${autor.fechaNacimiento}</td>
                        <td>${autor.fechaFallecimiento || 'Presente'}</td>
                    `;
                    autoresTableBody.appendChild(row);
                });
            }
        })
        .catch(error => {
            console.error('Error al buscar autores:', error);
            alert('No se pudieron cargar los autores.');
        });
});

// --- Nuevo código para buscar por título ---
document.getElementById('btnBuscar').addEventListener('click', async () => {
  const salida = document.getElementById('salida');
  const titulo = document.getElementById('tituloInput').value.trim();
  if (!titulo) { 
    salida.textContent = 'Ingresa un título.'; 
    return; 
  }
  salida.textContent = 'Buscando...';
  try {
    const res = await fetch(`/libros/buscar?titulo=${encodeURIComponent(titulo)}`, {
      method: 'POST'
    });
    const data = await res.json();

    if (!res.ok || data.error) {
      throw new Error(data.error || 'Error al guardar el libro.');
    }
    
    const libro = data.libro;
    const autor = libro.autor?.nombre ?? 'Desconocido';
    salida.textContent = `${data.mensaje}: 📘 ${libro.titulo} | Idioma: ${libro.idioma} | Descargas: ${libro.numeroDeDescargas} | Autor: ${autor}`;
  } catch (e) {
    salida.textContent = '❌ ' + e.message;
  }
});

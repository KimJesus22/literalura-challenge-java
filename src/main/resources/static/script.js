document.addEventListener('DOMContentLoaded', () => {

    const themeToggle = document.getElementById('theme-toggle');
    const body = document.body;

    // Aplicar el tema guardado al cargar la página
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
        body.classList.add(savedTheme);
    }

    if (themeToggle) {
        themeToggle.addEventListener('click', () => {
            if (body.classList.contains('dark-mode')) {
                body.classList.remove('dark-mode');
                localStorage.setItem('theme', 'light-mode');
            } else {
                body.classList.add('dark-mode');
                localStorage.setItem('theme', 'dark-mode');
            }
        });
    }

    // --- Lógica existente ---

    document.getElementById('fetch-libros').addEventListener('click', () => {
        fetch('/libros')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#libros-table tbody');
                tableBody.innerHTML = '';
                data.forEach(libro => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `<td>${libro.titulo}</td><td>${libro.autor.nombre}</td>`;
                });
            })
            .catch(error => console.error('Error al buscar libros:', error));
    });

    document.getElementById('fetch-autores').addEventListener('click', () => {
        const anio = document.getElementById('anio-input').value;
        if (!anio) {
            alert('Por favor, introduce un año.');
            return;
        }
        fetch(`/autores/vivos?anio=${anio}`)
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#autores-table tbody');
                tableBody.innerHTML = '';
                data.forEach(autor => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `<td>${autor.nombre}</td><td>${autor.fechaNacimiento}</td><td>${autor.fechaFallecimiento || 'Presente'}</td>`;
                });
            })
            .catch(error => console.error('Error al buscar autores:', error));
    });

    document.getElementById('btnBuscar').addEventListener('click', async () => {
        const salida = document.getElementById('salida');
        const titulo = document.getElementById('tituloInput').value.trim();
        if (!titulo) {
            salida.textContent = 'Ingresa un título.';
            return;
        }
        salida.textContent = 'Buscando...';
        try {
            const res = await fetch('/libros/buscar', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ titulo: titulo })
            });
            const data = await res.json();
            if (!res.ok) throw new Error(data.titulo || data.error || 'Error desconocido');
            const libro = data.libro;
            salida.textContent = `${data.mensaje}: 📘 ${libro.titulo} | Autor: ${libro.autor.nombre}`;
        } catch (e) {
            salida.textContent = '❌ ' + e.message;
        }
    });

    // --- Nueva funcionalidad ---

    // Cargar y mostrar el Top 10 de libros
    document.getElementById('fetch-top10').addEventListener('click', () => {
        fetch('/libros/top10')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#top10-table tbody');
                tableBody.innerHTML = '';
                data.forEach(libro => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `<td>${libro.titulo}</td><td>${libro.autor.nombre}</td><td>${libro.numeroDeDescargas}</td>`;
                });
            })
            .catch(error => console.error('Error al buscar el top 10 de libros:', error));
    });

    // Cargar idiomas en el select
    const idiomaSelect = document.getElementById('idioma-select');
    const idiomaMap = {
        'en': 'Inglés',
        'es': 'Español',
        'fr': 'Francés',
        'pt': 'Portugués'
    };

    function cargarIdiomas() {
        fetch('/libros/idiomas')
            .then(response => response.json())
            .then(data => {
                idiomaSelect.innerHTML = '<option value="" disabled selected>Selecciona idioma</option>';
                data.forEach(idioma => {
                    if (idioma.toLowerCase() !== 'hu') { // Filtro adicional en el frontend
                        const option = document.createElement('option');
                        option.value = idioma;
                        option.textContent = idiomaMap[idioma] || idioma;
                        idiomaSelect.appendChild(option);
                    }
                });
            })
            .catch(error => console.error('Error al cargar idiomas:', error));
    }
    cargarIdiomas(); // Cargar al iniciar

    // Buscar libros por idioma
    document.getElementById('fetch-libros-idioma').addEventListener('click', () => {
        const idioma = idiomaSelect.value;
        if (!idioma) {
            alert('Por favor, selecciona un idioma.');
            return;
        }
        fetch(`/libros/idioma?idioma=${idioma}`)
            .then(response => response.json())
            .then(data => {
                const tableBody = document.querySelector('#libros-idioma-table tbody');
                tableBody.innerHTML = '';
                data.forEach(libro => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `<td>${libro.titulo}</td><td>${libro.autor.nombre}</td>`;
                });
            })
            .catch(error => console.error('Error al buscar libros por idioma:', error));
    });

});

import React, { useState, useEffect } from 'react';

function LibrosPorIdioma() {
  const [idiomas, setIdiomas] = useState([]);
  const [idiomaSeleccionado, setIdiomaSeleccionado] = useState('');
  const [libros, setLibros] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchIdiomas = async () => {
      try {
        const response = await fetch('http://localhost:8080/libros/idiomas');
        const data = await response.json();
        if (response.ok) {
          setIdiomas(data);
          if (data.length > 0) {
            setIdiomaSeleccionado(data[0]);
          }
        } else {
          setError('Error al obtener los idiomas.');
        }
      } catch (err) {
        setError('Error al conectar con el servidor.');
      }
    };
    fetchIdiomas();
  }, []);

  useEffect(() => {
    if (idiomaSeleccionado) {
      const fetchLibrosPorIdioma = async () => {
        try {
          const response = await fetch(`http://localhost:8080/libros/idioma?idioma=${idiomaSeleccionado}`);
          const data = await response.json();
          if (response.ok) {
            setLibros(data);
          } else {
            setError('Error al obtener los libros por idioma.');
          }
        } catch (err) {
          setError('Error al conectar con el servidor.');
        }
      };
      fetchLibrosPorIdioma();
    }
  }, [idiomaSeleccionado]);

  return (
    <section>
      <h3>Libros por Idioma</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <select
        value={idiomaSeleccionado}
        onChange={(e) => setIdiomaSeleccionado(e.target.value)}
      >
        {idiomas.map((idioma) => (
          <option key={idioma} value={idioma}>
            {idioma}
          </option>
        ))}
      </select>
      <table>
        <thead>
          <tr>
            <th>Título</th>
            <th>Autor</th>
            <th>Idioma</th>
            <th>Descargas</th>
          </tr>
        </thead>
        <tbody>
          {libros.map((libro) => (
            <tr key={libro.id}>
              <td>{libro.titulo}</td>
              <td>{libro.autor.nombre}</td>
              <td>{libro.idioma}</td>
              <td>{libro.numeroDeDescargas}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}

export default LibrosPorIdioma;

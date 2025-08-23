import React, { useState, useEffect } from 'react';

function Libros() {
  const [libros, setLibros] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchLibros = async () => {
      try {
        const response = await fetch('http://localhost:8080/libros');
        const data = await response.json();
        if (response.ok) {
          setLibros(data);
        } else {
          setError('Error al obtener los libros.');
        }
      } catch (err) {
        setError('Error al conectar con el servidor.');
      }
    };
    fetchLibros();
  }, []);

  return (
    <section>
      <h3>Todos los Libros</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
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

export default Libros;

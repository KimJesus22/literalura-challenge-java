import React, { useState, useEffect } from 'react';

function Libros() {
  const [libros, setLibros] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchLibros = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await fetch('/libros');
        const data = await response.json();
        if (response.ok) {
          setLibros(data);
        } else {
          setError('Error al obtener los libros.');
        }
      } catch (err) {
        setError('Error al conectar con el servidor.');
      } finally {
        setLoading(false);
      }
    };
    fetchLibros();
  }, []);

  return (
    <section>
      <h3>Todos los Libros</h3>
      {loading && <p>Cargando...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {!loading && !error && (
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
      )}
    </section>
  );
}

export default Libros;

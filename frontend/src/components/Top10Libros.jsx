import React, { useState, useEffect } from 'react';

function Top10Libros() {
  const [libros, setLibros] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchTop10Libros = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await fetch('/libros/top10');
        const data = await response.json();
        if (response.ok) {
          setLibros(data);
        } else {
          setError('Error al obtener el top 10 de libros.');
        }
      } catch (err) {
        setError('Error al conectar con el servidor.');
      } finally {
        setLoading(false);
      }
    };
    fetchTop10Libros();
  }, []);

  return (
    <section>
      <h3>Top 10 Libros Más Descargados</h3>
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

export default Top10Libros;

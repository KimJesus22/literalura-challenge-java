import React, { useState, useEffect } from 'react';

function Estadisticas() {
  const [estadisticas, setEstadisticas] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchEstadisticas = async () => {
      try {
        const response = await fetch('http://localhost:8080/libros/estadisticas');
        const data = await response.json();
        if (response.ok) {
          setEstadisticas(data);
        } else {
          setError('Error al obtener las estadísticas.');
        }
      } catch (err) {
        setError('Error al conectar con el servidor.');
      }
    };
    fetchEstadisticas();
  }, []);

  return (
    <section>
      <h3>Estadísticas por Idioma</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <table>
        <thead>
          <tr>
            <th>Idioma</th>
            <th>Total de Libros</th>
          </tr>
        </thead>
        <tbody>
          {estadisticas.map((item) => (
            <tr key={item.idioma}>
              <td>{item.idioma}</td>
              <td>{item.total}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
}

export default Estadisticas;

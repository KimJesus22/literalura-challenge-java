import React, { useState, useEffect } from 'react';
import { getLanguageName } from '../utils/languageUtils.js';

function Estadisticas() {
  const [estadisticas, setEstadisticas] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchEstadisticas = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await fetch('/libros/estadisticas');
        const data = await response.json();
        if (response.ok) {
          setEstadisticas(data);
        } else {
          setError('Error al obtener las estadísticas.');
        }
      } catch (err) {
        setError('Error al conectar con el servidor.');
      } finally {
        setLoading(false);
      }
    };
    fetchEstadisticas();
  }, []);

  return (
    <section>
      <h3>Estadísticas por Idioma</h3>
      {loading && <p>Cargando...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {!loading && !error && (
        <div className="stats-container">
          {estadisticas.map((item) => (
            <div key={item.idioma} className="stat-card">
              <span className="stat-value">{item.total}</span>
              <span className="stat-label">Libros en {getLanguageName(item.idioma)}</span>
            </div>
          ))}
        </div>
      )}
    </section>
  );
}

export default Estadisticas;

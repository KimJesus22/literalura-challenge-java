import React, { useState } from 'react';

function AutoresVivos() {
  const [anio, setAnio] = useState('');
  const [autores, setAutores] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setAutores([]); // Limpiar resultados anteriores

    try {
      const response = await fetch(`/autores/vivos?anio=${anio}`);
      const data = await response.json();
      if (response.ok) {
        setAutores(data);
      } else {
        setError('Error al obtener los autores vivos.');
      }
    } catch (err) {
      setError('Error al conectar con el servidor.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <section>
      <h3>Autores Vivos por Año</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          value={anio}
          onChange={(e) => setAnio(e.target.value)}
          placeholder="Año"
          required
        />
        <button type="submit" disabled={loading}>__{loading ? 'Buscando...' : 'Buscar'}__</button>
      </form>
      {loading && <p>Cargando...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {!loading && !error && autores.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Nacimiento</th>
              <th>Fallecimiento</th>
            </tr>
          </thead>
          <tbody>
            {autores.map((autor) => (
              <tr key={autor.id}>
                <td>{autor.nombre}</td>
                <td>{autor.fechaNacimiento}</td>
                <td>{autor.fechaFallecimiento}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </section>
  );
}

export default AutoresVivos;

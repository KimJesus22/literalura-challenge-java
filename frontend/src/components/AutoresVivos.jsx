import React, { useState } from 'react';

function AutoresVivos() {
  const [anio, setAnio] = useState('');
  const [autores, setAutores] = useState([]);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

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
    }
  };

  return (
    <section>
      <h3>Autores Vivos</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          value={anio}
          onChange={(e) => setAnio(e.target.value)}
          placeholder="Año"
          required
        />
        <button type="submit">Buscar</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
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
    </section>
  );
}

export default AutoresVivos;

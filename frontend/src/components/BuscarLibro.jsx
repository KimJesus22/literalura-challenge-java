import React, { useState } from 'react';

function BuscarLibro() {
  const [titulo, setTitulo] = useState('');
  const [mensaje, setMensaje] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMensaje(null);
    setError(null);

    try {
      const response = await fetch('/libros/buscar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ titulo }),
      });
      const data = await response.json();
      if (data.error) {
        setError(data.error);
      } else {
        setMensaje(data.mensaje);
      }
    } catch (err) {
      setError('Error al conectar con el servidor.');
    } finally {
      setLoading(false);
      setTitulo('');
    }
  };

  return (
    <section>
      <h3>Buscar y Guardar Libro</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={titulo}
          onChange={(e) => setTitulo(e.target.value)}
          placeholder="Título del libro"
          required
          disabled={loading}
        />
        <button type="submit" disabled={loading}>
          {loading ? 'Buscando...' : 'Buscar y Guardar'}
        </button>
      </form>
      {mensaje && <p style={{ color: 'green' }}>{mensaje}</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </section>
  );
}

export default BuscarLibro;

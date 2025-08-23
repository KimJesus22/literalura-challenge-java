import React, { useState } from 'react';
import BuscarLibro from './components/BuscarLibro';
import Libros from './components/Libros';
import Top10Libros from './components/Top10Libros';
import LibrosPorIdioma from './components/LibrosPorIdioma';
import AutoresVivos from './components/AutoresVivos';
import Estadisticas from './components/Estadisticas';

function App() {
  const [vista, setVista] = useState('todos');

  const renderVista = () => {
    switch (vista) {
      case 'todos':
        return <Libros />;
      case 'top10':
        return <Top10Libros />;
      case 'idioma':
        return <LibrosPorIdioma />;
      case 'autores':
        return <AutoresVivos />;
      case 'estadisticas':
        return <Estadisticas />;
      default:
        return <Libros />;
    }
  };

  return (
    <div>
      <header>
        <h1>Literalura</h1>
        <nav>
          <button onClick={() => setVista('todos')}>Todos</button>
          <button onClick={() => setVista('top10')}>Top 10</button>
          <button onClick={() => setVista('idioma')}>Por Idioma</button>
          <button onClick={() => setVista('autores')}>Autores Vivos</button>
          <button onClick={() => setVista('estadisticas')}>Estadísticas</button>
        </nav>
      </header>
      <main>
        <BuscarLibro />
        {renderVista()}
      </main>
    </div>
  );
}

export default App;

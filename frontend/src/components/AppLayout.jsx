import React, { useState, useEffect } from 'react';
import { Outlet, Link } from 'react-router-dom';

function AppLayout() {
  const [theme, setTheme] = useState(() => {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) return savedTheme;
    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
  });

  useEffect(() => {
    document.body.className = theme + '-theme';
    localStorage.setItem('theme', theme);
  }, [theme]);

  const toggleTheme = () => {
    setTheme(prevTheme => (prevTheme === 'light' ? 'dark' : 'light'));
  };

  return (
    <div>
      <header>
        <h1>Literalura</h1>
        <div className="header-buttons">
          <button onClick={toggleTheme}>
            Cambiar a Tema {theme === 'light' ? 'Oscuro' : 'Claro'}
          </button>
          <a href="/swagger-ui.html" target="_blank" rel="noopener noreferrer">
            <button>API Docs</button>
          </a>
        </div>
        <nav>
          <Link to="/app/buscar"><button>Buscar y Guardar</button></Link>
          <Link to="/app/todos"><button>Todos</button></Link>
          <Link to="/app/top10"><button>Top 10</button></Link>
          <Link to="/app/idioma"><button>Por Idioma</button></Link>
          <Link to="/app/autores"><button>Autores Vivos</button></Link>
          <Link to="/app/estadisticas"><button>Estadísticas</button></Link>
        </nav>
      </header>
      <main>
        <Outlet /> {/* Aquí se renderizarán las páginas anidadas */}
      </main>
    </div>
  );
}

export default AppLayout;

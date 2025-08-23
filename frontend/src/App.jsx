import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Landing from './components/Landing';
import AppLayout from './components/AppLayout';
import BuscarLibro from './components/BuscarLibro';
import Libros from './components/Libros';
import Top10Libros from './components/Top10Libros';
import LibrosPorIdioma from './components/LibrosPorIdioma';
import AutoresVivos from './components/AutoresVivos';
import Estadisticas from './components/Estadisticas';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Landing />} />
      <Route path="/app" element={<AppLayout />}>
        {/* Redirige /app a /app/todos por defecto */}
        <Route index element={<Navigate to="todos" replace />} /> 
        <Route path="buscar" element={<BuscarLibro />} />
        <Route path="todos" element={<Libros />} />
        <Route path="top10" element={<Top10Libros />} />
        <Route path="idioma" element={<LibrosPorIdioma />} />
        <Route path="autores" element={<AutoresVivos />} />
        <Route path="estadisticas" element={<Estadisticas />} />
      </Route>
    </Routes>
  );
}

export default App;

import React from 'react';
import { Link } from 'react-router-dom';

function Landing() {
  return (
    <div className="landing">
      <h2>Bienvenido a Literalura</h2>
      <p>Tu biblioteca de libros del Proyecto Gutenberg.</p>
      <p>Explora y gestiona tu colección de libros fácilmente.</p>
      <Link to="/app/todos">
        <button style={{ marginTop: '2rem', fontSize: '1.2em' }}>
          Entrar a la Aplicación
        </button>
      </Link>
    </div>
  );
}

export default Landing;

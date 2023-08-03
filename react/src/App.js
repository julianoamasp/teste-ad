import React from 'react';
import { Outlet, Link } from 'react-router-dom';
import './style.css';
import Container from 'react-bootstrap/Container';

export default function App() {
  return (
    <div>

      <Container>
        <nav>
          <ul style={{ display: "flex", gap: "20px", padding: "20px" }}>
            <li style={{ listStyle: "none" }}>
              <Link to={``}>Produtos</Link>
            </li>
            <li style={{ listStyle: "none" }}>
              <Link to={`/categorias`}>Categorias</Link>
            </li>
          </ul>
        </nav>
        <div>
          <Outlet />
        </div>
      </Container>
    </div>
  );
}

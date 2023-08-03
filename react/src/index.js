import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './style.css';

import App from './App';

import Categorias from './Components/Categorias/Categorias';
import Produtos from './Components/Produtos/Produtos';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '',
        element: <Produtos />,
      },
      {
        path: '/categorias',
        element: <Categorias />,
      }
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  
    <RouterProvider router={router} />
  
);

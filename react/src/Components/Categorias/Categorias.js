import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import Table from 'react-bootstrap/Table';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';

import categoriasService from '../../Services/CategoriaService'
export default function Categorias() {

  //modal
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = (categoria = {
    id: null,
    nome: '',
    situacao: 'Ativado',
  }) => {
    setCategoria(categoria);
    setShow(true);
  };
  //modal

  useEffect(() => {
    atualizarTabelaCategoria()
  }, []);

  const [filtro, setFiltro] = useState({
    nome: '',
    situacao: 'Ativado'
  })
  function limparBusca() {
    setFiltro((prevFiltro) => ({ ...prevFiltro, nome: '' }));
    atualizarTabelaCategoria()
  }

  const [categoria, setCategoria] = useState({
    id: null,
    nome: '',
    situacao: '',
  });

  const [categorias, setCategorias] = React.useState([]);

  function atualizarTabelaCategoria() {
    categoriasService.todasCategorias().then((res) => {
      setCategorias(res);
    })
  }

  function filtrarCategorias() {
    categoriasService.filtrarCategorias(filtro).then((res) => {
      setCategorias(res);
    })
  }

  function salvarCategoria() {
    if (categoria.nome == "") {
      alert("Preencha o nome da categoria!")
    } else {
      categoriasService.createCategoria(categoria).then((res) => {
        setShow(false);
        atualizarTabelaCategoria();
        alert("Categoria Salva!");
      })
    }
  }
  function atualizarCategoria() {
    if (categoria.nome == "") {
      alert("Preencha o nome da categoria!")
    } else {
      categoriasService.updateCategoria(categoria).then((res) => {
        setShow(false);
        atualizarTabelaCategoria();
        alert("Categoria Atualizada!");
      })
    }
  }

  function apagarCategoria(categoria) {
    if (confirm('Deseja remover a categoria "' + categoria.nome + '"?')) {
      categoriasService.deleteCategoria(categoria.id).then((res) => {
        atualizarTabelaCategoria();
        alert("Categoria Removida!");
      })
    }

  }

  return (
    <Container>
      <Row>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>
              {categoria.id == undefined ? 'Adicionar' : 'Atualizar'} Categoria
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>

            <Form>
              <Row>
                <Form.Group className="mb-3">
                  <Form.Label>Nome</Form.Label>
                  <Form.Control type="text" placeholder="nome" defaultValue={categoria.nome} onChange={(event) => { setCategoria((prevFiltro) => ({ ...prevFiltro, nome: event.target.value })); }} />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label>Situação</Form.Label>
                  <Form.Select defaultValue={categoria.situacao} onChange={(event) => { setCategoria((prevFiltro) => ({ ...prevFiltro, situacao: event.target.value })); }}>
                    <option value="Ativado">Ativado</option>
                    <option value="Desativado">Desativado</option>
                  </Form.Select>
                </Form.Group>
              </Row>
            </Form>

          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            {categoria.id == undefined ?
              <Button variant="primary" onClick={salvarCategoria}>Adicionar</Button>
              :
              <Button variant="primary" onClick={atualizarCategoria}>Atualizar</Button>
            }

          </Modal.Footer>
        </Modal>

        <div>

          <div>
            <h2>
              Categorias{' '}
              <Button
                variant="primary"
                onClick={() => {
                  handleShow();
                }}
              >
                +
              </Button>
            </h2>
            <div className='row' style={{ display: "grid", gridTemplateColumns: "40% 40% 20%" }}>

              <Form.Group className="mb-3 " >
                <Form.Label>Nome</Form.Label>
                <Form.Control type="text" value={filtro.nome} defaultValue={filtro.nome} onChange={(event) => { setFiltro((prevFiltro) => ({ ...prevFiltro, nome: event.target.value })); }} />
              </Form.Group>

              <Form.Group className="mb-3" >
                <Form.Label>Situação</Form.Label>
                <Form.Select value={filtro.situacao} defaultValue={filtro.situacao} onChange={(event) => { setFiltro((prevFiltro) => ({ ...prevFiltro, situacao: event.target.value })); }}>
                  <option value="Ativado">Ativado</option>
                  <option value="Desativado">Desativado</option>
                </Form.Select>
              </Form.Group>
              <Form.Group className="mb-3" >
                <Button
                  style={{ marginTop: "30px", width: "50%" }}
                  variant="secondary"
                  onClick={() => {
                    limparBusca();
                  }}
                >
                  Limpar
                </Button>
                <Button
                  style={{ marginTop: "30px", width: "50%" }}
                  variant="primary"
                  onClick={() => {
                    filtrarCategorias();
                  }}
                >
                  Buscar
                </Button>
              </Form.Group>

            </div>
            <div>
              <Table striped bordered hover>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Situação</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {categorias.map((e, key) => {
                    return (
                      <tr key={key}>
                        <td>{e.id}</td>
                        <td>{e.nome}</td>
                        <td>{e.situacao}</td>
                        <td>
                          <Button
                            variant="primary"
                            onClick={() => {
                              handleShow(e);
                            }}
                          >
                            Editar
                          </Button>
                          <Button
                            variant="danger"
                            onClick={() => {
                              apagarCategoria(e);
                            }}
                          >
                            Delete
                          </Button>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </Table>
            </div>
          </div>
        </div>
      </Row>
    </Container>
  );
}

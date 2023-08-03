import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import Table from 'react-bootstrap/Table';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';

import produtosService from "../../Services/ProdutoService"
import categoriasService from "../../Services/CategoriaService"

export default function Produtos() {

  useEffect(() => {
    atualizarProdutos();
    categoriasService.todasCategorias().then((res) => {
      setCategorias(res);
    })
  }, []);

  //modal
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = (produto = {
    id: null,
    categoria_id: categorias[0].id,
    nome: '',
    descricao: '',
    preco: 0.0,
    situacao: 'Ativo',
  }) => {
    setProduto(produto);
    setShow(true);
  };
  //modal

  const [filtro, setFiltro] = useState({
    nome: '',
    situacao: 'Ativado',
    categoria: 1
  })
  const [produto, setProduto] = useState({
    id: null,
    categoria: 10,
    nome: '',
    descricao: '',
    preco: 0.0,
    situacao: 'Ativo',
  });
  const [produtos, setProdutos] = React.useState([]);
  const [categorias, setCategorias] = React.useState([]);

  function atualizarProdutos() {
    produtosService.todosProdutos().then((produtos) => { setProdutos(produtos); });
  }

  function filtrarCategoriaNome(e) {
    if (categorias.length > 0) {
      let categoria = categorias.filter((cat) => {
        if (cat.id == e.categoria_id) {
          return cat;
        }
      })
      return categoria[0].nome;
    } else {
      return "";
    }
  }

  function limparBusca() { setFiltro({ nome: '', situacao: 'Ativado', categoria: 1 }); atualizarProdutos(); }

  function FiltrarProdutos() {
    produtosService.filtrarProdutos(filtro).then((res) => { setProdutos(res) })
  }

  function salvarProduto() {
    if (produto.nome == "") {
      alert("Preencha o nome!");
      return;
    } else if (produto.descricao == "") {
      alert("Preencha a descrição!");
      return;
    }
    else if (produto.preco <= 0) {
      alert("Preço do produto tem que ser maior que zero!");
      return;
    } else {
      produtosService.createProduto(produto).then((res) => {
        setShow(false);
        atualizarProdutos();
        alert("Produto Salvo!");
      })
    }
  }
  function atualizarProduto() {
    if (produto.nome == "") {
      alert("Preencha o nome!");
      return;
    } else if (produto.descricao == "") {
      alert("Preencha a descrição!");
      return;
    }
    else if (produto.preco <= 0) {
      alert("Preço do produto tem que ser maior que zero!");
      return;
    } else {
      produtosService.updateProduto(produto).then((res) => {
        setShow(false);
        atualizarProdutos();
        alert("Produto Salvo!");
      })
    }
  }

  function apagarProduto(e) {
    if (confirm('Deseja remover o produto "' + e.nome + '"?')) {
      produtosService.deleteProduto(e.id).then((res) => {
        atualizarProdutos();
        alert("Produto Removido!");
      });
    }
  }

  return (
    <Container>
      <Row>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>
              {produto.id == undefined ? 'Adicionar' : 'Atualizar'} Produto
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>

            <Form>
              <Row>
                <Form.Group className="mb-3" >
                  <Form.Label>Categorias</Form.Label>
                  <Form.Select defaultValue={produto.categoria} onChange={(event) => { setProduto((prevFiltro) => ({ ...prevFiltro, categoria_id: event.target.value })); }}>
                    {
                      categorias.map((e, key) => {
                        return (<option key={key} value={e.id}>{e.nome}</option>)
                      })
                    }
                  </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3" >
                  <Form.Label>Nome </Form.Label>
                  <Form.Control type="text" placeholder="Nome" defaultValue={produto.nome} onChange={(event) => { setProduto((prevFiltro) => ({ ...prevFiltro, nome: event.target.value })); }} />
                </Form.Group>

                <Form.Group className="mb-3" >
                  <Form.Label>Descrição </Form.Label>
                  <Form.Control type="text" as="textarea" placeholder="Descrição" defaultValue={produto.descricao} onChange={(event) => { setProduto((prevFiltro) => ({ ...prevFiltro, descricao: event.target.value })); }} />
                </Form.Group>

                <Form.Group className="mb-3" >
                  <Form.Label>Preço </Form.Label>
                  <Form.Control type="number" placeholder="nome" defaultValue={produto.preco} onChange={(event) => { setProduto((prevFiltro) => ({ ...prevFiltro, preco: event.target.value })); }} />
                </Form.Group>

                <Form.Group className="mb-3" >
                  <Form.Label>Situação</Form.Label>
                  <Form.Select defaultValue={produto.situacao} onChange={(event) => { setProduto((prevFiltro) => ({ ...prevFiltro, situacao: event.target.value })); }}>
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
            {produto.id == undefined ?
              <Button variant="primary" onClick={salvarProduto}>Adicionar</Button>
              :
              <Button variant="primary" onClick={atualizarProduto}>Atualizar</Button>
            }

          </Modal.Footer>
        </Modal>

        <div>

          <div>
            <h2>
              Produtos{' '}
              <Button
                variant="primary"
                onClick={() => {
                  handleShow();
                }}
              >
                +
              </Button>
            </h2>
            <div className='row' style={{ display: "grid", gridTemplateColumns: "40% 20% 20% 20%" }}>

              <Form.Group className="mb-3 ">
                <Form.Label>Nome </Form.Label>
                <Form.Control type="text" placeholder="nome" defaultValue={filtro.nome} value={filtro.nome} onChange={(event) => { setFiltro((prevFiltro) => ({ ...prevFiltro, nome: event.target.value })); }} />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Situação </Form.Label>
                <Form.Select defaultValue={"Ativado"} value={"Ativado"} onChange={(event) => { setFiltro((prevFiltro) => ({ ...prevFiltro, situacao: event.target.value })); }}>
                  <option value="Ativado">Ativado</option>
                  <option value="Desativado">Desativado</option>
                </Form.Select>
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Categoria</Form.Label>
                <Form.Select defaultValue={filtro.categoria} value={filtro.categoria} onChange={(event) => { setFiltro((prevFiltro) => ({ ...prevFiltro, categoria: event.target.value })); }}>
                  {
                    categorias.map((e, key) => {
                      return (<option key={key} value={e.id}>{e.nome}</option>)
                    })
                  }
                </Form.Select>
              </Form.Group>

              <Form.Group className="mb-3">
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
                    FiltrarProdutos();
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
                    <th>Categoria</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Situação</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {produtos.map((e, key) => {
                    return (
                      <tr key={key}>
                        <td>{e.id}</td>
                        <td>{filtrarCategoriaNome(e)}</td>
                        <td>{e.nome}</td>
                        <td>{e.preco}</td>
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
                              apagarProduto(e);
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

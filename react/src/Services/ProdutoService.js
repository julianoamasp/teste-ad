import Axios from "axios";

const todosProdutos = async () => {
    const response = await Axios.get("http://localhost:8080/produto");
    return response.data;
}
const filtrarProdutos = async (filtros) => {
    let queryString = [];
    for (let key in filtros) {
        queryString.push(`${key}=${filtros[key]}`);
    }
    
    const response = await Axios({
        method: 'get',
        url: 'http://localhost:8080/produto/filtro?'+queryString.join('&'),
        data: filtros
    });
    return response.data;
}
const createProduto = async (produto) => {
    const response = await Axios({
        method: 'post',
        url: 'http://localhost:8080/produto',
        data: produto
    });

    return response.data;
}
const updateProduto = async (produto) => {
    const response = await Axios({
        method: 'put',
        url: 'http://localhost:8080/produto/' + produto.id,
        data: produto
    });
    return response.data;
}
const deleteProduto = async (id) => {
    const response = await Axios({
        method: 'delete',
        url: 'http://localhost:8080/produto/' + id
    });
    return response.data;
}
export default {
    todosProdutos,
    filtrarProdutos,
    createProduto,
    updateProduto,
    deleteProduto
}
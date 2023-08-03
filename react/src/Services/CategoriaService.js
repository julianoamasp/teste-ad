import Axios from "axios";

const todasCategorias = async () => {
    const response = await Axios.get("http://localhost:8080/categoria");
    return response.data;
}
const filtrarCategorias = async (filtros) => {
    let queryString = [];
    for (let key in filtros) {
        queryString.push(`${key}=${filtros[key]}`);
    }
    
    const response = await Axios({
        method: 'get',
        url: 'http://localhost:8080/categoria/filtro?'+queryString.join('&'),
        data: filtros
    });
    return response.data;
}
const createCategoria = async (categoria) => {
    const response = await Axios({
        method: 'post',
        url: 'http://localhost:8080/categoria',
        data: categoria
    });

    return response.data;
}
const updateCategoria = async (categoria) => {
    const response = await Axios({
        method: 'put',
        url: 'http://localhost:8080/categoria',
        data: categoria
    });
    return response.data;
}
const deleteCategoria = async (id) => {
    const response = await Axios({
        method: 'delete',
        url: 'http://localhost:8080/categoria/' + id
    });
    return response.data;
}
export default {
    todasCategorias,
    filtrarCategorias,
    createCategoria,
    updateCategoria,
    deleteCategoria
}
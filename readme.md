<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <style>
    img {
      max-width: 700px;
      margin-bottom: 20px;
    }
  </style>
  <title>Documentação</title>
</head>

<body>
  <div class="container">
    <img src="midia/img16.png">
    <h4>Título do projeto</h4>
    <p>Simulador de Loja</p>

    <h4>Descrição do projeto</h4>
    <p>O sistema simula uma parte de uma loja online, que terá somente categorias e produtos.</p>

    <h4>Funcionalidades do projeto</h4>
    <ul>
      <li><b>Categoria</b>
        <ul>
          <li>Buscar todas as categorias.</li>
          <li>Filtrar a busca de categorias por nome e situações.</li>
          <li>Criar categoria.</li>
          <li>Atualizar categoria.</li>
          <li>Remover categoria.</li>
        </ul>
      </li>
      <li><b>Produto</b>
        <ul>
          <li>Buscar todos os produtos.</li>
          <li>Filtrar a busca de produtos por nome, categorias e situações.</li>
          <li>Criar produto.</li>
          <li>Atualizar o produto.</li>
          <li>Remover o produto.</li>
        </ul>
      </li>
    </ul>

    <h4>Tecnologias utilizadas</h4>
    <ul>
      <li><b>JAVA v-17</b>
        <ul>
          <li>MAVEN</li>
          <li>Springboot v-3.1.2</li>
          <ul>
            <li>JPA v- last</li>
            <li>postgreesql v- last</li>
            <li>validation v-last</li>
          </ul>
        </ul>
      </li>
    </ul>
    <ul>
      <li>JAVASCRIPT/HTML5/CSS
        <ul>
          <li><b>React v-18.2.0</b>
            <ul>
              <li>react-router-dom v- 6.14.2 </li>
              <li>axios v- 1.4.0</li>
              <li>bootstrap v- 5.3.0</li>
            </ul>
          </li>
        </ul>
    </ul>
    <ul>
      <li><b>PostgreeSQL v-14</b></li>
    </ul>

    </ul>

    <h4>Estrutura de pastas</h4>
    <ul>
      <li><b>admissao</b>, fontes da aplicação backend com maven e JAVA</li>
      <li><b>react</b>, fontes da aplicação frontend com framework React</li>
      <li><b>midia</b>, imagens da documentação</li>
      <li><b>Admissão.postman_collection.json</b>, coleção exportada do postman em json</li>
    </ul>

    <h4>Colaboradores</h4>
    <p><b>Desenvolvedor Backend/Frontend: Juliano da silva mendes</b></p>

    <h4>Status do projeto</h4>
    <p>Projeto concluído.</p>



    <h4>Inicialização</h4>
    <ul>
      <li><b>Preparar o ambiente de armazenamento com o PostgreeSQL 14</b>
        <ol>
          <li>
            <p>Caso não tenha instalado o PostgreeSQL, acesse o site oficial para o download <a href="https://www.postgresql.org/download/">https://www.postgresql.org/download/</a>.</p>
          </li>
          <li>
            <p>Crie um novo servidor</p>
            <img src="./midia/img11.png">
          </li>
          <li>
            <p>Coloque o nome de PostgreeSQL 14</p>
            <img src="./midia/img12.png">
          </li>
          <li>
            <p>Configure o Host como <code>localhost</code> a porta como <code>5432</code> o administrador como
              <code>postgres</code> e a senha como <code>admin</code>
            </p>
            <img src="./midia/img13.png">
          </li>
          <li>
            <p>Certifique-se de que seu postgreeSql está na versão 14 e verifique se o servidor foi criado</p>
            <img src="./midia/img5.png">
          </li>
          <li>
            <p>Crie a BASE com o nome admissional</p>
            <img src="./midia/img6.png">

          </li>
          <li>
            <p>Certifique-se de que a BASE foi criada</p>
            <img src="./midia/img7.png">
          </li>
          <li>
            <p>Selecione a BASE, em seguida selecione a aba superior `Tool` e após o menu Query Tool</p>
            <img src="./midia/img8.png">
          </li>
          <li>
            <p>Cole o script abaixo na aba que aparecer e execute clicando no botão com o formato de PLAY</p>
            <code>
              CREATE TABLE categoria (<br>
                  id BIGSERIAL PRIMARY KEY,<br>
                  nome VARCHAR(255),<br>
                  situacao VARCHAR(255)<br>
              );<br>
              <br>
              CREATE TABLE produto (<br>
                  id BIGSERIAL PRIMARY KEY,<br>
                  categoria_id BIGINT,<br>
                  descricao VARCHAR(255),<br>
                  nome VARCHAR(255),<br>
                  preco DOUBLE PRECISION,<br>
                  situacao VARCHAR(255)<br>
              );<br>
<br>
              ALTER TABLE produto<br>
              ADD FOREIGN KEY (categoria_id)<br>
              REFERENCES categoria (id);<br><br>
            </code>
            <img src="./midia/img9.png">
          </li>
          <li>
            <p>Antes de irmos para o próximo passo, certifique-se de que as tabelas foram criadas</p>
            <img src="./midia/img10.png">
          </li>
        </ol>
      </li>
      <li><b>Executar o JAR</b>
        <ol>
          <li>Para executar o arquivo .jar será nescessário ter instalado a Máquina virtual java com suporte para a versão, que pode ser obtida no seguinte link <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html</a></li>
          <li>
            <p>Vá para local padrão de construção de porjeto do maven <code>admissao/target</code> e execute o arquivo
              <code>admissao-0.0.1-SNAPSHOT.jar</code>
            </p>
            <img src="./midia/img14.png">
          </li>
          <li>
            <p>Pode ser que apareça uma alerta de violação de segurança, simplesmente autorize.
            </p>
            <img src="./midia/img2.png">
          </li>
          <li>
            <p>Em seguinda acesse o endereço <code>http://localhost:8080/</code>
              
            </p>
            <img src="./midia/img15.png">
          </li>
        </ol>
    </ul>

    <h4>Extras</h4>
    <p>Para acessar a exportação dos testes efetuados com o Postman, faça o download do <a href="./Admissão.postman_collection.json" download="filename" target="_blank">ARQUIVO</a> e importe em seu Postman. Caso não tenha o software, acesse o link para fazer download <a href="https://www.postman.com/">https://www.postman.com/</a> e acesse a documentação para saber como importar o arquivo JSON.</p>
<ol>
  <li>
    <p>Na aplicação clique no botão superior a esquerda chamado import</p>
    <img src="./midia/img17.png">
  </li>
  <li>
    <p>Em seguida clique em selecionar um arquivo</p>
    <img src="./midia/img18.png">
  </li>
  <li>
    <p>Em seguida selecione o arquivo json</p>
    <img src="./midia/img19.png">
  </li>
  <li>
    <p>Após importar, irá carregar o teste feito com alguns endpoints</p>
    <img src="./midia/img3.png">
  </li>
</ol>
  </div>

  
  <!-- Option 1: Bootstrap Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>

  <!-- Option 2: Separate Popper and Bootstrap JS -->
  <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
</body>

</html>
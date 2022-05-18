<%-- 
    Document   : cabecalho
    Created on : 25 de abr de 2022, 13:50:47
    Author     : KGe
--%>

<!-- Inicio cabecalho-->
<div class="jumbotron"><h1>BookStoreWeb</h1></div>



<p>
<div class="menu">
    <a href="<%=request.getContextPath()%>/bstore/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>Adicionar novo Livro</a>

    <a href="<%=request.getContextPath()%>/bstore/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Listar Todos os Livros</a>
        
    <a href="<%=request.getContextPath()%>/bsuser/new" class="btn btn-default">
        <span class="glyphicon glyphicon-log-out"></span>
        Criar Conta</a>  
        
    <a href="<%=request.getContextPath()%>/bsuser/list" class="btn btn-default">
        <span class="glyphicon glyphicon-log-out"></span>
        Usuários Cadastrados</a>    

    <a href="<%=request.getContextPath()%>/logout" class="btn btn-default">
        <span class="glyphicon glyphicon-log-out"></span>
        Sair</a>
</div>
</p>

<!-- Fim cabecalho-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">
    <head>
        <jsp:include page="./contents/headerTags.jsp"/>
        <title>Aplicação BookStoreWeb</title>
        <jsp:include page="Styles.jsp"/>
    </head>
    <body>
        <div class ="container" align="center">
            <div>
            <p> Usuário logado: <c:out value=" ${user.fullname}" /> <p>
        </div>
            <jsp:include page="./contents/cabecalho.jsp"/>
            <h1>Aplicação BookStoreWeb</h1>
            <div class="table-responsive">
                <table class="table table-hover" border="5" cellpadding="5" align="center">
                    <caption><h2>Lista de livros</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Titulo</th>
                        <th>Autor</th>
                        <th>Preco</th>
                        <th>Ações</th>
                    </tr>

                    <c:forEach var="book" items="${listaBook}">
                        <tr>
                            <td><c:out value="${book.id}" /></td>
                            <td><c:out value="${book.titulo}" /></td>
                            <td><c:out value="${book.autor}" /></td>
                            <td><c:out value="${book.preco}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/bstore/edit?id=<c:out value='${book.id}' />">
                                    <span class="glyphicon glyphicon-pencil"/> </a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="<%=request.getContextPath()%>/bstore/delete?id=<c:out value='${book.id}' />">
                                    <span class="glyphicon glyphicon-trash"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="./contents/rodape.jsp"/>
        </div>
    </body>
</html>

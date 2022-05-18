<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Aplicação Books Store</title>
        <jsp:include page="/contents/headerTags.jsp"/>
        <jsp:include page="Styles.jsp"/>        
        <jsp:include page="Scripts.jsp"/>
    </head>
    <body>
        <div class="container" align="center">
            <div>
            <p> Usuário logado: <c:out value=" ${user.fullname}" /> <p>
        </div>
            <jsp:include page="./contents/cabecalho.jsp"/>
            <h1>Aplicação BookStoreWeb</h1>
            <div class="formulario">
                <c:if test="${userForm != null}">
                    <form action="update" method="post" onsubmit="confirmacaoEnvio()">
                    </c:if>
                    <c:if test="${userForm == null}">
                        <form action="insert" method="post">
                        </c:if>
                        <table border="1" cellpadding="5">
                            <caption>
                                <h2>
                                    <c:if test="${userForm != null}">
                                        Criar Usuário
                                    </c:if>
                                    <c:if test="${userForm == null}">
                                        <h3>Adicionar novo Usuário</h3>
                                    </c:if>
                                </h2>
                            </caption>
                            <c:if test="${userForm != null}">
                                <input type="hidden" name="formId" value="<c:out
                                           value='${userForm.id}' />" />
                            </c:if>
                            <tr>
                                <th>E-mail: </th>
                                <td>
                                    <input type="text" name="formEmail" size="45"
                                           value="<c:out value='${userForm.email}' />"
                                           />
                                </td>
                            </tr>
                            <tr>
                                <th>Senha: </th>
                                <td>
                                    <input type="text" name="formPassword" size="45"
                                           value="<c:out value='${userForm.password}' />"
                                           />
                                </td>
                            </tr>
                            <tr>
                                <th>Nome: </th>
                                <td>
                                    <input type="text" name="formFullname" size="5"
                                           value="<c:out value='${userForm.fullname}' />"
                                           />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="Enviar" />
                                </td>
                            </tr>
                        </table>
                        <jsp:include page="./contents/rodape.jsp"/>
                    </form>
            </div>
        </div>
    </body>
</html>

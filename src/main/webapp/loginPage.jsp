<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <jsp:include page="./contents/headerTags.jsp"/>
        <title>Bookstore Website</title>
        <jsp:include page="Styles.jsp"/>
    </head>
    <body>
        <div class="container" align="center">
        <div style="text-align: center">
            <h1>Admin Login</h1> 
            <form action="login" method="post">
                <label for="email">Email:</label>
                <input name="email" size="30" />
                <br><br>
                <label for="password">Password:</label>
                <input type="password" name="password" size="30" />
                <!--
                Esse atributo MESSAGE será utilizado como retorno de
               mensagem ao usuário caso
                login inválido.
                -->
                <br>
                <h1>${message}</h1>
                <br><br>
                <button type="submit">Login</button>
            </form>
            <br>
        </div>
        <jsp:include page="./contents/rodape.jsp"/>
        </div>
    </body>
</html>
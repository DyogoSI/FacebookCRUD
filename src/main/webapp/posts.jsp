<%@ page import="java.util.List" %>
<%@ page import="model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Lista de Posts</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
        <h1 class="my-4">Lista de Posts</h1>

        <!-- Botão para adicionar um novo post -->
        <a href="posts?acao=novo" class="btn btn-success mb-4">Adicionar Novo Post</a>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Conteúdo</th>
                    <th>Data</th>
                    <th>Usuário ID</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recuperando a lista de posts da request
                    List<Post> posts = (List<Post>) request.getAttribute("posts");

                    // Verificando se a lista de posts não está vazia
                    if (posts != null && !posts.isEmpty()) {
                        for (Post post : posts) {
                %>
                <tr>
                    <!-- Exibindo os dados dos posts -->
                    <td><%= post.getId() %></td>
                    <td><%= post.getTitulo() %></td>
                    <td><%= post.getConteudo() %></td>
                    <td><%= post.getPostDate() %></td>
                    <td><%= post.getUserId() %></td>
                    <td>
                        <a href="posts?acao=editar&id=<%= post.getId() %>" class="btn btn-warning btn-sm">Editar</a> |
                        <a href="posts?acao=excluir&id=<%= post.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">Nenhum post encontrado</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>

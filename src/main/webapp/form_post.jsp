<%@ page import="model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><%= (request.getAttribute("post") == null) ? "Novo Post" : "Editar Post" %></title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
        <h1><%= (request.getAttribute("post") == null) ? "Novo Post" : "Editar Post" %></h1>
        
        <!-- Formulário para adicionar/editar post -->
        <form action="posts" method="post">
            <!-- Campo oculto para id (só será preenchido ao editar) -->
            <input type="hidden" name="id" value="<%= (request.getAttribute("post") != null) ? ((Post)request.getAttribute("post")).getId() : "" %>" />

            <div class="mb-3">
                <label for="titulo" class="form-label">Título</label>
                <input type="text" name="titulo" id="titulo" class="form-control" value="<%= (request.getAttribute("post") != null) ? ((Post)request.getAttribute("post")).getTitulo() : "" %>" required />
            </div>

            <div class="mb-3">
                <label for="conteudo" class="form-label">Conteúdo</label>
                <textarea name="conteudo" id="conteudo" class="form-control" rows="4" required><%= (request.getAttribute("post") != null) ? ((Post)request.getAttribute("post")).getConteudo() : "" %></textarea>
            </div>

            <div class="mb-3">
                <button type="submit" class="btn btn-primary">
                    <%= (request.getAttribute("post") == null) ? "Adicionar Post" : "Salvar Alterações" %>
                </button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>

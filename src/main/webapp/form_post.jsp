<%@ page import="model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<<<<<<< HEAD
    <title>${post == null ? 'Novo Post' : 'Editar Post'}</title>
=======
    <title><%= (request.getAttribute("post") == null) ? "Novo Post" : "Editar Post" %></title>
>>>>>>> f78b0a868070a6d74d84cde5f54224e9bf2882d0
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
<<<<<<< HEAD
        <h1>${post == null ? 'Novo Post' : 'Editar Post'}</h1>
        
        
        <form action="${pageContext.request.contextPath}/posts" method="post">
           
            <input type="hidden" name="id" value="${post != null ? post.id : ''}" />

            <div class="mb-3">
                <label for="titulo" class="form-label">Título</label>
                <input type="text" name="titulo" id="titulo" class="form-control" value="${post != null ? post.titulo : ''}" required />
            </div>

            <div class="mb-3">
                <label for="content" class="form-label">Conteúdo</label>
                <textarea name="content" id="content" class="form-control" rows="4" required>${post != null ? post.conteudo : ''}</textarea>
=======
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
>>>>>>> f78b0a868070a6d74d84cde5f54224e9bf2882d0
            </div>

            <div class="mb-3">
                <button type="submit" class="btn btn-primary">
<<<<<<< HEAD
                    ${post == null ? 'Adicionar Post' : 'Salvar Alterações'}
=======
                    <%= (request.getAttribute("post") == null) ? "Adicionar Post" : "Salvar Alterações" %>
>>>>>>> f78b0a868070a6d74d84cde5f54224e9bf2882d0
                </button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>

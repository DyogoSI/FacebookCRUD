<%@ page import="model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${post == null ? 'Novo Post' : 'Editar Post'}</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
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
            </div>

            <div class="mb-3">
                <button type="submit" class="btn btn-primary">
                    ${post == null ? 'Adicionar Post' : 'Salvar Alterações'}
                </button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>

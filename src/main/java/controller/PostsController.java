package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Post;
import model.User;
import model.dao.PostDAO;
import model.dao.MySQLPostDAO;
import model.ModelException;

@WebServlet("/posts")
public class PostsController extends HttpServlet {
    private PostDAO postDAO;

    @Override
    public void init() {
        postDAO = new MySQLPostDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";

        try {
            switch (acao) {
                case "novo":
                    req.getRequestDispatcher("form_post.jsp").forward(req, resp);
                    break;

                case "editar":
                    int idEditar = Integer.parseInt(req.getParameter("id"));
                    Post postEditar = postDAO.readById(idEditar);
                    req.setAttribute("post", postEditar);
                    req.getRequestDispatcher("form_post.jsp").forward(req, resp);
                    break;

                case "excluir":
                    int idExcluir = Integer.parseInt(req.getParameter("id"));
                    postDAO.delete(idExcluir);
                    resp.sendRedirect("posts");
                    break;

                default:
                    List<Post> lista = postDAO.read();
                    req.setAttribute("posts", lista);
                    req.getRequestDispatcher("posts.jsp").forward(req, resp);
                    break;
            }
        } catch (ModelException e) {
            throw new ServletException("Erro ao acessar dados de posts", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String titulo = req.getParameter("titulo");
<<<<<<< HEAD
        String conteudo = req.getParameter("content");  // Corrigido aqui!

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usuario_logado");

        if (user == null) {
            resp.sendRedirect("login.jsp");
=======
        String conteudo = req.getParameter("conteudo");

        // Obtendo o usuário logado da sessão
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usuario_logado");

        // Verifica se o usuário está logado
        if (user == null) {
            resp.sendRedirect("login.jsp"); // Redireciona para o login se não estiver logado
>>>>>>> f78b0a868070a6d74d84cde5f54224e9bf2882d0
            return;
        }

        Post post = new Post();
        post.setTitulo(titulo);
        post.setConteudo(conteudo);
<<<<<<< HEAD
        post.setUserId(user.getId());  // Atribuindo o user_id corretamente!

        if (idStr != null && !idStr.isEmpty()) {
            post.setId(Integer.parseInt(idStr));
            try {
                Post existingPost = postDAO.readById(post.getId());
                if (existingPost != null) {
                    post.setPostDate(existingPost.getPostDate());
                }
            } catch (ModelException e) {
                e.printStackTrace();
                return;
            }
        } else {
            String currentDate = java.time.LocalDateTime.now().toString();
            post.setPostDate(currentDate);  // A data está sendo preenchida corretamente!
=======
        post.setUserId(user.getId()); // O campo 'id' no banco de dados refere-se ao usuário logado

        // Se o id não estiver vazio, é uma atualização. A data deve ser preenchida para evitar problemas de nulo
        if (idStr != null && !idStr.isEmpty()) {
            post.setId(Integer.parseInt(idStr)); // Para editar, atribui o ID do post
            try {
                Post existingPost = postDAO.readById(post.getId());  // Buscando o post existente
                if (existingPost != null) {
                    post.setPostDate(existingPost.getPostDate());  // Mantém a data do post original
                }
            } catch (ModelException e) {
                // Tratar a exceção de leitura do post
                req.setAttribute("erro", "Erro ao recuperar os dados do post: " + e.getMessage());
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
                return; // Não prossegue no código caso haja erro
            }
        } else {
            String currentDate = java.time.LocalDateTime.now().toString(); // Obtendo a data atual
            post.setPostDate(currentDate);  // Atribuindo a data de criação ao post
>>>>>>> f78b0a868070a6d74d84cde5f54224e9bf2882d0
        }

        try {
            if (idStr == null || idStr.isEmpty()) {
<<<<<<< HEAD
                postDAO.create(post);  // Criação do post
            } else {
                postDAO.update(post);  // Atualização do post
            }
            resp.sendRedirect("posts");  // Redireciona para a lista de posts
        } catch (ModelException e) {
            e.printStackTrace();
=======
                // Criar novo post
                postDAO.create(post);
            } else {
                // Editar post existente
                postDAO.update(post);
            }
            resp.sendRedirect("posts"); // Redireciona para a lista de posts
        } catch (ModelException e) {
            // Tratar a exceção aqui e exibir um erro amigável ao usuário
            req.setAttribute("erro", "Erro ao salvar o post: " + e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, resp);
>>>>>>> f78b0a868070a6d74d84cde5f54224e9bf2882d0
        }
    }
}

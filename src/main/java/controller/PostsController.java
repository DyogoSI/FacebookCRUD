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
        String conteudo = req.getParameter("content");  // Corrigido aqui!

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usuario_logado");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Post post = new Post();
        post.setTitulo(titulo);
        post.setConteudo(conteudo);
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
        }

        try {
            if (idStr == null || idStr.isEmpty()) {
                postDAO.create(post);  // Criação do post
            } else {
                postDAO.update(post);  // Atualização do post
            }
            resp.sendRedirect("posts");  // Redireciona para a lista de posts
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
}

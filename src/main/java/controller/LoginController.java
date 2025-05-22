package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ModelException;
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.utils.PasswordEncryptor;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userLogin = req.getParameter("user_login"); // E-mail do usuário
        String userPW = req.getParameter("user_pw"); // Senha fornecida

        UserDAO dao = DAOFactory.createDAO(UserDAO.class);
        User user = null;

        try {
            // Buscar usuário pelo e-mail
            user = dao.findByEmail(userLogin);

            // Verificar se o usuário existe e se a senha está correta
            if (user != null && PasswordEncryptor.checkPassword(userPW, user.getPassword())) {
                // Se a senha estiver correta, cria a sessão
                HttpSession session = req.getSession(true);  // Obtém ou cria a sessão
                session.setAttribute("usuario_logado", user);  // Armazena o usuário na sessão
                resp.sendRedirect("/facebook/");  // Redireciona para a página inicial
            } else {
                // Se falhar na comparação de senha
                resp.sendRedirect("/facebook/login.jsp?erro=true");  // Redireciona com erro
            }
        } catch (ModelException e) {
            e.printStackTrace();
            resp.sendRedirect("/facebook/login.jsp?erro=true");  // Redireciona com erro
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Fazer logout, destruindo a sessão
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();  // Invalida a sessão
        }
        resp.sendRedirect("/facebook/login.jsp");  // Redireciona para a página de login
    }
}
package filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        // Verifica se o usuário está logado (se o atributo 'usuario_logado' estiver presente na sessão)
        boolean userLogged = httpReq.getSession().getAttribute("usuario_logado") != null;

        // Obtém a URL da requisição
        String url = httpReq.getRequestURI();

        // Verifica se a URL corresponde a uma página pública (login.jsp ou login)
        boolean isPublicPage = url.endsWith("login.jsp") || url.endsWith("login");

        // Permite o acesso aos recursos públicos como CSS e JS
        boolean isPublicRes = url.contains("/css/") || url.contains("/js/");

        // Se o usuário estiver logado ou acessando uma página pública, ou recursos públicos, continua a requisição
        if (userLogged || isPublicPage || isPublicRes) {
            chain.doFilter(req, res);  // Continua a requisição
        } else {
            // Caso contrário, redireciona para a página de login
            httpRes.sendRedirect("/facebook/login.jsp");  // Redireciona para login
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização do filtro, caso necessário
    }

    @Override
    public void destroy() {
        // Destruição do filtro, caso necessário
    }
}

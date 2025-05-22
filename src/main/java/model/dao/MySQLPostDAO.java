package model.dao;

import model.Post;
import model.ModelException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPostDAO implements PostDAO {

    private Connection connection;

    public MySQLPostDAO() {
        try {
            this.connection = MySQLConnectionFactory.getConnection();
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Post post) throws ModelException {
        String sql = "INSERT INTO posts (titulo, content, post_date, user_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getTitulo());
            stmt.setString(2, post.getConteudo());  // Usando 'content' no banco de dados
            stmt.setString(3, post.getPostDate());  // A data do post
            stmt.setInt(4, post.getUserId());  // Atribuindo o user_id (id do usuário logado)

            stmt.executeUpdate();  // Executa a inserção no banco de dados
        } catch (SQLException e) {
            throw new ModelException("Erro ao salvar post", e);
        }
    }

    @Override
    public List<Post> read() throws ModelException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitulo(rs.getString("titulo"));
                post.setConteudo(rs.getString("content"));  // Usando 'content' no banco de dados
                post.setPostDate(rs.getString("post_date"));
                post.setUserId(rs.getInt("user_id"));
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new ModelException("Erro ao acessar os dados de posts", e);
        }
        return posts;
    }

    @Override
    public Post readById(int id) throws ModelException {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitulo(rs.getString("titulo"));
                post.setConteudo(rs.getString("content"));  // Usando 'content' no banco de dados
                post.setPostDate(rs.getString("post_date"));
                post.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            throw new ModelException("Erro ao buscar post", e);
        }
        return post;
    }

    @Override
    public void update(Post post) throws ModelException {
        String sql = "UPDATE posts SET titulo = ?, content = ?, post_date = ?, user_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, post.getTitulo());
            stmt.setString(2, post.getConteudo());  // Usando 'content' no banco de dados
            stmt.setString(3, post.getPostDate());  // A data do post (garantindo que não seja nula)
            stmt.setInt(4, post.getUserId());  // Atribuindo o user_id
            stmt.setInt(5, post.getId());  // Definindo o ID do post a ser atualizado
            stmt.executeUpdate();  // Executa a atualização no banco de dados
        } catch (SQLException e) {
            throw new ModelException("Erro ao atualizar post", e);
        }
    }

    @Override
    public void delete(int id) throws ModelException {
        String sql = "DELETE FROM posts WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ModelException("Erro ao excluir post", e);
        }
    }
}

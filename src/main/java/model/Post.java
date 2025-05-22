package model;

public class Post {
    private int id;
    private String titulo;
    private String conteudo;
    private String postDate;  // Data do post
    private int userId;  // ID do usuário que criou o post

    // Construtor sem parâmetros
    public Post() {
    }

    // Construtor com parâmetros
    public Post(int id, String titulo, String conteudo, String postDate, int userId) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.postDate = postDate;
        this.userId = userId;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getPostDate() {
        return postDate;  // Método para obter a data do post
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;  // Método para definir a data do post
    }

    public int getUserId() {
        return userId;  // Método para obter o ID do usuário
    }

    public void setUserId(int userId) {
        this.userId = userId;  // Método para definir o ID do usuário
    }

    // Método toString() para facilitar a exibição do objeto
    @Override
    public String toString() {
        return "Post{id=" + id + ", titulo='" + titulo + "', conteudo='" + conteudo + "', postDate='" + postDate + "', userId=" + userId + '}';
    }
}

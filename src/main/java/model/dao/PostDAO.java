package model.dao;


import model.ModelException;
import java.util.List;
import model.Post;

public interface PostDAO {
    void create(Post post) throws ModelException;
    List<Post> read() throws ModelException;
    Post readById(int id) throws ModelException;
    void update(Post post) throws ModelException;
    void delete(int id) throws ModelException;
}

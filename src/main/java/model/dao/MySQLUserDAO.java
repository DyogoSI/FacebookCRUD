package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.User;

public class MySQLUserDAO implements UserDAO {

    @Override
    public boolean save(User user) throws ModelException {
        try {
            DBHandler db = new DBHandler();

            String sqlInsert = "INSERT INTO users (nome, sexo, email, password) VALUES (?, ?, ?, ?);";

            db.prepareStatement(sqlInsert);
            db.setString(1, user.getName());
            db.setString(2, user.getGender());
            db.setString(3, user.getEmail());
            db.setString(4, user.getPassword());

            return db.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ModelException("Erro ao salvar o usuário", e);
        }
    }

    @Override
    public boolean update(User user) throws ModelException {
        try {
            DBHandler db = new DBHandler();

            String sqlUpdate = "UPDATE users SET nome = ?, sexo = ?, email = ?, password = ? WHERE id = ?;";

            db.prepareStatement(sqlUpdate);

            String password = user.getPassword();
            if (password == null || password.isEmpty()) {
                password = this.findById(user.getId()).getPassword();
            }

            db.setString(1, user.getName());
            db.setString(2, user.getGender());
            db.setString(3, user.getEmail());
            db.setString(4, password);
            db.setInt(5, user.getId());

            return db.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ModelException("Erro ao atualizar o usuário", e);
        }
    }

    @Override
    public boolean delete(User user) throws ModelException {
        try {
            DBHandler db = new DBHandler();

            String sqlDelete = "DELETE FROM users WHERE id = ?;";

            db.prepareStatement(sqlDelete);
            db.setInt(1, user.getId());

            return db.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ModelException("Erro ao excluir o usuário", e);
        }
    }

    @Override
    public List<User> listAll() throws ModelException {
        try {
            DBHandler db = new DBHandler();
            List<User> users = new ArrayList<>();

            String sqlQuery = "SELECT * FROM users ORDER BY id";

            db.prepareStatement(sqlQuery);
            db.executeQuery();

            while (db.next()) {
                users.add(createUser(db));
            }

            return users;
        } catch (SQLException e) {
            throw new ModelException("Erro ao listar os usuários", e);
        }
    }

    @Override
    public User findById(int id) throws ModelException {
        try {
            DBHandler db = new DBHandler();

            String sql = "SELECT * FROM users WHERE id = ?";
            db.prepareStatement(sql);
            db.setInt(1, id);
            db.executeQuery();

            if (db.next()) {
                return createUser(db);
            }

            return null;
        } catch (SQLException e) {
            throw new ModelException("Erro ao buscar o usuário por ID", e);
        }
    }

    @Override
    public User findByEmail(String email) throws ModelException {
        try {
            DBHandler db = new DBHandler();

            String sql = "SELECT * FROM users WHERE email = ?";
            db.prepareStatement(sql);
            db.setString(1, email);
            db.executeQuery();

            if (db.next()) {
                return createUser(db);
            }

            return null;
        } catch (SQLException e) {
            throw new ModelException("Erro ao buscar o usuário por e-mail", e);
        }
    }

    private User createUser(DBHandler db) throws ModelException {
        try {
            User u = new User(db.getInt("id"));
            u.setName(db.getString("nome"));
            u.setGender(db.getString("sexo"));
            u.setEmail(db.getString("email"));
            u.setPassword(db.getString("password"));
            return u;
        } catch (SQLException e) {
            throw new ModelException("Erro ao criar o usuário", e);
        }
    }
}

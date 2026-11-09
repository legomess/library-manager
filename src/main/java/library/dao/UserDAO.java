package library.dao;

import library.service.DatabaseConnection;
import library.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {

    // CREATE - insere um novo usuário no banco
    public void save(User user) {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        // 4 colunas, 4 "?" -> não incluímos o "id" porque ele é AUTO_INCREMENT

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());

            stmt.executeUpdate(); // executa o INSERT

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ - busca todos os usuários
    public List<User> findAll() {
        List<User> users = new ArrayList<>(); // lista vazia que vai ser preenchida
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // executeQuery() retorna várias linhas

            while (rs.next()) { // percorre cada linha retornada
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user); // adiciona na lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // READ - busca um usuário pelo id
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // preenche o único "?"

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // se encontrou alguma linha
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // não achou nenhum usuário com esse id
    }

    // UPDATE - atualiza um usuário existente
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE id = ?";
        // 4 campos para atualizar + 1 "?" extra pro WHERE (identifica QUAL usuário)

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getId()); // último "?" é o WHERE id = ?

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE - remove um usuário pelo id
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

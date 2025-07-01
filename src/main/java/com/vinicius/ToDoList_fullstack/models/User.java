package com.vinicius.ToDoList_fullstack.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Objects;

@Entity // Indica que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados.
@Table(name = User.TABLE_NAME) // Especifica o nome da tabela no banco de dados.
public class User {
    public interface CreateUser{} // Grupo de validação para a criação de um usuário.
    public interface UpdateUser{} // Grupo de validação para a atualização de um usuário.

    public static final String TABLE_NAME = "user";

    @Id // Marca o campo 'id' como a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o 'id' será gerado automaticamente pelo banco de dados (auto-incremento).
    @Column(name = "id", unique = true) // Mapeia para a coluna 'id' no banco e garante que seja única.
    private Long id;

    @Column(name = "userName", length = 100, nullable = false, unique = true) // Mapeia para a coluna 'userName', define tamanho, não permite nulos e garante que seja único.
    @NotNull(groups = CreateUser.class) // Garante que 'userName' não seja nulo ao criar um usuário.
    @NotEmpty(groups = CreateUser.class) // Garante que 'userName' não seja vazio ao criar um usuário.
    @Size(groups = CreateUser.class, min = 2, max = 100) // Define o tamanho mínimo e máximo para 'userName' ao criar.
    private String UserName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Apenas permite que a 'password' seja escrita (recebida), mas não lida (retornada em JSON).
    @Column(name = "password", length = 60, nullable = false) // Mapeia para a coluna 'password', define tamanho e não permite nulos.
    @NotNull(groups = {CreateUser.class, UpdateUser.class}) // Garante que 'password' não seja nula ao criar ou atualizar.
    @NotEmpty(groups = CreateUser.class) // Garante que 'password' não seja vazia ao criar.
    @Size(groups = {CreateUser.class, UpdateUser.class} , min = 8, max = 60) // Define o tamanho mínimo e máximo para 'password' ao criar ou atualizar.
    private String password;

    //private List<task> = new ArrayList<Task>();

    public User(){

    }

    public User(Long id, String userName, String password) {
        this.id = id;
        UserName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(id, user.id) && Objects.equals(UserName, user.UserName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
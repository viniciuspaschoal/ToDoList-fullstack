package com.vinicius.ToDoList_fullstack.services;

import com.vinicius.ToDoList_fullstack.models.User;
import com.vinicius.ToDoList_fullstack.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById( Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário nãi encontrado! Id: " + id + ", Tipo: " +  User.class.getName()
        ));
    }

    @Transactional  //user sempre que for dar um create ou um update no banco
                    // @Transactional: Garante que as operações de banco de dados (como criar ou atualizar) sejam executadas como uma única transação atômica.
                    // Se algo falhar, todas as alterações são desfeitas para manter a consistência dos dados.
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);

        try{
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, pois há entidades relacionadas!");
        }
    }
}

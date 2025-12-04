package dio.projeto_dio_spring.repository;

import dio.projeto_dio_spring.handler.BusinessException;
import dio.projeto_dio_spring.handler.CampoObrigatorioException;
import dio.projeto_dio_spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository{
    public void save( User user ){
        if(user.getLogin() == null) throw new CampoObrigatorioException("Login");
        if(user.getPassword() == null) throw new CampoObrigatorioException("Password");
        System.out.println("SAVE - Recebendo o usuário na camada de repositório");
        System.out.println(user);
    }
    public void update( User user ){
        System.out.println("UPDATE - Recebendo o usuário na camada de repositório");
        System.out.println(user);
    }
    public void remove(Integer id){
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para excluir um usuário", id));
        System.out.println(id);
    }
    public List<User> listAll(){
        List<User> users = new ArrayList<>();
        users.add(new User("gleyson","password"));
        users.add(new User("frank","masterpass"));
        return users;
    }
    public User finById( Integer id){
        System.out.println(String.format("GET/id - Recebendo o id: %d para localizar um usuário", id));
        return new User("gleyson","password");
    }
    public User finByName( String name ){
        System.out.println(String.format("GET/id - Recebendo username: %s para localizar um usuário", name));
        return new User("gleyson","password");
    }

}

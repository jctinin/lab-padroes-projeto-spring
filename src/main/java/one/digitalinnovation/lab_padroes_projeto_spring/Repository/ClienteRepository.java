package one.digitalinnovation.lab_padroes_projeto_spring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.lab_padroes_projeto_spring.model.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    // Aqui podemos adicionar métodos personalizados, se necessário
    // Exemplo: List<Cliente> findByNome(String nome);

}

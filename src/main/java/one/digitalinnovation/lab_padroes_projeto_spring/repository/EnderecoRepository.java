package one.digitalinnovation.lab_padroes_projeto_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.lab_padroes_projeto_spring.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
    // Aqui podemos adicionar métodos personalizados, se necessário
    // Exemplo: List<Endereco> findByLogradouro(String logradouro);

}

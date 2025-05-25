package one.digitalinnovation.lab_padroes_projeto_spring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.lab_padroes_projeto_spring.model.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring.model.Endereco;
import one.digitalinnovation.lab_padroes_projeto_spring.repository.ClienteRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.repository.EnderecoRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.service.ClienteService;
import one.digitalinnovation.lab_padroes_projeto_spring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  @Autowired
  private ViaCepService viaCepService;

  @Override
  public Iterable<Cliente> buscarTodos() {
    return clienteRepository.findAll();
  }

  @Override
  public Cliente buscarPorId(Long id) {
    return clienteRepository.findById(id).orElse(null);
  }

  @Override
  public void inserir(Cliente cliente) {

    String cep = cliente.getEndereco().getCep();
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      Endereco novoEndereco = viaCepService.consultarCep(cep);
      enderecoRepository.save(novoEndereco);
      return novoEndereco;

    });
    cliente.setEndereco(endereco);
    clienteRepository.save(cliente);

  }

  @Override
  public void atualizar(Long id, Cliente cliente) {
    Optional<Cliente> clienteExistente = clienteRepository.findById(id);
    if (clienteExistente.isPresent()) {
      cliente.setId(id);
      String cep = cliente.getEndereco().getCep();
      Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
        Endereco novoEndereco = viaCepService.consultarCep(cep);
        enderecoRepository.save(novoEndereco);
        return novoEndereco;
      });
      cliente.setEndereco(endereco);
      clienteRepository.save(cliente);
    } else {
      throw new RuntimeException("Cliente não encontrado com o ID: " + id);
    }

  }

  @Override
  public void deletar(Long id) {
    Optional<Cliente> clienteExistente = clienteRepository.findById(id);
    if (clienteExistente.isPresent()) {
      clienteRepository.delete(clienteExistente.get());
    } else {
      throw new RuntimeException("Cliente não encontrado com o ID: " + id);
    }

  }

}

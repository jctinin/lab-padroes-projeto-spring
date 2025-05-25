package one.digitalinnovation.lab_padroes_projeto_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.lab_padroes_projeto_spring.Repository.ClienteRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.Repository.EnderecoRepository;
import one.digitalinnovation.lab_padroes_projeto_spring.model.Cliente;
import one.digitalinnovation.lab_padroes_projeto_spring.model.Endereco;
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

    Cliente clienteExistente = clienteRepository.findById(cliente.getId()).orElse(null);
    if (clienteExistente != null) {
      throw new RuntimeException("Cliente já existe com o ID: " + cliente.getId());

    }

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
    // TODO Auto-generated method stub

  }

  @Override
  public void deletar(Long id) {
    // TODO Auto-generated method stub

  }

}

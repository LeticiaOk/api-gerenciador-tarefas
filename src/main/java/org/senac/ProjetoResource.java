package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/projetos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjetoResource {

    @GET
    public List<Projeto> listarTodos() {
        return Projeto.listAll();
    }

    @GET
    @Path("/{id}")
    public Projeto buscarPorId(@PathParam("id") Long id) {
        return Projeto.findById(id);
    }

    @POST
    @Transactional
    public Projeto cadastrar(Projeto projeto) {
        projeto.persist();
        return projeto;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Projeto atualizar(@PathParam("id") Long id, Projeto dados) {
        Projeto projeto = Projeto.findById(id);
        if (projeto == null) {
            throw new NotFoundException("Projeto não encontrado");
        }
        projeto.nome = dados.nome;
        projeto.descricao = dados.descricao;
        return projeto;
    }

    // 🔄 ROTA ADICIONAL: Listar tarefas do projeto
    @GET
    @Path("/{id}/tarefas")
    public List<Tarefa> listarTarefasDoProjeto(@PathParam("id") Long id) {
        return Tarefa.list("projeto.id", id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Projeto projeto = Projeto.findById(id);
        if (projeto == null) {
            throw new NotFoundException("Projeto não encontrado");
        }
        projeto.delete();
    }

}

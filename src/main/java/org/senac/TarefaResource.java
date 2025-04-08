package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {

    @GET
    public List<Tarefa> listarTodas() {
        return Tarefa.listAll();
    }

    @GET
    @Path("/{id}")
    public Tarefa buscarPorId(@PathParam("id") Long id) {
        return Tarefa.findById(id);
    }

    @POST
    @Transactional
    public Tarefa cadastrar(Tarefa tarefa) {
        tarefa.persist();
        return tarefa;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Tarefa atualizar(@PathParam("id") Long id, Tarefa dados) {
        Tarefa tarefa = Tarefa.findById(id);
        if (tarefa == null) {
            throw new NotFoundException("Tarefa não encontrada");
        }

        tarefa.titulo = dados.titulo;
        tarefa.descricao = dados.descricao;
        tarefa.status = dados.status;
        tarefa.usuario = dados.usuario;
        tarefa.projeto = dados.projeto;

        return tarefa;
    }

    // 🔄 ROTA ADICIONAL: Filtrar por status
    @GET
    @Path("/status/{status}")
    public List<Tarefa> filtrarPorStatus(@PathParam("status") String status) {
        return Tarefa.list("status", status);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Tarefa tarefa = Tarefa.findById(id);
        if (tarefa == null) {
            throw new NotFoundException("Tarefa não encontrada");
        }
        tarefa.delete();
    }

}

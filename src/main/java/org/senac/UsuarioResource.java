package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @GET
    public List<Usuario> listarTodos() {
        return Usuario.listAll();
    }

    @GET
    @Path("/{id}")
    public Usuario buscarPorId(@PathParam("id") Long id) {
        return Usuario.findById(id);
    }

    @POST
    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        usuario.persist();
        return usuario;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Usuario atualizar(@PathParam("id") Long id, Usuario dados) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        usuario.nome = dados.nome;
        usuario.email = dados.email;
        return usuario;
    }

    // 🔄 ROTA ADICIONAL: Listar tarefas do usuário
    @GET
    @Path("/{id}/tarefas")
    public List<Tarefa> listarTarefasDoUsuario(@PathParam("id") Long id) {
        return Tarefa.list("usuario.id", id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        usuario.delete();
    }

}

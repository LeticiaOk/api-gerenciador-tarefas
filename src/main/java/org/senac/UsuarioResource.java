package org.senac;

import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.faulttolerance.Fallback;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RateLimit(value = 5, window = 1, windowUnit = ChronoUnit.MINUTES)
    @Fallback(fallbackMethod = "fallbackParaRateLimit")
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
    @Idempotent(expireAfter = 3600) // expira em 1 hora
    public Usuario cadastrar(Usuario usuario) {
        usuario.persist();
        return usuario;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Idempotent
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
    @Idempotent

    public void deletar(@PathParam("id") Long id) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        usuario.delete();
    }

    public List<Usuario> fallbackParaRateLimit() {
        throw new WebApplicationException(
                Response.status(429)
                        .entity("Taxa de requisições excedida. Por favor, tente novamente mais tarde.")
                        .type(MediaType.TEXT_PLAIN)
                        .build()
        );
    }
}

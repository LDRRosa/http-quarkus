package com.leandro.controller;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.leandro.model.Mensagem;
import com.leandro.service.MensagemService;

import java.util.List;

@Path("/mensagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensagemController {

    @Inject
    MensagemService service;

    @GET
    public List<Mensagem> listar() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        return service.buscarPorId(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response criar(Mensagem mensagem) {
        Mensagem nova = service.criar(mensagem);
        return Response.status(Response.Status.CREATED).entity(nova).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean removido = service.deletar(id);

        if (removido) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
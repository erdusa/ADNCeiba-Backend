package com.ceiba.carro.puerto.repositorio;

import com.ceiba.carro.modelo.entidad.Carro;

public interface RepositorioCarro {

    /**
     * Permite consultar un carro por su identificador
     *
     * @param idCarro identificador del carro
     * @return los datos del carro
     */
    Carro consultar(Long idCarro);
}

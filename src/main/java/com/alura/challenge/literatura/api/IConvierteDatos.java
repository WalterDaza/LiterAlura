package com.alura.challenge.literatura.api;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);

}

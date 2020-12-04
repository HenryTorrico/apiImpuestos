package com.example.demo.configurate;

import java.util.List;

/**
 *
 * @author ruth
 */
public interface AbstractReadOnly<T> {

    T leerPorCodigo(String codigo);

    List<T> listar();

}

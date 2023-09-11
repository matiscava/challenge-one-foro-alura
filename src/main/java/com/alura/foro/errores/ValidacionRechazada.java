package com.alura.foro.errores;

public class ValidacionRechazada extends RuntimeException {
    public ValidacionRechazada(String s) {
        super(s);
    }
}

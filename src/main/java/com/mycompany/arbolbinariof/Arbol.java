/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbolbinariof;


/**
 *
 * @author 52999
 */
public class Arbol {

    class Nodo {
        String info;  
        Nodo izq, der;

        Nodo(String info) {
            this.info = info;
            this.izq = null;
            this.der = null;
        }
    }

    Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    // Método para construir el árbol a partir de una expresión aritmética
    public Nodo construirArbol(String expresion) {

        
        //Eliminar los paréntesis extras
        while (expresion.startsWith("(") && expresion.endsWith(")")) {
            expresion = expresion.substring(1, expresion.length() - 1);
        }       

        //Busca la raiz (el operador que va más arriba)
        int operadorPrincipal = encontrarOperadorPrincipal(expresion);

        if (operadorPrincipal == -1) {
            return new Nodo(expresion); //No hay ninguna operacion, solo un número o letro, aquí termina la recursividad (CASO BASE)
        }

        //Tomar el valor del operador principal
        String operador = String.valueOf(expresion.charAt(operadorPrincipal));
        Nodo nodo = new Nodo(operador);

        //Toma la parte de la expresión que está a la izquierda del operador principal (del 0 hasta uno antes del operador) 
        String izquierda = expresion.substring(0, operadorPrincipal);

        //Toma la parte de la expresión que está a la derecha del operador principal (de uno después del operador hasta el final)
        String derecha = expresion.substring(operadorPrincipal + 1);

        //Crea los nodos izquierdos y derechos utilizando la recursividad hasta que termine todo el árbol (CASO RECURSIVO)
        nodo.izq = construirArbol(izquierda);
        nodo.der = construirArbol(derecha);

        return nodo;
    }
    
    private int encontrarOperadorPrincipal(String expresion) {
        int nivelParentesis = 0;
        int posicion = -1;

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '(') {
                nivelParentesis++;
            } else if (c == ')') {
                nivelParentesis--;
            } else if (nivelParentesis == 0 && esOperador(c)) {
                if (posicion == -1 || prioridad(c) <= prioridad(expresion.charAt(posicion))) {
                    posicion = i;
                }
            }
        }

        return posicion;
    }

    // Verificar si un carácter es operador
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Definir prioridades de operadores
    private int prioridad(char operador) {
        switch (operador) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    //Imprimir inorden
    public String imprimirInOrden(Nodo nodo) {
    if (nodo == null) {
        return "";
    }
    return imprimirInOrden(nodo.izq) + nodo.info + " " + imprimirInOrden(nodo.der);
}
    
    // Imprimir preorden
    public String imprimirPreOrden(Nodo nodo) {
    if (nodo == null) {
        return "";
    }
    return nodo.info + " " + imprimirPreOrden(nodo.izq) + imprimirPreOrden(nodo.der);
}

    // Imprimir posorden
    public String imprimirPosOrden(Nodo nodo) {
    if (nodo == null) {
        return "";
    }
    return imprimirPosOrden(nodo.izq) + imprimirPosOrden(nodo.der) + nodo.info + " ";
}

    // Imprimir árbol visualmente
    public void imprimirArbol(Nodo nodo, int nivel) {
        if (nodo != null) {
            imprimirArbol(nodo.der, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("   ");
            }
            System.out.println(nodo.info);
            imprimirArbol(nodo.izq, nivel + 1);
        }
    }
}

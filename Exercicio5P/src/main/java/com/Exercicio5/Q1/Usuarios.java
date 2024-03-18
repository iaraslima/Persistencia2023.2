package com.Exercicio5.Q1;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "estande")
public class Usuarios {

    @JacksonXmlElementWrapper(localName = "usuarios")
    @JacksonXmlProperty(localName = "usuario")
    public List<Usuario> usuarios;

    public Usuarios() {
    }

    public Usuarios(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    @Override
    public String toString(){
        return "Usuarios{" +
                "usuarios=" + usuarios +
                '}';
    }
}

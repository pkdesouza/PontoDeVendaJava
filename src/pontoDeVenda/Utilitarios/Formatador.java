package pontoDeVenda.Utilitarios;

import java.awt.event.KeyEvent;

public class Formatador {

    private static final String caracteres = "0123456789";
    
    public static void formatarCampoNumerico(KeyEvent evt) {  
        if (!caracteres.contains(evt.getKeyChar() + "")) 
            evt.consume();
    }

    public static void formatarCampoLetras(KeyEvent evt) {
        if (caracteres.contains(evt.getKeyChar() + ""))
            evt.consume();
    }
}
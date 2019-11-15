package pontoDeVenda.Exibicao;

import java.awt.Color;
import javax.swing.UIManager;

public class Main {

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc="Definindo as cores da interface gráfica">
        try {
            UIManager.put("nimbusBase", new Color(1, 0, 244));
            UIManager.put("nimbusBlueGrey", new Color(240, 240, 240));
            UIManager.put("control", new Color(241, 255, 255));

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PontoDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        PontoDeVenda pontoDeVenda = new PontoDeVenda();
        pontoDeVenda.setEnabled(true);
        pontoDeVenda.setVisible(true);
        pontoDeVenda.toFront();
    }

}

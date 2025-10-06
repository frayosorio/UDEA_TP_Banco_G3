package servicios;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class MascaraNumeroReal extends DocumentFilter {

    @Override
    public void replace(FilterBypass filtro, int offset, int length, String textoDigitado, AttributeSet attr)
            throws BadLocationException {
        Document documento = filtro.getDocument();
        String textoActual = documento.getText(0, documento.getLength());
        textoActual+=textoDigitado;

        if (textoActual.matches("[0-9]+[.]?[0-9]*")) {

            super.insertString(filtro, offset, textoDigitado, attr);
        }
    }

}

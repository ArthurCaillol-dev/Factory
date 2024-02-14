// BatimentVisitor.java
package com.factory.batiment;

public interface BatimentVisitor {
    void visit(Base base);
    void visit(Tourelle tourelle);
    void visit (Foreuse foreuse);
    // Ajoutez d'autres méthodes pour chaque type de bâtiment que vous pourriez avoir
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import logika.IHra;

/*******************************************************************************
 * Interface Observer.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   listopad 2017
 */
public interface Observer {
    
    /**
     * Metoda update se volá z metody notifyObservers() sledovaného objektu (Subject),
     * u kterého je tento Observer registrován - sledovaný tak upozorní své sledovatele,
     * že u mohlo dojít ke změně stavu, na který by sledovatelé mohli chtít reagovat.
     */
    public void update();
    
    /**
     * Metoda novaHra se stará o přeregistrování vazeb z instance staré 
     * (ukončované) hry na instanci nové (spouštěné) hry.
     * @param hra instance hry
     */
    public void novaHra(IHra Hra);
}



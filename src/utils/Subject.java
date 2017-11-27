/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/*******************************************************************************
 * Interface Subject.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   listopad 2017
 */
public interface Subject {
    
    /**
     * Metoda, která vytváří vazbu mezi sledovaným (Subject) a sledovatelem (Observer).
     * @param observer sledovatel, který bude zaregistrovaný k Subjectu
     */
    void registerObserver(Observer observer);
    
    /**
     * Metoda, která ruší vazbu mezi sledovaným (Subject) a sledovatelem (Observer).
     * @param observer pozorovatel, kterĂ˝ mĂˇ bĂ˝t vyĹ™azen ze seznamu pozorovatelĹŻ
     */
    void removeObserver(Observer observer);
    
    /**
     * Metoda, kterou sledovaný informuje své sledovatele, že u něj mohlo dojít
     * ke změně stavu, na který by sledovatelé mohli chtít reagovat.
     */
    void notifyObservers();
    
}

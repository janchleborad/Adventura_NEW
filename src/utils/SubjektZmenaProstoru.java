/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Honysek
 */
public interface SubjektZmenaProstoru {
    
    public void registerObserver(ObserverZmenaProstoru observer);
    
    public void unregisterObserver(ObserverZmenaProstoru observer);
    
    public void notifyObservers();
    
}

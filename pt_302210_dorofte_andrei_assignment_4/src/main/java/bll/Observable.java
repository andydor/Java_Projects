package bll;

import presentation.Observer;

public interface Observable {
    public void registerObserver(Observer observer);
    public void notifyObservers();
    public void removeObserver(Observer observer);
}

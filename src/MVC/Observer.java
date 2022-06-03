/**
 * Author: Michael Odhiambo
 * Email: michaelallanodhiambo@gmail.com
 */

package MVC;

/**
 * The Observer pattern provides a design for publish/subscribe mechanism among objects. The observer
 * pattern allows an object ( the observer ) to register its interest in another object ( the observable ).
 * Whenever the observable wants to notify its observers of a change, it will call an update() method
 * on each observer.
 *
 * An observable will provide a method through which observers can register and deregister their interest
 * in updates.
 */


public interface Observer {
    abstract void update();
}

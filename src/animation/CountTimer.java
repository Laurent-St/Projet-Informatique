/*
 * Comme Count sauf qu'ici c'est une minuterie effectuant une action: elle appelle la fonction atCount d�finie dans les classes
 * qui impl�mentent l'interface CountTimerListener (� chaque incr�mentation du compte).
 * 
 * (Utile pour les objets disposant d'une animation qui n�cessite une action sp�ciale � chaque incr�mentation du compte)
 */

package animation;

public class CountTimer extends Count {

	private static final long serialVersionUID = 1L;
	private CountTimerListener source;
	
	public CountTimer(int max, int sleepTime, CountTimerListener source) {
		super(max,sleepTime);
		this.source = source;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
			this.increment();
			source.atCount(this);
		}		
	}

}

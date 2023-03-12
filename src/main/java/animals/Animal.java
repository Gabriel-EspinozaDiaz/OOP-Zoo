package animals;

import areas.IArea;

/**
 * You can modify the contents of this class, but you cannot:
 * - change the name, parameters or return types of provided methods
 * - change it to an interface or concrete class
 * - remove it entirely
 */
public abstract class Animal
{
	private String nickname;


	public Animal(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return Returns this animal's given name.
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Check whether two animals can live together.
	 * @param animal The animal for which to check compatibility with this animal.
	 * @return Returns true for compatible animals and false otherwise.
	 */
	public abstract boolean isCompatibleWith(Animal animal);

	// Checks if an animal is compatible with the parameter environment area
	public abstract boolean canLiveIn(IArea area);

}


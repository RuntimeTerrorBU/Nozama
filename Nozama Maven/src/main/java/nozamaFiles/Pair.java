package nozamaFiles;

/**
 * The Pair class creates a pair of items
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class Pair<T1, T2> {
	public T1 first;
	public T2 second;

	/**
	 * Pair constructor
	 *
	 * @param first, T1 item in the pair
	 * @param second, T2 item in the pair
	 */
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}

	@Override
	/**
	 * Creates a specific mapping to a value
	 *
	 * @return integer hashed value of the input value
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	/**
	 * Tells if two objects are equal
	 *
	 * @param object to compare to the object being used
	 * @return true if the two objects are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
}
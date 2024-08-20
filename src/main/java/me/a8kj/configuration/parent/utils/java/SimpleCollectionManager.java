package me.a8kj.configuration.parent.utils.java;

import java.util.Collection;

/**
 * A utility class for managing a collection of elements.
 * Provides basic operations to add, remove, and clear elements, as well as
 * checking for containment.
 *
 * @param <V> the type of elements in the collection.
 */
public class SimpleCollectionManager<V> {

    /**
     * The collection being managed.
     */
    private Collection<V> collection;

    /**
     * Constructs a new SimpleCollectionManager with the specified collection.
     *
     * @param newCollection the collection to be managed; must not be {@code null}.
     */
    public SimpleCollectionManager(Collection<V> newCollection) {
        this.collection = newCollection;
    }

    /**
     * Adds an element to the collection if it is not already present.
     *
     * @param value the element to add.
     */
    public void add(V value) {
        if (contains(value))
            return;
        this.collection.add(value);
    }

    /**
     * Removes an element from the collection if it is present.
     *
     * @param value the element to remove.
     */
    public void remove(V value) {
        if (contains(value))
            return;
        this.collection.remove(value);
    }

    /**
     * Clears all elements from the collection.
     * 
     * <p>
     * If the collection is already empty, this method does nothing.
     * </p>
     */
    public void clearAll() {
        if (collection.isEmpty())
            return;
        this.collection.clear();
    }

    /**
     * Checks if the collection contains a specified element.
     *
     * @param value the element to check for.
     * @return {@code true} if the collection contains the element; {@code false}
     *         otherwise.
     */
    public boolean contains(V value) {
        return this.collection.contains(value);
    }

    /**
     * Updates the collection being managed with a new collection.
     *
     * @param newCollection the new collection to set; must not be {@code null}.
     * @throws ClassCastException if the new collection is not compatible with the
     *                            type of the original collection.
     */
    @SuppressWarnings("unchecked")
    public void updateCollection(@SuppressWarnings("rawtypes") Collection newCollection) {
        this.collection = newCollection;
    }

    /**
     * Returns the collection being managed.
     *
     * @return the current collection.
     */
    public Collection<V> getCollection() {
        return collection;
    }
}

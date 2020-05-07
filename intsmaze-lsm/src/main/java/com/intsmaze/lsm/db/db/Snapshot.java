package com.intsmaze.lsm.db.db;

/**
 * Represents a database snapshot.
 */
public class Snapshot implements Comparable<Snapshot> {

    public static final Snapshot MAX = new Snapshot(Long.MAX_VALUE);
    public static final Snapshot MIN = new Snapshot(0);

    private final long id;

    public Snapshot(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }

    @Override
    public int compareTo(Snapshot o) {
        return Long.compare(id, o.id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Snapshot snapshot = (Snapshot) o;

        if (id != snapshot.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "id=" + id +
                '}';
    }
}

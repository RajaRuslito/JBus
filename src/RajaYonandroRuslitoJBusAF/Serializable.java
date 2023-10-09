package RajaYonandroRuslitoJBusAF;

import java.util.HashMap;

public class Serializable implements Comparable<Serializable> {
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable() {
        if (mapCounter.keySet().contains(this.getClass())) {
            mapCounter.put(this.getClass(), mapCounter.get(this.getClass()) + 1);
            this.id = mapCounter.get(this.getClass());
        } else {
            mapCounter.put(this.getClass(), 0);
            this.id = 0;
        }
    }

    // Making sure that this method is used for classes that extends Serializable, otherwise error
    public static <T extends Serializable> Integer getLastAssignedId(Class<T> tClass) {
        return mapCounter.get(tClass);
    }

    public static <T extends Serializable> Integer setLastAssignedId(Class<T> tClass, int id) {
        return mapCounter.put(tClass, id);
    }

    @Override
    public int compareTo(Serializable o) {
        if (id == o.id) {
            return 0;
        } else if (id > o.id) {
            return 1;
        }

        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Serializable) {
            Serializable sr = (Serializable) obj;
            return id == sr.id;
        }

        return false;
    }

    public boolean equals(Serializable ser) {
        return id == ser.id;
    }
}

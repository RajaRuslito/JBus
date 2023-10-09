package RajaYonandroRuslitoJBusAF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Algorithm {

    private Algorithm(){

    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current))
                return true;
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, T val) {
        final Predicate<T> pred = val::equals;
        return exists(iterator, pred);
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }
    public static <T> boolean exists(Iterable<T> iterable, T val) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, val);
    }
    public static <T> boolean exists(T[] arrT, T val) {
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return exists(it, val);
    }
    public static <T> boolean exists(T[] arrT, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return exists(it, pred);
    }



    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterable<T> iterable, T val){
        final Iterator<T> it = iterable.iterator();
        return collect(it, val);
    }
    public static <T> List<T> collect(T[] arrT, T val){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return collect(it, val);
    }
    public static <T> List<T> collect(T[] arrT, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterator<T> iterator, T val){
        final Predicate<T> pred = val::equals;
        return collect(iterator, pred);
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred){
        List<T> newlist = new ArrayList<>();
        while(iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current)){
                newlist.add(current);
            }
        }
        return newlist;
    }



    public static <T> int count(Iterator<T> iterator, T val){
        /*final Predicate<T> pred = val::equals;
        return count(iterator, pred);*/
        int cnt = 0;
        while(iterator.hasNext()){
            T current = iterator.next();
            if(current.equals(val)){
                cnt++;
            }
        }
        return cnt;
    }
    public static <T> int count(T[] arrT, T val){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return count(it, val);
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;
        while(iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current)){
                cnt++;
            }
        }
        return cnt;
    }
    public static <T> int count(T[] arrT, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return count(it, pred);
    }
    public static <T> int count(Iterable<T> iterable, T val){
        final Iterator<T> it = iterable.iterator();
        return count(it, val);
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }


    public static <T> T find(Iterator<T> iterator, T val){
        /*final Predicate<T> pred = val::equals;
        return find(iterator, pred);*/
        while(iterator.hasNext()){
            T current = iterator.next();
            if(current.equals(val)){
                return current;
            }
        }
        return null;
    }
    public static <T> T find(T[] arrT, T val){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return find(it, val);
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T current = iterator.next();
            if(pred.predicate(current)){
                return current;
            }
        }
        return null;
    }
    public static <T> T find(T[] arrT, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return find(it, pred);
    }
    public static <T> T find(Iterable<T> iterable, T val){
        final Iterator<T> it = iterable.iterator();
        return find(it, val);
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }


    public static <T> List <T> paginate(T[] arrT, int pageSize, int page, Predicate<T> pred){
        final Iterator<T> it = Arrays.stream(arrT).iterator();
        return paginate(it, page, pageSize, pred);
    }
    public static <T> List<T> paginate(Iterable<T> iterable, int pageSize, int page, Predicate<T> pred){
        final Iterator<T> it = iterable.iterator();
        return paginate(it, page, pageSize, pred);
    }
    public static <T> List<T> paginate(Iterator<T> iterator, int pageSize, int page, Predicate<T> pred) {
        List<T> retPage = new ArrayList<>();
        int cnt = 0;
        //int lastIndex = page * pageSize;
        while (iterator.hasNext() && (retPage.size() < page)) {
            T check = iterator.next();
            if ((cnt < ((page + 1) * pageSize)) && (cnt > (page * pageSize))) {
                retPage.add(check);
                cnt++;
            } else {
                cnt++;
            }
        }
        return retPage;
    }
}

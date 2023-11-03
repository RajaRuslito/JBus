package RajaYonandroRuslitoJBusAF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;
import java.util.function.Consumer;

import java.util.*;

public class Algorithm {
    private Algorithm() {
    }

    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        Iterator<T> i = iterable.iterator();
        return collect(i, value);
    }

    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> i = iterable.iterator();
        return collect(i, predicate);
    }

    public static <T> List<T> collect(T[] array, T value) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, value);
    }

    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    public static <T> List<T> collect(T[] array, Predicate<T> predicate) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, predicate);
    }

    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList();

        while(iterator.hasNext()) {
            T tempVar = iterator.next();
            if (pred.predicate(tempVar)) {
                list.add(tempVar);
            }
        }

        return list;
    }

    public static <T> int count(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    public static <T> int count(Iterable<T> iterable, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterable, pred);
    }

    public static <T> int count(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    public static <T> int count(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(it, value);
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int count = 0;

        while(iterator.hasNext()) {
            if (pred.predicate(iterator.next())) {
                ++count;
            }
        }

        return count;
    }

    public static <T> T find(Iterator<T> iterator, T number) {
        Objects.requireNonNull(number);
        Predicate<T> pred = number::equals;
        return find(iterator, pred);
    }

    public static <T> T find(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    public static <T> T find(T[] arr, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(arr).iterator();
        return find(it, pred);
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return current;
            }

            return null;
        }
    }

    public static <T> T find(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return true;
            }

            return false;
        }
    }

    public static <T> List<T> paginate(T[] arr, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = Arrays.stream(arr).iterator();
        return paginate(i, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return paginate(i, page, pagesize, pred);
    }

    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pagesize, Predicate<T> pred) {
        List<T> pageResult = new ArrayList();
        int count = 0;
        int startindex = page * pagesize;
        int endindex = startindex + pagesize;
        while(iterator.hasNext()) {
            T obj = iterator.next();
            if (pred.predicate(obj)) {
                if (count >= startindex && count < endindex) {
                    pageResult.add(obj);
                }

                ++count;
            }
        }

        return pageResult;
    }
}

/*
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
        */
/*final Predicate<T> pred = val::equals;
        return count(iterator, pred);*//*

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
        */
/*final Predicate<T> pred = val::equals;
        return find(iterator, pred);*//*

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
            if ((cnt <= ((page + 1) * pageSize)) && (cnt == (page * pageSize))) {
                retPage.add(check);
                cnt++;
            } else {
                cnt++;
            }
        }
        return retPage;
    }
}
*/

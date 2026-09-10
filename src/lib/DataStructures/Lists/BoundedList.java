package lib.DataStructures.Lists;

public interface BoundedList<ListData> extends List<ListData> {

    boolean insert(ListData element);

    boolean isFull();

}
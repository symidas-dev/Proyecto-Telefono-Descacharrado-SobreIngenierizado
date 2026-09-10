package lib.DataStructures.Lists;

public interface List<ListData> {
    public boolean remove(ListData element);

    public ListData get(int index);

    public boolean contains(ListData element);

    public int size();

    public boolean isEmpty();

    public void clear();
}

public class BinaryInsertionSort {

    private int[] array;
    private int array_index;
    private int compare_index;
    private int key;
    private boolean startOfIteration = false;
    private boolean locFound=false;
    private int loc;
    private int mid;

    private int low, high;
    public BinaryInsertionSort(int[] array) {
        this.array = array;
        array_index = 1;
    }

    public int[] sortOnlyOneItem() {

        if (startOfIteration == false) {
            key = array[array_index];
            compare_index = array_index - 1;
            startOfIteration = true;
            low=0; high=compare_index;
        }


        if(!locFound){
            if (low <= high) {
                mid = low + (high - low) / 2;
                if (key == array[mid]){
                    loc= mid + 1;
                    locFound=true;
                }
                else if (key > array[mid])
                    low = mid + 1;
                else
                    high = mid - 1;
                return array;
            }
            loc=low;
            locFound=true;
        }

        if(compare_index>=loc){
            array[compare_index+1]=array[compare_index];
            compare_index--;
            return array;
        }
        else {
            array[compare_index + 1] = key;
            array_index++;
        }
        startOfIteration = false;
        locFound=false;
        return array;
    }
    public int binarySearch(int a[], int item, int low, int high)
    {
        while (low <= compare_index) {
            int mid = low + (compare_index - low) / 2;
            if (key == a[mid])
                return mid + 1;
            else if (key > a[mid])
                low = mid + 1;
            else
                compare_index = mid - 1;
        }

        return low;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getMid(){
        return mid;
    }

    public int getArrayIndex() {
        return array_index;
    }

    public void setArrayIndex(int array_index) {
        this.array_index = array_index;
    }

    public int getCompareIndex() {
        return compare_index;
    }

    public void setCompareIndex(int compare_index) {
        this.compare_index = compare_index;
    }

    public boolean getStartOfIteration() {
        return startOfIteration;
    }

    public void setStartOfIteration(boolean startOfIteration) {
        this.startOfIteration = startOfIteration;
    }
}
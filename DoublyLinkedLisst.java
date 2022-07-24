package week02;

/*
#### 2.实现一个双向链表类，要求如下：

        （1）泛型类

        （2）能够正确插入、删除、查找、遍历、翻转链表

        （3）能够对一般错误进行抛出并捕获，例如删除一个不存在的元素

        （4）自己编写测试类测试各个方法

 */
public class DoublyLinkedLisst<T> {

    //测试方法
    public static void main(String[] args) {
        DoublyLinkedLisst<Integer> dll = new DoublyLinkedLisst<>();
        for(int i=2;i<5;i++){
            dll.addLast(i);
        }
        for(int i=2;i>=0;i--){
            dll.addFirst(i);
        }
        System.out.println("插入后");
        dll.print();   //0 1 2 2 3 4
        dll.removeFirst(); //1 2 2 3 4
        dll.removeLast(); //1 2 2 3
        dll.remove(1);//1 2 3
        System.out.println("删除后");
        dll.print();//1 2 3
        System.out.println("查找10:");
        dll.remove(10);
    }



    private static int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T>{
        private  T data;
        private Node<T> front;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }

        public T getData(){
            return data;
        }

        public void setData(T data){
            this.data = data;
        }

        public Node<T> getFront(){
            return front;
        }

        public void setFront(Node<T> front){
            this.front = front;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public static int getSize(){
        return size;
    }


    public void addFirst(T value){
        Node<T> node = new Node<>(value);
        if(size == 0){
            first = node;
            last = first;
        }
        else{
            node.next = first;
            first.front = node;
            first = node;
        }
        size++;
    }

    public void addLast(T value){
        if(size == 0){
             addFirst(value);
        }
        else{
            Node<T> node  = new Node<>(value);
            last.next = node;
            node.front = last;
            last = node;
        }
        size++;
    }


    public void add(int index,T value){
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("数据下标越界");
        }
        else if(index == 0){
            addFirst(value);
        }
        else if(index == size){
            addLast(value);
        }
        else{
            Node<T>node = new Node<>(value);
            Node<T> head = first;
            for(int i=0;i<index-1;i++){
                head = head.getNext();
            }
            node.next = head.next;
            head.next = node;
            node.front = head;
            node.next.front = node;
        }
        size++;
    }

    public T removeFirst(){
        if(size==0){
            throw new RuntimeException("链表为空");
        }
        T data = first.getData();
        Node<T> node = first.next;
        node.setFront(null);
        first = node;
        return data;
    }


    public T removeLast(){
        if(size==0){
            throw new RuntimeException("链表为空");
        }
        T data = last.getData();
        Node<T> node = last.front;
        node.setNext(null);
        last = node;
        return data;
    }

    public T remove(int index){
        if(size == 0){
            throw new RuntimeException("链表为空");
        }
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("数据下标越界");
        }
        else if(index == 0){
            return removeFirst();
        }
        else if(index == size-1){
            return removeLast();
        }
        else{
            Node<T> node = first;
            for(int i= 0;i<index-1;i++){
                node = node.next;
            }
            Node<T> temp = node.next;
            T data = temp.getData();
            node.next = temp.next;
            temp.next.front = node;
            return data;
        }
    }


    public void removeElement(T value){
        if(!check(value)){
            throw new RuntimeException("找不到要删除的元素");
        }
        Node<T> node = first;
        while(node!=null){
            if(node.getData()==value){
                if(node.front == null)
                {
                    first = node.next;
                    node.next.front = null;
                }
                else if(node.next==null){
                    node.front.next = null;
                }else{
                    node.front.next = node.next;
                    node.next.front = node.front;
                }
                System.out.println("删除成功");
                size--;
                break;
            }
        }
    }


    public T searchData(int index){
        if(size == 0){
            throw new RuntimeException("链表为空");
        }
        else if(index<0||index>size-1){
            throw new IndexOutOfBoundsException("数据下标越界");
        }
        else{
            Node<T> node = first;
            for(int i=0;i<index;i++)
            {
                node = node.next;
            }
            return node.getData();
        }
    }


    public void print(){
        if(size == 0){
            System.out.println("该链表为空");
        }
        Node<T> node = first;
        while(node!=null){
            System.out.print(node.getData()+"  ");
            node = node.next;
        }
        System.out.println();
    }


    public boolean check(T value){
        Node<T> node = first;
        while(node!=null){
            if(node.getData()==value){
                return true;
            }

        }
        return false;
    }

}








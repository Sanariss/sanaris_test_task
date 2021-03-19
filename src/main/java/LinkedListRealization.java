public class LinkedListRealization<E> {
    private int size = 0;
    private transient Node<E> first; // нужна для задания ссылки предыдущей ноде на следующую вновь созданную ноду.
    private transient Node<E> last; // переменная содержащая ссылку на последнюю созданную ноду.
    private transient Node<E> linkFirst; // постоянно содержит ссылку на первую ноду
    private transient Node<E> linkLast; // постоянно содержит ссылку на самую последнюю созданную ноду

    // добавляем элемент (создаем каждый раз новый объект Node)
    public void add(E elem) {
        if (size == 0) {
            // если это первая нода, то ссылок на другие ноды нет.
            // но мы запоминает ссылку на самую первую ноду - linkFirst
            // и увеличиваем size - каунтер нод
            first = new Node<>(first, elem, last);
            linkFirst(first);
        } else {
            // мы обнуляем last т.к. в дальнейшем мы его используем, а у второго объекта при создании не может быть
            //ссылки на будующий объект
            // 1. мы создаем ноду nextNode - first - ссылка на ноду созданную ранее
            // 2. last = ссылка на вновь созданную ноду
            // 3. методом setNext - мы устанавливаем ссылку на nextNode - предыдущей Node.
            // 4. first - принимает значение ссылки на только-что созданную nextNode,
            // для того что-бы в дальнейшем при создании 3, 4 и т.д. нод мы могли установить ссылку на предыдущую ноду.
            // 5. linkLast - нужна для хранения ссылки на последнюю созданную ноду.
            // 6. мы увеличиваем size - дабы зафиксировать добавления еще 1 ноды.
            Node<E> nextNode = new Node<>(first, elem, null);
            last = nextNode;
            first.setNext(last);
            first = nextNode;
            linkLast(last);
        }
        size++;
    }

    public void clear() {
        if (size != 0) {
            Node<E> elem = linkFirst;
            Node<E> elem2;
            for (int buff = 0; buff < size; buff++) {
                elem2 = elem.getNext();
                elem.prev = null;
                elem.next = null;
                elem.item = null;
                elem = elem2;
            }
            size = 0;
            linkFirst = null;
            linkLast = null;
            System.out.println("Все элементы успешно удалены");
        } else {
            System.out.println("Нет элементов в коллекции");
        }
    }

    public void addAll(E[] elem) {
        for (E element : elem) {
            add(element);
        }
    }

    public void getSize() {
        System.out.println("На текущий момент создано " + size + " элементов");
    }

    // запоминаем ссылку на первую ноду
    private void linkFirst(Node<E> firstLink) {
        linkFirst = firstLink;
    }


    // запоминаем ссылку на последнюю ноду
    private void linkLast(Node<E> lastLink) {
        linkLast = lastLink;
    }

    @SuppressWarnings({"ConstantConditions"})
    public void get(int index) {
        int buff = size;
        Node<E> item;
        do {
            if (index < 0) {
                System.out.println("Вы указали отрицательный индекс, так быть не может!");
                break;
            }

            if (buff / 2 >= index) {
                item = linkFirst;
                for (buff = 0; buff != index; buff++) {
                    item = item.getNext();
                }
                System.out.println(item.getItem().toString());
                break;
            }

            if (buff / 2 < index && index <= size) {
                item = linkLast;
                for (; buff - 1 != index; buff--) {
                    item = item.getPrev();
                }
                System.out.println(item.getItem().toString());
            } else {
                System.out.println("Вы вышли за границы коллекции");
            }

        } while (false);
    }

    public void remove(int index) {
        Node<E> prevNode;  // буфферная нода
        Node<E> nextNode;  // буфферная нода
        if (index < size && index >= 0) {   // первая глобальная проверка - попадает ли индекс в массив объектов
            if (index > size / 2) { // вторая проверка показывает начинаем мы обход с первого элемента или последнего
                if (index != size - 1) { // выясняем - является ли элемент самым последним в коллекции
                    Node<E> lastNode = linkLast;
                    for (int buff = size; index != (buff - 1); buff--) {
                        lastNode = lastNode.getPrev();
                    }
                    prevNode = lastNode.getPrev();
                    nextNode = lastNode.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    lastNode.allToNull();
                    size--;
                } else {
                    prevNode = linkLast.getPrev();
                    linkLast.allToNull();
                    prevNode.setNext(null);
                    linkLast = prevNode;
                    size--;
                }

            } else {
                if (index != 0) { // проверка не является ли элемент первым в коллекции.
                    Node<E> firstNode = linkFirst;
                    for (int buff = 0; index != buff; buff++) {
                        firstNode = firstNode.getNext();
                    }
                    prevNode = firstNode.getPrev();
                    nextNode = firstNode.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    firstNode.allToNull();
                    size--;
                } else {
                    if (index != size - 1) {  // проверка а не является ли элемент единственным в коллекции
                        nextNode = linkFirst.getNext();
                        nextNode.setPrev(null);
                        linkFirst = nextNode;
                        size--;
                    } else {
                        linkFirst.allToNull();
                        size--;
                    }
                }

            }
        } else {

            System.out.println("Вы вышли за границу коллекции");

        }
    }

    // Экземпляр класса Node<E> - по сути является элементом
    // который хранил в себе ссылку на предыдущий элемент, сам элемент и ссылку на следующий элемент
    private static class Node<E> {

        private E item;
        private Node<E> next;
        private Node<E> prev;

        private Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }

        //дезинтегратор
        private void allToNull() {
            this.item = null;
            this.next = null;
            this.prev = null;
        }

        private void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        private Node<E> getPrev() {
            return prev;
        }

        private Node<E> getNext() {
            return next;
        }

        private E getItem() {
            return item;
        }
    }
}

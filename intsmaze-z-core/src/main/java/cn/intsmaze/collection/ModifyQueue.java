package cn.intsmaze.collection;
/** 
 * @author:YangLiu
 * @param <E>
 * @date:2018年7月5日 上午10:27:31 
 * @describe: 
 */
public class ModifyQueue<E> {

    /**
    * Head of linked list.
    * Invariant: head.item == null
    */
    Node<E> head;

   /**
    * Tail of linked list.
    * Invariant: last.next == null
    */
     Node<E> last;
	
   public ModifyQueue(int capacity) {
       if (capacity <= 0) {
           throw new IllegalArgumentException();
       }
       last = head = new Node<E>(null);//一开始，头和尾都指向同一个节点地址
       System.out.println("0:"+last);
       System.out.println("0:"+last.next);
       System.out.println("0:"+head);
       System.out.println("0:"+head.next);
   }
  
   static class Node<E> {
       E item;//存储的用户添加的元素

       /**
        * One of:
        * - the real successor Node
        * - this Node, meaning the successor is head.next
        * - null, meaning there is no successor (this is the last node)
        */
       Node<E> next;//指向下一个节点

       Node(E x) { item = x; }
   } 
   
   private void enqueue(Node<E> node) {
       // assert putLock.isHeldByCurrentThread();
       // assert last.next == null;
//       last = last.next = node;//将尾节点和他的下节点都指向同一个node.
	   last.next = node;
	   System.out.println("22:"+last);
	   System.out.println("22:"+last.next);
	   last = last.next;
	   System.out.println("223:"+last);
	   System.out.println("223:"+last.next);
      
       System.out.println("1:"+head);
       System.out.println("1:"+head.next);
       
       System.out.println("2:"+last);
       System.out.println("2:"+last.next);
   }
   
   public void offer(Node<E> node) {
       
       enqueue(node);
   }
   
   
   public E dequeue() {
       // assert takeLock.isHeldByCurrentThread();
       // assert head.item == null;
       Node<E> h = head;
       Node<E> first = h.next;
       h.next = h; // help GC
       head = first;
       E x = first.item;
       first.item = null;
       return x;
   }
   
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModifyQueue a=new ModifyQueue(10);
		
		Node<Integer> node = new Node<Integer>(123);
		System.out.println("添加的node地址:"+node);
		a.offer(node);
		
		
		Node<Integer> node1 = new Node<Integer>(234);
		System.out.println("再添加的node地址:"+node1);
		a.offer(node1);
		
		
		System.out.println("添加的node地址:"+node);
		System.out.println("添加的node地址:"+node.next);
		System.out.println("再添加的node地址:"+node1);
		System.out.println("再添加的node地址:"+node1.next);
		
		
		System.out.println("last指针地址:"+a.last);
		System.out.println("last指针地址:"+a.last.next);
		System.out.println("head指针地址:"+a.head);
		System.out.println("head指针地址:"+a.head.next);
		
		a.dequeue();
		a.dequeue();
		
		
		//没有数据后，他们的地址又一样了
		System.out.println("last指针地址:"+a.last);
		System.out.println("last指针地址:"+a.last.next);
		System.out.println("head指针地址:"+a.head);
		System.out.println("head指针地址:"+a.head.next);
		
	}

}

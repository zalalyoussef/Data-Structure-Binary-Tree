**Binary Tree and Performance Testing**

This repository contains the implementation and analysis tasks, focusing on binary search trees and AVL trees. The lab involves completing mandatory coding tasks, performing tests, and conducting performance evaluations using the Java Microbenchmark Harness (JMH).

## Project Objectives

1. **Understanding Binary Tree Structures**  
   Examine and work with the provided `Lab2_BinaryTree` project code to deepen your knowledge of binary search trees (`BstSet`) and AVL trees (`AvlSet`).

2. **Implementation Tasks**  
   Implement the missing methods in the `BstSet` and `AvlSet` classes to complete their functionality.

3. **Testing Functionality**  
   Test the implemented methods through the provided user interface or via the console.

4. **Performance Benchmarking**  
   Conduct speed tests using JMH to compare the performance of similar methods across different set implementations (`BstSet`, `AvlSet`, and `TreeSet`).

---

## Mandatory Implementation Tasks

### **1. BstSet Class**
Implement the following methods:  
- `remove(E element)`  
  - Includes the helper method `removeRecursive(E element, BstNode<E> node)`.  
  - Includes an iterative version of `remove()`.  
- `addAll(Set<E> set)`  
  - Adds all elements from the given set while avoiding duplicates.  
- `containsAll(Set<E> set)`  
  - Checks if the set contains all elements of the given set.  
- `retainAll(Set<E> set)`  
  - Removes elements not present in the given set.  
- `headSet(E e)`  
  - Returns a subset of elements up to `e` (exclusive).  
- `tailSet(E e)`  
  - Returns a subset of elements from `e` (inclusive) onward.  
- `subSet(E element1, E element2)`  
  - Returns a subset of elements from `element1` (inclusive) to `element2` (exclusive).

### **2. AvlSet Class**
Implement the following methods:  
- `remove()`  
  - Includes the helper method `removeRecursive(E element, BstNode<E> node)`.

---

## Speed Testing with JMH

### **Performance Comparison Tasks**
Conduct a speed test to compare the performance of one pair of methods from the following table:  

| Variant | Method 1                    | Method 2                    |
|---------|-----------------------------|-----------------------------|
| 1       | `BstSet.remove()`           | `AvlSet.remove()`           |
| 2       | `BstSet.contains()`         | `AvlSet.contains()`         |
| 3       | `BstSet.containsAll()`      | `AvlSet.containsAll()`      |
| 4       | `BstSet.addAll()`           | `AvlSet.addAll()`           |
| 5       | `BstSet.retainAll()`        | `AvlSet.retainAll()`        |
| 6       | `BstSet.remove()`           | `TreeSet<E>.remove()`       |
| 7       | `BstSet.contains()`         | `TreeSet<E>.contains()`     |
| 8       | `AvlSet.remove()`           | `TreeSet<E>.remove()`       |
| 9       | `AvlSet.contains()`         | `TreeSet<E>.contains()`     |

---

## How to Run

1. Clone this repository:  
   ```bash
   git clone https://github.com/your-username/Lab-2-BinaryTree.git
   ```
2. Open the project in your preferred Java IDE.
3. Implement the required methods in the `BstSet` and `AvlSet` classes.
4. Run tests via the provided user interface or console.
5. Perform performance benchmarks using JMH.

---

## License

This project is for educational purposes and is released under the MIT License.

---

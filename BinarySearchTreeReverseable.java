
class BinarySearchTreeReverseable<T extends Comparable<T>> extends BinarySearchTree<T> {
  private LinkedStack<T> inOrderStack;
	private ArrayIndexedList<T> balanceList;
  static final int INORDER_REV = 4;

	public BinarySearchTreeReverseable() {
		super();
	}

	private void inOrderRev(BSTNode<T> tree) {
		if (tree != null) {
			inOrderRev(tree.getLeft());
			inOrderStack.push(tree.getInfo());
			inOrderRev(tree.getRight());
		}
	}

	public int reset(int orderType) {
		int numNodes = size();

		if (orderType < 4) {
			super.reset(orderType);
		} else if (orderType == INORDER_REV) {
			inOrderStack = new LinkedStack<T>();
			inOrderRev(root);
		}

		return numNodes;
	}

	public T getNext (int orderType) {
    T answer = null;

		if (orderType < 4) {
			answer = super.getNext(orderType);
		} else if (orderType == INORDER_REV) {
      answer = (T)inOrderStack.top();
      inOrderStack.pop();
		}

    return answer;
	}

	public void balance() {
		int count = reset(1);
		balanceList = new ArrayIndexedList<T>(count);

		for (int i = 0; i < count; i++) {
			balanceList.add(i, getNext(1));
		}

		root = null;

		recBalance(0, count-1);
	}

  private void recBalance(int low, int high) {
		if(low == high) {
			add(balanceList.get(low));
		} else if ((low + 1) == high) {
			add(balanceList.get(low));
			add(balanceList.get(high));
		} else {
			int mid = (low + high) / 2;
			add(balanceList.get(mid));
			recBalance(low, mid - 1);
			recBalance(mid + 1, high);
		}
  }


}

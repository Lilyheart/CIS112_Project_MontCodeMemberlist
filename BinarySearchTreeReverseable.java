
class BinarySearchTreeReverseable<T extends Comparable<T>> extends BinarySearchTree<T> {
  protected LinkedStack inOrderStack;
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


}

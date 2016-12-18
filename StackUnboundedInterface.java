//----------------------------------------------------------------------------
// UnboundedStackInterface.java       by Dale/Joyce/Weems            Chapter 3
//
// Interface for a class that implements a stack of <T> with no bound
// on the size of the stack. A stack is a last-in, first-out structure.
//----------------------------------------------------------------------------

//Removed import and package references to book code

public interface StackUnboundedInterface<T> extends StackInterface<T>

{
  void push(T element);
  // Places element at the top of this stack.

}

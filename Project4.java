import java.util.Scanner;

class Project4 {
  public static void main(String[] args) {
    String test = "(a+b)/c*(d-e)+f/g";
    System.out.println("infix:     " + test);
    System.out.println("postfix:   " + postfix(test));
    System.out.println("prefix:    " + prefix(test));
  }

  static String postfix(String s) {
    String output = "";
    NodeStack<Character> ns = new NodeStack<Character>();
    char[] infix = s.toCharArray();

    for (char c: infix) {
      switch(c) {
        case '(':
          ns.push(c);
          break;
        case ')':
          while(!ns.top().equals('(')) {
            output += ns.pop();
          }
          ns.pop();
          break;
        case '*':
        case '/':
        case '+':
        case '-':
          while (!ns.isEmpty() && precedence(c) <= precedence(ns.top())) {
            output += ns.pop();
          }
          ns.push(c);
          break;
        default:
          output += c;
      }
    }

    while (!ns.isEmpty()) {

      // check for left over parenthesis here

      output += ns.pop();
    }

    return output;
  }

  static String prefix(String s) {
    String output = "";
    NodeStack<String> operands = new NodeStack<String>();
    NodeStack<Character> operators = new NodeStack<Character>();
    char[] infix = s.toCharArray();

    for (char c: infix) {
      char op = ' ';
      String left = "";
      String right = "";
      String combine = "";
      switch(c) {
        case '(':
          operators.push(c);
          break;
        case ')':
          while (!operators.top().equals('(')) {
            op = operators.pop();
            right = operands.pop();
            left = operands.pop();
            combine = op + left + right;
            operands.push(combine);
          }
          operators.pop();
          break;
        case '*':
        case '/':
        case '+':
        case '-':
          while (!operators.isEmpty() && precedence(c) <= precedence(operators.top())) {
            op = operators.pop();
            right = operands.pop();
            left = operands.pop();
            combine = op + left + right;
            operands.push(combine);
          }
          operators.push(c);
          break;
        default:
          operands.push(String.valueOf(c));
      }
    }

    while (!operators.isEmpty()) {
      char op = operators.pop();
      String right = operands.pop();
      String left = operands.pop();
      String combine = op + left + right;
      operands.push(combine);
    }

    output = operands.pop();

    return output;
  }

  static int precedence(char c) {
    switch(c) {
      case '*':
      case '/':
        return 3;
      case '+':
      case '-':
        return 2;
      case '(':
        return 1;
      default:
        return 0;
    }
  }
}

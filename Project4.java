import java.util.Scanner;

class Project4 {
  public static void main(String[] args) {
    String test = "(a-b*c)/d-e*(f+g)";
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
      output += ns.pop();
    }

    return output;
  }

  static String prefix(String infix) {
    String output = "";
    NodeStack<String> operands = new NodeStack<String>();
    NodeStack<Character> operators = new NodeStack<Character>();
    char[] infix = s.toCharArray();

    for (char c: infix) {
      switch(c) {
        case '(':
          break;
        case ')':
          break;
        case '*':
        case '/':
        case '+':
        case '-':
          break;
        default:
          output += c;
      }
    }

    /*
    while (!ns.isEmpty()) {
      output += ns.pop();
    }
    */

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
